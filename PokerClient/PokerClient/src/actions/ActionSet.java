package actions;

/**
 * Enumerazione che rappresenta le azioni possibili
 */
public enum ActionSet {
    
    /**
     * Chiamata,Rialzo,Scommessa,Stare,Lasciare,Grande e Piccolo buio
     */
    CALL("Call"),
    RAISE("Raise"),
    BET("Bet"),
    CHECK("Check"),
    FOLD("Fold"),
    BIG_BLIND("Big Blind"),
    SMALL_BLIND("Small Blind");
    
    private String name;
    
    /**
     * Costruttore dell'enumerazione
     * @param name nome dell'azione
     */
    ActionSet(String name){
        this.name = name;
    }
}
