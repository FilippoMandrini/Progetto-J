package poker;

import gametypes.*;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import players.Player;
import players.ShadowPlayer;

/**
 * Interfaccia observer del game
 */
public abstract interface GameObserver {
    
    /**
     * Invocato quando si aggiorna la board
     * @param board il tavolo
     */
    public void boardUpdated(Board board);
    
    /**
     * Invocato quando si aggiorna lo stato di un giocatore
     * @param player il giocatore
     */
    public void playerUpdated(Player player);
    
    /**
     * Invocato per inviare messaggi al client
     * @param message il messaggio
     */
    public void messageUpdated(String message);
    
    /**
     * Invocato quando inizia la partita
     * @param players la lista dei giocatori
     * @param settings le impostazioni della partita
     */
    public void gameStarted(List<Player> players, GameType settings);
    
    /**
     * Invocato quando inizia la mano
     * @param dealer il player dealer della mano attuale
     */
    public void handStarted(Player dealer, int dealerPosition);
    
    /**
     * Invocato quando il giocatore corrente ha subito modifiche
     * @param currentPlayer il giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     */
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition);
    
    /**
     * Invocato quando viene effettuata una scommessa
     * @param bet l'importo della scommessa
     * @param minBet l'importo minimo della scommessa
     * @param totalPot il totale delle scommesse nel pot
     */
    public void bettingUpdated(int bet, int minBet, int totalPot);
    
    /**
     * Invocato quando il giocatore stesso subisce una modifica
     * @param player il giocatore stesso
     */
    public void selfUpdated(Player player);

    /**
     * Invocato quando il giocatore attuale compie un'azione
     * @param shadowCopy la copia pubblica del giocatore
     */
    public void currentPlayerActed(ShadowPlayer shadowCopy);
    
    public void disconnect();
    
}
