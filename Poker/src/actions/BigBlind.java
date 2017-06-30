package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta il grande buio
 */
 public class BigBlind extends Action {

    /**
     * Costruttore che rappresenta il BigBlind
     * @param amount valore del grande buio
     */
    public BigBlind(int amount) {
        super("Big Blind", "paga big blind", amount);
        this.actionType = ActionSet.BIG_BLIND;
    }

    @Override
    /**
     * Esegue la puntata del grande buio
     * @param game la partita
     * @param playersLeft il numero di giocatori che devono ancora agire
     */
    public int execute(GameFacade game, int playersLeft) {
        game.payCurrentPlayer(amount);
        game.setCurrentPlayerBet(amount);
        game.setBet(amount);
        game.setMinBet(amount);
        return 999;
    }
}