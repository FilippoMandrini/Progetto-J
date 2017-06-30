package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta la chiamata
 */
public class Call extends Action {
    
    /**
     * Costruttore di Call
     */
    public Call() {
        super("Call", "chiama");
        this.actionType = ActionSet.CALL;
    }

    @Override
    /**
     * Esegue la chiamata
     * @param game la partita
     * @param playersLeft il numero di giocatori che devono ancora agire
     * @return il numero di giocatori che devono ancora agire dopo questa azione
     */
    public int execute(GameFacade game, int playersLeft) {
        int callAmount = game.getBet() - game.getCurrentPlayerBet();
        if (callAmount > game.getCurrentPlayerStake())
        {
            callAmount = game.getCurrentPlayerStake();
        }
        game.setCurrentPlayerBet(game.getCurrentPlayerBet() + callAmount);
        game.payCurrentPlayer(callAmount);
        this.amount = callAmount;
        return playersLeft - 1;
    }
    
}