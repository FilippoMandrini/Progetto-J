package actions;

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
}
