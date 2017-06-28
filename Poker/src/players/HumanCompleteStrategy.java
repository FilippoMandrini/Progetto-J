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
import java.util.Objects;
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
    protected boolean blocked;
    
    private JSONEncoder encoder;
    private JSONDecoder decoder;
    
    /**
     * Costruttore della classe
     * @param socket il socket del client
     */
    public HumanCompleteStrategy(Socket socket) throws IOException {
        this.socket = socket;
        this.connected = true;
        this.blocked = false;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
        } catch (IOException ex) {
        }   
    }
    
    /** {@inheritDoc} */
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) throws IOException, SocketTimeoutException {
        out.println(JSONEncoder.getInstance().encodeAct(minBet, bet, allowedActions));
        blocked = true;
        String toDecode = in.readLine();
        Action action = (Action)JSONDecoder.getInstance().decode(toDecode);
        blocked = false;
        return action;
    }
    
    /** {@inheritDoc} */
    @Override
    public void boardUpdated(Board board) {
        out.println(JSONEncoder.getInstance().encodeBoardUpdated(board));
    }
    
    /** {@inheritDoc} */
    @Override
    public void playerUpdated(Player player) {
        out.println(JSONEncoder.getInstance().encodePlayerUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void messageUpdated(String message) {
        out.println(JSONEncoder.getInstance().encodeMessageUpdated(message));
    }
    
    /** {@inheritDoc} */
    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        out.println(JSONEncoder.getInstance().encodeHandStarted(dealer, dealerPosition));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        out.println(JSONEncoder.getInstance().encodeCurrentPlayerUpdated(currentPlayer, currentPlayerPosition));
    }
    
    /** {@inheritDoc} */
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        out.println(JSONEncoder.getInstance().encodeBettingUpdated(bet, minBet, totalPot));
    }
    
    /** {@inheritDoc} */
    @Override
    public void selfUpdated(Player player) {
        out.println(JSONEncoder.getInstance().encodeSelfUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        out.println(JSONEncoder.getInstance().encodeCurrentPlayerActed(shadowCopy));
    }
    
    /** {@inheritDoc} */
    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        out.println(JSONEncoder.getInstance().encodeGameStarted(players, settings));
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
        out.println(JSONEncoder.getInstance().encodeDisconnect());
    }

    @Override
    public void ping() throws IOException {
        out.println(JSONEncoder.getInstance().encodePing());
        if (out.checkError())
        {
            throw new IOException(this.toString());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.socket);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HumanCompleteStrategy other = (HumanCompleteStrategy) obj;
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }
    
    

    
    
    
    
}
