package actions;

/**
 * Classe che rappresenta il grande buio
 */
 public class BigBlind extends Action {

    /**
     * Costruttore di BigBlind
     * @param amount valore del grande buio
     */
    public BigBlind(int amount) {
        super("Big Blind", "paga big blind", amount);
        this.actionType = ActionSet.BIG_BLIND;
    }
}