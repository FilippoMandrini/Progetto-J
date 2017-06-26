package players;

import actions.Action;
import actions.ActionSet;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Set;
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
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) throws IOException, SocketTimeoutException;
    
    public boolean isReachable(int timeout);
    
    public boolean isConnected();
    
    public void setConnected(boolean connected);
        
    @Override
    public void disconnect();
    
}