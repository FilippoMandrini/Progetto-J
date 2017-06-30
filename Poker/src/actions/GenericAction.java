package actions;

import exceptions.IllegalActionException;
import poker.GameFacade;

/**
 * Azione generica (per i test)
 */
public class GenericAction extends Action{
    
    /**
     * Costruttore di GenericAction
     */
    public GenericAction(int amount)
    {
        super("Generic", "azione di test", amount);
    }

    @Override
    /** @inheritDoc */
    public int execute(GameFacade game, int playersLeft) {
        throw new IllegalActionException("Azione Generica di Test"); 
    }
}