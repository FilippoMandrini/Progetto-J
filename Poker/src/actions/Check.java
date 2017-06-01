package actions;

/**
 * Classe che rappresenta il Check
 */
public class Check extends Action{
    
    /**
     * Costruttore della classe Check
     */
    public Check() {
        super("Check", "checka");
        this.actionType = ActionSet.CHECK;
    }
}
