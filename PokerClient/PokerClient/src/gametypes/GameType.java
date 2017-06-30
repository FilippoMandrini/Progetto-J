package gametypes;

/**
 * Classe astratta che rappresenta le impostazioni del gioco
 */
public abstract class GameType {
    
    protected int maxPlayers;
    protected int maxRaises;
    protected int bigBlind;
    protected boolean isBigBlindFixed;
    protected int roundsForDoubling;
    protected boolean alwaysDoShowdown;
    protected int startingStake;
    protected int AIdelay;
    protected boolean onlyHumans;
    
    /**
     * Ritorna la descrizione delle impostazioni
     * @return la descrizione delle impostazioni
     */
    public abstract String getDescription();
    
    /**
     * Ritorna la descrizione dettagliata delle impostazioni
     * @return la descrizione dettagliata delle impostazioni
     */
    @Override
    public abstract String toString();
    
    /**
     * Ritorna il nome delle impostazioni
     * @return il nome delle impostazioni
     */
    public abstract String getName();

    /**
     * Ritorna il numero massimo di giocatori possibili
     * @return il numero massimo di giocatori possibili
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }
    
    /**
     * Ritorna il numero massimo di raise possibili
     * @return il numero massimo di raise possibili
     */
    public int getMaxRaises() {
        return maxRaises;
    }
    
    /**
     * Ritorna il valore del grande buio
     * @return valore del grande buio
     */
    public int getBigBlind() {
        return bigBlind;
    }

    /**
     * Ritorna una booleana che indica se il grande buio è fisso oppure no
     * @return true se è fissato, false altrimenti
     */
    public boolean isIsBigBlindFixed() {
        return isBigBlindFixed;
    }

    /**
     * Ritorna il valore di partenza dello stake
     * @return valore di partenza dello stake
     */
    public int getStartingStake() {
        return startingStake;
    }

    /**
     * Imposta il valore dello stake di partenza
     * @param startingStake stake di partenza
     */
    public void setStartingStake(int startingStake) {
        this.startingStake = startingStake;
    }  

    /**
     * Ritorna la durata delle azioni dei bot
     * @return la durata delle azioni dei bot
     */
    public int getAIdelay() {
        return AIdelay;
    }

    /**
     * Indica se sono ammessi solo giocatori umani
     * @return true se sono ammessi solo umani, false altrimenti
     */
    public boolean isOnlyHumans() {
        return onlyHumans;
    }
    
}