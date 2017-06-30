package model;

import actions.ActionSet;
import gametypes.*;
import java.util.List;
import java.util.Set;

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
     * @param toShow true se è da mostrare
     */
    public void playerUpdated(Player player, boolean toShow);
    
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
     * @param dealerPosition la posizione del dealer
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
     * @param player il giocatore attuale
     */
    public void currentPlayerActed(Player player);

    /**
     * Invocato quando viene richiesta un'azione
     * @param bet la puntata
     * @param minBet la puntata minima
     * @param allowedActions le azioni consentite
     */
    public void actionRequest(int bet, int minBet, Set<ActionSet> allowedActions);
    
    /**
     * Invocato quando il giocatore si disconnette
     */
    public void disconnect();
    
}