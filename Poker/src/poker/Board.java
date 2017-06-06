package poker;


import players.Player;
import java.util.ArrayList;
import java.util.List;
import annotations.JSONExclude;

/**
 *  Classe che rappresenta il banco
 */
public class Board {

    @JSONExclude
    private Deck deck;
    private List<Card> communityCards;
    
    /**
     * Costruttore di Board
     */
    public Board() {
        this.deck = new Deck();      
        this.communityCards = new ArrayList<>();
        
    }
    
    /**
     * Restituisce la lista delle carte comuni
     * @return le carte comuni
     */
    public List<Card> getCommunityCards() {
        return communityCards;
    }
    
    /**
     * Distribuisce le due carte personali del giocatore
     * @param player il giocatore
     * @return
     */
    public boolean dealCards(Player player) {
        player.reset();
        for (int i = 0; i < 2; i++) {
            player.addCard(this.deck.dealCard());
        }
        return true;
    }

    /**
     * Distribuisce le due carte personali di tutti i giocatori
     * e imposta lo stake iniziale a 2000 
     * @param giocatori
     */
    public void preflop(List<Player> giocatori) {
        for (Player player : giocatori) {
            dealCards(player);
        }
    }

    /**
     * Mostra le prime tre carte comuni
     * @return
     */
    public boolean flop() {
        for (int i = 0; i < 3; i++) {
            communityCards.add(deck.dealCard());
        }
        return true;
    }

    /**
     * Mostra la quarta carta comune
     * @return
     */
    public boolean turn() {
        deck.burnCard();
        communityCards.add(deck.dealCard());
        return true;
    }

    /**
     * Mostra la quinta e ultima carta comune
     * @return
     */
    public boolean river() {
        deck.burnCard();
        communityCards.add(deck.dealCard());
        return true;
    }
      
    /**
     * Esegue il reset delle carte del banco e del deck terminando la mano 
     * @return 
     */
    public boolean clear() {
        this.communityCards.clear();
        this.deck.restore();
        return true;
    }
}
        
