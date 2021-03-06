package actions;

import poker.GameFacade;

/**
 * Classe astratta che rappresenta l'azione da compiere
 */
public abstract class Action {
  
    protected String name;
    protected String description;
    protected int amount;
    protected ActionSet actionType;

    /**
     * Costruttore della classe Action
     * @param name nome dell'azione
     * @param description descrizione dell'azione
     */
    public Action(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Costruttore della classe Action
     * @param name nome dell'azione
     * @param description descrizione dell'azione
     * @param amount valore della scommessa
     */
    public Action(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Restituisce il nome dell'azione
     * @return nome dell'azione
     */ 
    public String getName() {
        return name;
    }

    /**
     * Restituisce la descrizione dell'azione
     * @return descrizione dell'azione
     */
    public String getDescription() {
        return description;
    }

    /**
     * Restituisce il valore della scommessa
     * @return valore della scommessa
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Imposta il valore della scommessa
     * @param amount valore della scommessa
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Restiuisce il tipo di azione
     * @return tipo di azione
     */
    public ActionSet getActionType() {
        return actionType;
    }

    /**
     * Imposta il nome dell'azione
     * @param name il nome dell'azione
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Imposta la descrizione dell'azione
     * @param description la descrizione dell'azione
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Imposta il set di azioni
     * @param actionType il set di azioni
     */
    public void setActionType(ActionSet actionType) {
        this.actionType = actionType;
    }
    
    /**
     * Esegue l'azione scelta
     * @param game la partita
     * @param playersLeft il numero di giocatori che devono ancora agire
     * @return il numero di giocatori che devono ancora agire dopo l'azione
     */
    public abstract int execute(GameFacade game, int playersLeft);

    /**
     * Ritorna la descrizione dell'azione
     * @return la descrizione dell'azione
     */
    @Override
    public String toString()
    {
        return description + " " + amount;
    }
    
}