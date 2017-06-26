package players;

import actions.Action;
import actions.ActionSet;
import gametypes.GameType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import poker.Board;
import json.JSONDecoder;
import json.JSONEncoder;

/**
 *Classe che si occupa della comunicazione con il client e stabilire le mosse del giocatore
 */
public class HumanCompleteStrategy extends HumanStrategy{

    protected Socket socket;
    protected BufferedReader in = null;
    protected PrintStream out = null;
    protected boolean connected;
    
    private JSONEncoder encoder;
    private JSONDecoder decoder;
    
    /**
     * Costruttore della classe
     * @param socket il socket del client
     */
    public HumanCompleteStrategy(Socket socket) throws IOException {
        this.socket = socket;
        this.connected = true;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
        } catch (IOException ex) {
        }   
        this.encoder = JSONEncoder.getInstance();
        this.decoder = JSONDecoder.getInstance();
    }
    
    /** {@inheritDoc} */
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) throws IOException, SocketTimeoutException {
        out.println(encoder.encodeAct(minBet, bet, allowedActions));
        return (Action)decoder.decode(in.readLine());

    }
    
    /** {@inheritDoc} */
    @Override
    public void boardUpdated(Board board) {
        out.println(encoder.encodeBoardUpdated(board));
    }
    
    /** {@inheritDoc} */
    @Override
    public void playerUpdated(Player player) {
        out.println(encoder.encodePlayerUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void messageUpdated(String message) {
        out.println(encoder.encodeMessageUpdated(message));
    }
    
    /** {@inheritDoc} */
    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        out.println(encoder.encodeHandStarted(dealer, dealerPosition));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        out.println(encoder.encodeCurrentPlayerUpdated(currentPlayer, currentPlayerPosition));
    }
    
    /** {@inheritDoc} */
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        out.println(encoder.encodeBettingUpdated(bet, minBet, totalPot));
    }
    
    /** {@inheritDoc} */
    @Override
    public void selfUpdated(Player player) {
        out.println(encoder.encodeSelfUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        out.println(encoder.encodeCurrentPlayerActed(shadowCopy));
    }
    
    /** {@inheritDoc} */
    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        out.println(encoder.encodeGameStarted(players, settings));
    }

    @Override
    public boolean isReachable(int timeout) {
        try {
            return socket.getInetAddress().isReachable(timeout);
        } catch (IOException ex) {
            return false;
        }
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public void disconnect() {
        out.println(encoder.encodeDisconnect());
    }

    
    
    
}
