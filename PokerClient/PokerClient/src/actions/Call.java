package actions;

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
    
}