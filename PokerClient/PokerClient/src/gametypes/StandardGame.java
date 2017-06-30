package gametypes;

/**
 * Classe che rappresenta il gioco con le regole standard
 */
public class StandardGame extends GameType{
    
    /**
     * Ritorna la descrizione delle impostazioni
     * @return la descrizione delle impostazioni
     */
    @Override
    public String getDescription() {  
        return "Variante standard per l'applicazione del poker nella sua versione HoldEm";
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
        return "Standard Game";
    }

    /**
     * Costruttore della classe
     * @param amount valore dello stake di partenza
     */
    public StandardGame(int amount) {
        this.maxPlayers = 5;
        this.maxRaises = 4;
        this.isBigBlindFixed = true;
        this.alwaysDoShowdown = true;
        this.startingStake = amount;
        this.bigBlind = 50;
        this.AIdelay = 2000;
        this.onlyHumans = true;
    }
    
}