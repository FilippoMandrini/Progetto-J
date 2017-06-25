package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta il Check
 */
public class Check extends Action{
    
    /**
     * Costruttore della classe Check
     */
    public Check() {
        super("Check", "checka");
        this.actionType = ActionSet.CHECK;
    }

    @Override
    public int execute(GameFacade game, int playersLeft) {
        return playersLeft -1;
    }
}
