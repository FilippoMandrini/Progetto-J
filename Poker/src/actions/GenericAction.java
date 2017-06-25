package actions;

import exceptions.IllegalActionException;
import poker.GameFacade;

public class GenericAction extends Action{
    
    public GenericAction(int amount)
    {
        super("Generic", "azione di test", amount);
    }

    @Override
    public int execute(GameFacade game, int playersLeft) {
        throw new IllegalActionException("Azione Generica di Test"); 
    }
}
