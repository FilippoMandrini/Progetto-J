package players;

import actions.Action;
import actions.ActionSet;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import poker.GameObserver;

/**
 * Interfaccia del client
 */
public interface Client extends GameObserver{
    
    /**
     * Invocato quando un client esegue un'azione
     * @param minBet l'importo minimo della scommessa
     * @param bet la scommessa
     * @param allowedActions la lista delle azioni consentite
     * @return l'azione compiuta
     * @throws java.io.IOException
     * @throws java.net.SocketTimeoutException
     */
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) throws IOException, TimeoutException;
    
    /**
     * Restituisce un valore booleano che indica se è raggiungibile
     * @param timeout il tempo per il timeout
     * @return true se raggiungibile, false altrimenti
     */
    public boolean isReachable(int timeout);
    
    /**
     * Restituisce un valore booleano che indica se è connesso
     * @return true se connesso, false altrimenti
     */
    public boolean isConnected();
    
     /**
     * Restituisce un valore booleano che indica se è bloccato
     * @return true se bloccato, false altrimenti
     */
    public boolean isBlocked();
    
     /**
     * Restituisce un valore booleano che indica se il giocatore è umano
     * @return true se il giocatore è umano, false se è un bot
     */
    public boolean isHuman();
    
    /**
     * Imposta se è connesso
     * @param connected true se connesso, false altrimenti
     */
    public void setConnected(boolean connected);
        
    /**
     * Disconnette il client
     */
    @Override
    public void disconnect();
    
}