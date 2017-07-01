package gametypes;

/**
 * Classe che rappresenta le impostazioni personalizzate della partita
 */
public class CustomGame extends GameType{

    /**
     * Ritorna la descrizione delle impostazioni
     * @return la descrizione delle impostazioni
     */
    @Override
    public String getDescription() {  
        return "Variante personalizzata del poker nella sua versione HoldEm";
    }

    /**
     * Ritorna la descrizione dettagliata delle impostazioni
     * @return la descrizione dettagliata delle impostazioni
     */
    @Override
    public String toString() {
        return "Big Blind di base: " + this.bigBlind + "\nIl Big Blind raddoppia: " + !this.isBigBlindFixed + "\nMax giocatori: " + this.maxPlayers +"\nMax numero di raise: " + this.maxRaises;
    }
    
    /**
     * Ritorna il nome delle impostazioni
     * @return il nome delle impostazioni
     */
    @Override
    public String getName()
    {
        return "Custom Game";
    }


    /**
     * Imposta il numero massimo dei giocatori
     * @param maxPlayers il numero massimo dei giocatori
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Imposta il numero massimo dei raise
     * @param maxRaises il numero massimo dei raise
     */
    public void setMaxRaises(int maxRaises) {
        this.maxRaises = maxRaises;
    }

    /**
     * Imposta il valore del Grande Buio
     * @param bigBlind il valore del Grande Buio
     */
    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    /**
     * Imposta se il Grande Buio e' fisso
     * @param isBigBlindFixed true se fisso, false altrimenti
     */
    public void setIsBigBlindFixed(boolean isBigBlindFixed) {
        this.isBigBlindFixed = isBigBlindFixed;
    }

    /**
     * Restituisce il numero di turni per il raddoppio del Grande Buio
     * @return il numero di turni per il raddoppio del Grande Buio
     */
    public int getRoundsForDoubling() {
        return roundsForDoubling;
    }

    /**
     * Imposta il numero di turni per il raddoppio del Grande Buio
     * @param roundsForDoubling il numero di turni per il raddoppio
     * @throws IllegalArgumentException quando il Grande Buio e' fisso
     */
    public void setRoundsForDoubling(int roundsForDoubling) {
        
        if(isBigBlindFixed)
        {
            throw new IllegalArgumentException("Parameter cannot be changed");
        }
        this.roundsForDoubling = roundsForDoubling;
    }

    /**
     * Indica se esegue sempre lo Showdown
     * @return true se lo esegue, false altrimenti
     */
    public boolean isAlwaysDoShowdown() {
        return alwaysDoShowdown;
    }

    /**
     * Imposta se eseguire sempre lo Showdown
     * @param alwaysDoShowdown true se eseguirlo sempre, false altrimenti
     */
    public void setAlwaysDoShowdown(boolean alwaysDoShowdown) {
        this.alwaysDoShowdown = alwaysDoShowdown;
    }

    /**
     * Ritorna la durata delle azioni dei bot
     * @return la durata delle azioni dei bot
     */
    @Override
    public int getAIdelay() {
        return AIdelay;
    }

    /**
     * Imposta la durata delle azioni dei bot
     * @param AIdelay la durata delle azioni dei bot
     */
    public void setAIdelay(int AIdelay) {
        this.AIdelay = AIdelay;
    }

    /**
     * Costruttore della classe
     */
    public CustomGame() {
        
    }
    
}