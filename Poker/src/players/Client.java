package players;

import actions.Action;
import actions.ActionSet;
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
     */
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions);
    
}