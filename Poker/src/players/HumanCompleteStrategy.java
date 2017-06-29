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
    
     /**
     * Invocato quando un esegue un'azione
     * @param minBet l'importo minimo della scommessa
     * @param bet la scommessa
     * @param allowedActions la lista delle azioni consentite
     * @return l'azione compiuta
     * @throws java.io.IOException
     * @throws java.net.SocketTimeoutException
     */
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

    /**
     * Restituisce un valore booleano che indica se è raggiungibile
     * @param timeout il tempo per il timeout
     * @return true se raggiungibile, false altrimenti
     */
    @Override
    public boolean isReachable(int timeout) {
        try {
            return socket.getInetAddress().isReachable(timeout);
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * Restituisce un valore booleano che indica se è connesso
     * @return true se connesso, false altrimenti
     */
    @Override
    public boolean isConnected() {
        return connected;
    }

    /**
     * Imposta se è connesso
     * @param connected true se connesso, false altrimenti
     */
    @Override
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Disconnette il client
     */
    @Override
    public void disconnect() {
        out.println(JSONEncoder.getInstance().encodeDisconnect());
    }

    /**
     * Esegue il ping
     * @throws IOException in caso di errore
     */
    @Override
    public void ping() throws IOException {
        out.println(JSONEncoder.getInstance().encodePing());
        if (out.checkError())
        {
            throw new IOException(this.toString());
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.socket);
        return hash;
    }

    /** {@inheritDoc} */
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

    /**
     * Restituisce un valore booleano che indica se è bloccato
     * @return true se bloccato, false altrimenti
     */
    @Override
    public boolean isBlocked() {
        return blocked;
    }
    
}
