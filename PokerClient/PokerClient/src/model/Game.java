package model;

import actions.Action;
import actions.ActionSet;
import gametypes.GameType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe che rappresenta la partita
 */
public class Game extends GameObservable {
    
    private List<Player> players;
    private List<Player> activePlayers;
    private Action currentAction;
    private Board board;
    private int bet;
    private int minBet;
    private int totalPot;
    private GameType settings;
    private Player dealer;
    private int dealerPosition;
    private int currentPlayerPosition;
    private Player currentPlayer;
    private String lastMessage;
    private Set<ActionSet> allowedActions;
    private boolean inTurn;
    private int noOfHands;

    /**
     * Costruttore di Game
     * @param settings le impostazioni della partita
     * @param players la lista dei giocatori
     */
    public Game(GameType settings, List<Player> players) {
        this.settings = settings;
        this.players = players;
        noOfHands = 1;
    }
    
    /**
     * Aggiorna la lista dei giocatori attivi
     */
    public void setActivePlayers()
    {
        activePlayers.clear();
        for(Player player : players)
        {
            if (player.isActive())
            {
                if (!players.contains(player))
                    activePlayers.add(player);
            }
        }
    }

    /**
     * Ritorna la lista dei giocatori
     * @return la lista dei giocatori
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Imposta la lista dei giocatori
     * @param players i giocatori
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Ritorna la lista dei giocatori attivi
     * @return la lista dei giocatori attivi
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Imposta la lista dei giocatori attivi
     * @param activePlayers i giocatori attivi
     */
    public void setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }

    /**
     * Ritorna l'azione corrente
     * @return l'azione corrente
     */
    public Action getCurrentAction() {
        return currentAction;
    }

    /**
     * Imposta l'azione corrente
     * @param currentAction l'azione corrente
     */
    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * Ritorna il Board della partita
     * @return il Board della partita
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Imposta il Board della partita
     * @param board il Board della partita
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Ritorna il valore della puntata
     * @return il valore della puntata
     */
    public int getBet() {
        return bet;
    }

    /**
     * Imposta il valore della puntata
     * @param bet il valore della puntata
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Ritorna il valore della puntata minima
     * @return il valore della puntata minima
     */
    public int getMinBet() {
        return minBet;
    }

    /**
     * Imposta il valore della puntata minima
     * @param minBet il valore della puntata minima
     */
    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    /**
     * Ritorna il totale del Pot
     * @return il totale del Pot
     */
    public int getTotalPot() {
        return totalPot;
    }

    /**
     * Imposta il totale del Pot
     * @param totalPot il totale del Pot
     */
    public void setTotalPot(int totalPot) {
        this.totalPot = totalPot;
    }

    /**
     * Ritorna le impostazioni della partita
     * @return le impostazioni della partita
     */
    public GameType getSettings() {
        return settings;
    }

    /**
     * Imposta le impostazioni della partita
     * @param settings le impostazioni della partita
     */
    public void setSettings(GameType settings) {
        this.settings = settings;
    }

    /**
     * Costruttore di Game
     */
    public Game() {
        this.activePlayers = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    /**
     * Ritorna il giocatore dealer
     * @return il dealer
     */
    public Player getDealer() {
        return dealer;
    }

    /**
     * Ritorna l'ultimo messaggio
     * @return l'ultimo messaggio
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Imposta l'ultimo messaggio
     * @param lastMessage l'ultimo messaggio
     */
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    
    /**
     * Imposta il giocatore dealer
     * @param dealer il nuovo dealer
     */
    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    /**
     * Ritorna la posizione del dealer
     * @return la posizione del dealer
     */
    public int getDealerPosition() {
        return dealerPosition;
    }

    /**
     * Imposta la posizione del dealer
     * @param dealerPosition la posizione del dealer
     */
    public void setDealerPosition(int dealerPosition) {
        this.dealerPosition = dealerPosition;
    }

    /**
     * Ritorna la posizione del giocatore attuale
     * @return la posizione del giocatore attuale
     */
    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    /**
     * Imposta la posizione del giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     */
    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    /**
     * Ritorna il giocatore attuale
     * @return il giocatore attuale
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Imposta il giocatore attuale
     * @param currentPlayer il giocatore attuale
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Aggiorna il Board
     * @param board il Board della partita
     */
    public void updateBoard(Board board) {
        this.board = board;
        notifyBoardUpdated(board);
    }
    
    /**
     * Aggiorna l'ultimo messaggio
     * @param message l'ultimo messaggio
     */
    public void updateMessage(String message)
    {
        this.lastMessage = message;
        notifyMessageUpdated(message);
    }
    
    /**
     * Aggiorna la puntata
     * @param bet la puntata
     * @param minBet la puntata minima
     * @param totalPot il pot complessivo
     */
    public void updateBetting(int bet, int minBet, int totalPot)
    {
        this.bet = bet;
        this.minBet = minBet;
        this.totalPot = totalPot;
        notifyBettingUpdate(bet, minBet, totalPot);
    }
    
    /**
     * Aggiorna la partita
     * @param settings le impostazioni della partita
     * @param players i giocatori
     */
    public void updateGameStarted(GameType settings, List<Player> players)
    {
        this.settings = settings;
        this.players = players;
        setActivePlayers();
        notifyGameStarted(players, settings);
    }
    
    /**
     * Aggiorna il giocatore
     * @param player il giocatore
     */
    public void updatePlayer(Player player)
    {
        int itemIndex = players.indexOf(player);
        if (itemIndex != -1) {
            players.set(itemIndex, player);
        }
        itemIndex = activePlayers.indexOf(player);
        if (itemIndex != -1) {
            activePlayers.set(itemIndex, player);
        }
        notifyPlayerUpdated(player);
    }
    
    /**
     * Aggiorna l'azione corrente
     * @param currentPlayer il giocatore corrente
     */
    public void updateCurrentAction(Player currentPlayer)
    {
        this.currentAction = currentPlayer.getLastAction();
        this.currentPlayer = currentPlayer;
        notifyCurrentPlayerActed(currentPlayer);
    }
    
    /**
     * Aggiorna il giocatore attuale
     * @param player il giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     */
    public void updateCurrentPlayer(Player player, int currentPlayerPosition)
    {
        if (players.contains(player))
        {
            currentPlayer = player;
            this.currentPlayerPosition = currentPlayerPosition;
            notifyCurrentPlayerUpdated(currentPlayer, currentPlayerPosition);
        }
    }
    
    /**
     * Aggiorna il dealer
     * @param dealer il dealer
     * @param dealerPosition la posizione del dealer
     */
    public void updateDealer(Player dealer, int dealerPosition)
    {
        if (players.contains(dealer))
        {
            this.dealer = dealer;
            this.dealerPosition = dealerPosition;
            notifyHandStarted(dealer, dealerPosition);
        }
    }

    /**
     * Aggiorna per la richiesta di azione
     * @param bet la puntata
     * @param minBet la puntata minima
     * @param allowedActions le azioni consentite
     */
    public void actionRequested(int bet, int minBet, Set<ActionSet> allowedActions)
    {
        this.inTurn = true;
        this.bet = bet;
        this.minBet = minBet;
        this.allowedActions = allowedActions;
        notifyActionRequest(bet, minBet, allowedActions);
    }
    
    /**
     * Indica se è in corso il turno
     * @return true se è in corso il turno, false altrimenti
     */
    public boolean isInTurn() {
        return inTurn;
    }

    /**
     * Imposta se è in corso il turno
     * @param isInTurn true se è in corso il turno, false altrimenti
     */
    public void setInTurn(boolean isInTurn) {
        this.inTurn = isInTurn;
    }

    /**
     * Aggiorna all'inizio della mano
     * @param dealer il dealer
     * @param dealerPosition la posizione del dealer
     */
    public void updateHandStarted(Player dealer, int dealerPosition) {
        updateDealer(dealer, dealerPosition);
        noOfHands++;
    }

    /**
     * Ritorna il numero delle mani
     * @return il numero delle mani
     */
    public int getNoOfHands() {
        return noOfHands;
    }
    
}