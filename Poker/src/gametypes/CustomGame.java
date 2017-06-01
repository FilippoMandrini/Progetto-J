package gametypes;

/**
 * Classe che rappresenta le impostazioni personalizzate della partita
 */
public class CustomGame extends GameType{

    @Override
    public String getDescription() {  
        return "Variante personalizzata del poker nella sua versione HoldEm";
    }

    @Override
    public String toString() {
        return "Big Blind di base: " + this.bigBlind + "\nIl Big Blind raddoppia: " + !this.isBigBlindFixed + "\nMax giocatori: " + this.maxPlayers +"\nMax numero di raise: " + this.maxRaises;
    }

    /**
     * Imposta il valore massimo dei giocatori
     * @param maxPlayers valore massimo dei giocatori
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Imposta il numero massimo dei raise
     * @param maxRaises numero massimo dei raise
     */
    public void setMaxRaises(int maxRaises) {
        this.maxRaises = maxRaises;
    }

    /**
     *
     * @param bigBlind
     */
    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    /**
     *
     * @param isBigBlindFixed
     */
    public void setIsBigBlindFixed(boolean isBigBlindFixed) {
        this.isBigBlindFixed = isBigBlindFixed;
    }

    /**
     *
     * @return
     */
    public int getRoundsForDoubling() {
        return roundsForDoubling;
    }

    /**
     *
     * @param roundsForDoubling
     */
    public void setRoundsForDoubling(int roundsForDoubling) {
        
        if(isBigBlindFixed)
        {
            throw new IllegalArgumentException("Parameter cannot be changed");
        }
        this.roundsForDoubling = roundsForDoubling;
    }

    /**
     *
     * @return
     */
    public boolean isAlwaysDoShowdown() {
        return alwaysDoShowdown;
    }

    /**
     *
     * @param alwaysDoShowdown
     */
    public void setAlwaysDoShowdown(boolean alwaysDoShowdown) {
        this.alwaysDoShowdown = alwaysDoShowdown;
    }

    public CustomGame() {
        
    }
    
    
}
