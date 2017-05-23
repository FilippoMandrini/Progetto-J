package poker;

import exceptions.InvalidPlayerNameException;
import exceptions.PlayerNotFoundException; 
import gametypes.GameType;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe per la gestione della partita
 */
public class Game {
    
    private GameType settings;
    
    private Board board;
    
    private Deck deck;
    
    private List<Player> players;
    
    private List<Player> activePlayers;
    
    private Player currentPlayer;
    
    private int currentPlayerPosition;
    
    private Player dealer;
   
    private int dealerPosition;
    
    private List<Pot> pots;
    
    private int bet;

    private int minBet;
    
    private Player lastAggressor;
    
    private int raises;
    
    private int currentBigBlind;
    
    
    
    
    /**
     * Costruttore di Game
     * @param settings le impostazioni del tavolo
     */
    public Game(GameType settings) {
        
        this.settings = settings;
        this.currentBigBlind = settings.getBigBlind();
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        deck = new Deck();
        board = new Board();
        pots = new ArrayList<>();
    }
    
    /**
     * metodo principale del gioco
     */
    public void playGame()
    {

    }
    /**
     * metodo che gioca una mano
     */
    private void playSingleHand()
    {
        resetHand();
        if (activePlayers.size() > 2)
        {
            shiftCurrentPlayer();
        }
        betSmallBlind();
        shiftCurrentPlayer();
        betBigBlind();
        preFlop();
        if (activePlayers.size() > 1) 
        {
            bet = 0;
            preFlop();
            bettingRound();
            if (activePlayers.size() > 1) 
            {
                bet = 0;
                flop();
                minBet = 2 * currentBigBlind;
                bettingRound();
                if (activePlayers.size() > 1) 
                {
                    bet = 0;
                    turn();
                    bettingRound();
                    if (activePlayers.size() > 1) 
                    {
                        bet = 0;
                        showdown();
                    }
                }
            }
        }
        
    }    
    
    private void resetHand()
    {
       
    }
    
    /**
     * Metodo del giro di puntate
     */
    private void bettingRound()
    {
        
    }
    
    /**
     * cambia ruotando la posizione del giocatore che deve agire
     */
    private void shiftCurrentPlayer()
    {
        
    }
    
    private void betSmallBlind()
    {
        
    }
    
    private void betBigBlind()
    {
        
    }
    
    private void preFlop()
    {
        for(Player player : activePlayers)
        {
            this.board.dealCards(player);
        }
    }
    
    private void flop()
    {
        this.board.flop();
    }
    
    private void turn()
    {
        this.board.turn();
    }
    
    private void river()
    {
        this.board.river();
    }
    
    private void showdown()
    {
        
    }
    
    private void getTotalPot()
    {
        
    }
    
    /**
     * Aggiunge un giocatore controllando che non si utilizzi un nome 
     * già scelto da un altro utente
     * @param player il giocatore
     */
    public void addPlayer(Player player) {
        boolean presence = false;
        for (Player giocatore : players) {
            if (giocatore.getName().equals(player.getName())) {
                presence = true;
            }
        }
        if (presence == false) {
            players.add(player);
            player.setStake(settings.getStartingStake());
        } else {
            throw new InvalidPlayerNameException("Nome già utilizzato!");
        }
    }
    
    /**
     * Ritorna la mano del giocatore 
     * @param player il giocatore
     * @return la mano del giocatore
     */
    public Hand getPlayerHand(Player player) {
        if (players.contains(player)) {
            return player.getCurrent();
        }
        throw new PlayerNotFoundException("Giocatore non trovato!");
    }

    /**
     * Restituisce i players
     * @return la lista dei players
     */
    public List<Player> getGiocatori() {
        return players;
    }

    /**
     * Ritorna se sono o no presenti i players
     * @return true se sono presenti players, false altrimenti
     */
    public boolean hasPlayers() {
        return !players.isEmpty();
    }
}
    
    
    
   
