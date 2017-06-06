package actions;

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
}