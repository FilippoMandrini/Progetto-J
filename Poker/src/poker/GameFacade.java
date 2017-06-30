package poker;

import gametypes.GameType;
import java.util.List;
import players.Player;

/**
 * Classe Facade di Game
 */
public class GameFacade {

    private final Game game;

    /**
     * Costruttore della classe
     * @param game la partita
     */
    public GameFacade(Game game) {
        this.game = game;
    }

    /**
     * Aggiunge un giocatore alla partita
     * @param player il giocatore
     */
    public void addPlayer(Player player) {
        game.addPlayer(player);
    }

    /**
     * Restituisce la lista dei giocatori attivi
     * @return la lista dei giocatori attivi
     */
    public List<Player> getActivePlayers() {
        return game.getActivePlayers();
    }

    /**
     * Restituisce la lista dei giocatori
     * @return la lista dei giocatori
     */
    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    /**
     * Restituisce le impostazioni della partita
     * @return le impostazioni della partita
     */
    public GameType getSettings() {
        return game.getSettings();
    }

    /**
     * Restituisce true se ci sono giocatori nella partita, false altrimenti
     * @return true se ci sono giocatori, false altrimenti
     */
    public boolean hasPlayers() {
        return game.hasPlayers();
    }

    /**
     * Restituisce il gestore dei pots
     * @return il gestore dei pots
     */
    public PotHandler getPotHandler() {
        return game.getPotHandler();
    }

    /**
     * Restituisce la posizione del giocatore corrente
     * @return la posizione del giocatore attuale
     */
    public int getCurrentPlayerPosition() {
        return game.getCurrentPlayerPosition();
    }

    /**
     * Restituisce il giocatore corrente
     * @return il giocatore corrente
     */
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    /**
     * Restituisce la puntata
     * @return il valore della puntata
     */
    public int getBet() {
        return game.getBet();
    }

    /**
     * Restituisce la puntata minima
     * @return il valore della puntata minima
     */
    public int getMinBet() {
        return game.getMinBet();
    }

    /**
     * Restituisce il valore del grande buio
     * @return il valore del grande buio
     */
    public int getCurrentBigBlind() {
        return game.getCurrentBigBlind();
    }

    /**
     * Restituisce il numero dei raise
     * @return il numero dei raise
     */
    public int getRaises() {
        return game.getRaises();
    }

    /**
     * Imposta il valore della puntata
     * @param bet il valore della puntata
     */
    public void setBet(int bet) {
        game.setBet(bet);
    }
    
    /**
     * Imposta il valore della puntata minima
     * @param bet il valore della puntata minima
     */
    public void setMinBet(int bet) {
        game.setMinBet(bet);
    }

    /**
     * Consegna il premio totale all'ultimo giocatore rimasto in partita
     */
    public void winLastActivePlayer() {
        getActivePlayers().get(0).win(getPotHandler().getTotalPot());
    }

    /**
     * Decrementa di 1 la posizione del giocatore attuale
     */
    public void decreaseCurrentPlayerPosition() {
        setCurrentPlayerPosition(getCurrentPlayerPosition() - 1);
    }

    /**
     * Aumenta di 1 la posizione del giocatore attuale
     */
    public void increaseCurrentPlayerPosition() {
        setCurrentPlayerPosition(getCurrentPlayerPosition() + 1);
    }

    /**
     * Imposta la posizione del giocatore attuale
     * @param newPos la nuova posizione del giocatore attuale
     */
    public void setCurrentPlayerPosition(int newPos) {
        game.setCurrentPlayerPosition(newPos);
    }

    /**
     * Gestisce il fold del giocatore rimuovendolo dai giocatori attivi e
     * cambia la posizione del giocatore attivo
     */
    public void foldCurrentPlayer() {
        removeCurrentPlayerFromActive();
        getCurrentPlayer().foldCards();
        decreaseCurrentPlayerPosition();
    }

    /**
     * Rimuove il giocatore attuale dalla lista degli attivi
     */
    public void removeCurrentPlayerFromActive() {
        removeFromActive(getCurrentPlayer());
    }

    /**
     * Rimuove un giocatore dalla lista degli attivi
     * @param player il giocatore
     */
    public void removeFromActive(Player player) {
        if (getActivePlayers().contains(player)) {
            getActivePlayers().remove(player);
        }
    }

    /**
     * Ritorna il numero dei giocatori attivi
     * @return il numero dei giocatori attivi
     */
    public int getNoOfActive() {
        return getActivePlayers().size();
    }

    /**
     * Ritorna lo stake del giocatore attuale
     * @return lo stake del giocatore attuale
     */
    public int getCurrentPlayerStake() {
        return getCurrentPlayer().getStake();
    }

    /**
     * Ritorna il valore della scommessa del giocatore attuale
     * @return il valore della scommessa del giocatore attuale
     */
    public int getCurrentPlayerBet() {
        return getCurrentPlayer().getCurrentBet();
    }

    /**
     * Imposta il valore della scommessa del giocatore attuale
     * @param amount il valore della scommessa
     */
    public void setCurrentPlayerBet(int amount) {
        getCurrentPlayer().setCurrentBet(amount);
    }

    /**
     * Detrae dallo stake del giocatore le fiches che deve pagare
     * @param amount le fiches da pagare
     */
    public void payCurrentPlayer(int amount) {
        getCurrentPlayer().pay(amount);
        getPotHandler().addToPot(amount, getCurrentPlayer());
    }

    /**
     * Imposta il giocatore corrente come ultimo aggressore
     */
    public void setCurrentAsLastAggressor() {
        setLastAggressor(getCurrentPlayer());
    }

    /**
     * Imposta l'ultimo giocatore aggressore
     * @param player il giocatore
     */
    public void setLastAggressor(Player player) {
        game.setLastAggressor(player);
    }
}