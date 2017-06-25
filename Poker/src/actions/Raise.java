package actions;

import exceptions.IllegalActionException;
import poker.GameFacade;

/**
 * Classe che rappresenta il Raise
 */
public class Raise extends Action {
    
    /**
     * Costruttore della classe Raise
     * @param amount valore della scommessa
     */
    public Raise(int amount) {
        super("Raise", "rilancia", amount);
        this.actionType = ActionSet.RAISE;
    }   

    @Override
    public int execute(GameFacade game, int playersLeft) {

        if (game.getBet() + amount - game.getCurrentPlayerBet() > game.getCurrentPlayerStake())
        {
            if (game.getBet() - game.getCurrentPlayerBet() > game.getCurrentPlayerStake())
            {
                throw new IllegalActionException("Totale per raise più alto dello stake");
            }
            amount = game.getCurrentPlayerStake() + game.getCurrentPlayerBet() - game.getBet();
        }
        game.setBet(amount + game.getBet());
        game.setMinBet(game.getBet());
        int raiseAmount = game.getBet() - game.getCurrentPlayerBet();
        game.payCurrentPlayer(raiseAmount);
        game.setCurrentPlayerBet(game.getBet());
        game.setCurrentAsLastAggressor();
        return game.getNoOfActive() -1 ;    
    }
}
