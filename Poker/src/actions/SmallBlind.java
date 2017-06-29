package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta il piccolo buio
 */
public class SmallBlind extends Action {

    /**
     * Costruttore della classe SmallBlind
     * @param amount valore del piccolo buio
     */
    public SmallBlind(int amount) {
        super("Small Blind", "paga small blind", amount);
        this.actionType = ActionSet.SMALL_BLIND;
    }

    @Override
    /**
     * Esegue la puntata del piccolo buio
     * @param game la partita
     * @param playersLeft il numero di giocatori che devono ancora agire
     */
    public int execute(GameFacade game, int playersLeft) {
        game.payCurrentPlayer(amount);
        game.setCurrentPlayerBet(amount);
        return 999;
    }

}
