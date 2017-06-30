package players;

/**
 * Classe astratta che implementa il client
 */
public abstract class HumanStrategy implements Client {
    
    /**
     * Restituisce un valore booleano che indica se il giocatore è umano
     * @return true se il giocatore è umano, false se è un bot
     */
    @Override
    public boolean isHuman()
    {
        return true;
    }
}