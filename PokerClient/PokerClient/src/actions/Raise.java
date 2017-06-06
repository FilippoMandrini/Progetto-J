package actions;

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
}
