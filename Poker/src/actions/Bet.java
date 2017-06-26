package actions;

import exceptions.IllegalActionException;
import poker.GameFacade;

/**
 * Classe che rappresenta la scommessa
 */
public class Bet extends Action {
    
    /**
     * Costruttore della classe Bet
     * @param amount valore della scommessa
     */
    public Bet(int amount) {
        super("Bet", "scommette", amount);
        this.actionType = ActionSet.BET;
    } 
    
    @Override
    public int execute(GameFacade game, int playersLeft) {

        if (amount < game.getMinBet() && amount < game.getCurrentPlayerStake())
        {
            throw new IllegalActionException("Azione illegale");
        }
        if (amount > game.getCurrentPlayerStake())
        {
            throw new IllegalActionException("Impossibile scommettere più dello stake");
        }
        game.setCurrentPlayerBet(amount);
        game.payCurrentPlayer(amount);
        game.setBet(amount);
        game.setMinBet(amount);
        game.setCurrentAsLastAggressor();
        return game.getNoOfActive() -1 ;
    }
}
