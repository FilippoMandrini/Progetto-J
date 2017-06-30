package players;

/**
 *Classe astratta della strategia del bot che implementa il client
 */
public abstract class AIStrategy implements Client{

    /**
     * Restituisce un valore booleano che indica se è connesso
     * @return true se connesso, false altrimenti
     */
    @Override
    public boolean isConnected() {
        return true;
    }
    
    /**
     * Restituisce un valore booleano che indica se è raggiungibile
     * @param timeout il tempo per il timeout
     * @return true se raggiungibile, false altrimenti
     */
    @Override
    public boolean isReachable(int timeout) {
        return true;
    }
    
    /**
     * Imposta se è connesso
     * @param connected true se connesso, false altrimenti
     */
    @Override
    public void setConnected(boolean connected) {
        // non implementato
    }
    
    /**
     * Disconnette il client
     */
    @Override
    public void disconnect()
    {
        // non implementato
    }
    
    /**
     * Esegue il ping
     */
    @Override
    public void ping() {
        //non implementato
    }

    /**
     * Restituisce un valore booleano che indica se è bloccato
     * @return true se bloccato, false altrimenti
     */
    @Override
    public boolean isBlocked()
    {
        return false;
    }
    
    /**
     * Restituisce un valore booleano che indica se il giocatore è umano
     * @return true se il giocatore è umano, false se è un bot
     */
    @Override
    public boolean isHuman()
    {
        return false;
    }
}
