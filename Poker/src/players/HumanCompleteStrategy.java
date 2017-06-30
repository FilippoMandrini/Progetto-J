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
import java.util.Set;
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
     * @throws java.io.IOException in caso di problemi con InputStream / OutputStream
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
     * @throws java.io.IOException in caso di errori di stream o codifica/decodifica
     * @throws java.net.SocketTimeoutException in caso di timeout
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
    
    /**
     * Invocato quando si aggiorna la board
     * @param board il tavolo
     */
    @Override
    public void boardUpdated(Board board) {
        out.println(JSONEncoder.getInstance().encodeBoardUpdated(board));
    }
    
    /**
     * Invocato quando si aggiorna lo stato di un giocatore
     * @param player il giocatore
     */
    @Override
    public void playerUpdated(Player player) {
        out.println(JSONEncoder.getInstance().encodePlayerUpdated(player));
    }
    
    /**
     * Invocato per inviare messaggi al client
     * @param message il messaggio
     */
    @Override
    public void messageUpdated(String message) {
        out.println(JSONEncoder.getInstance().encodeMessageUpdated(message));
    }
    
    /**
     * Invocato quando inizia la mano
     * @param dealer il player dealer della mano attuale
     * @param dealerPosition la posizione del dealer
     */
    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        out.println(JSONEncoder.getInstance().encodeHandStarted(dealer, dealerPosition));
    }
    
    /**
     * Invocato quando il giocatore corrente ha subito modifiche
     * @param currentPlayer il giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     */
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        out.println(JSONEncoder.getInstance().encodeCurrentPlayerUpdated(currentPlayer, currentPlayerPosition));
    }
    
    /**
     * Invocato quando viene effettuata una scommessa
     * @param bet l'importo della scommessa
     * @param minBet l'importo minimo della scommessa
     * @param totalPot il totale delle scommesse nel pot
     */
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        out.println(JSONEncoder.getInstance().encodeBettingUpdated(bet, minBet, totalPot));
    }
    
    /**
     * Invocato quando il giocatore stesso subisce una modifica
     * @param player il giocatore stesso
     */
    @Override
    public void selfUpdated(Player player) {
        out.println(JSONEncoder.getInstance().encodeSelfUpdated(player));
    }
    
    /**
     * Invocato quando il giocatore attuale compie un'azione
     * @param shadowCopy la copia pubblica del giocatore
     */
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        out.println(JSONEncoder.getInstance().encodeCurrentPlayerActed(shadowCopy));
    }
    
    /**
     * Invocato quando inizia la partita
     * @param players la lista dei giocatori
     * @param settings le impostazioni della partita
     */
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
     * Invocato quando il giocatore si disconnette
     */
    @Override
    public void disconnect() {
        out.println(JSONEncoder.getInstance().encodeDisconnect());
    }

    /**
     * Invocato per il ping
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
