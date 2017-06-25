package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta il Fold
 */
public class Fold extends Action {
    
    /**
     * Costruttore della classe Fold
     */
    public Fold() {
        super("Fold", "folda");
        this.actionType = ActionSet.FOLD;
    }

    @Override
    public int execute(GameFacade game, int playersLeft) {
        
        game.foldCurrentPlayer();
        if (game.getNoOfActive() == 1)
        {
            game.winLastActivePlayer();
            return 0;
        }
        return playersLeft - 1;
    }
    
}