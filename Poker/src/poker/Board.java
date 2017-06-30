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
     * Distribuisce le carte personali del giocatore
     * @param player il giocatore
     * @param noOfCards numero di carte da distribuire al giocatore
     * @return true a consegna avvenuta con successo
     */
    public boolean dealHoleCards(Player player, int noOfCards) {
        player.resetCards();
        for (int i = 0; i < noOfCards; i++) {
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
            dealHoleCards(player, 2);
        }
    }

    /**
     * Mostra le carte comuni
     * @param noOfCards il numero di carte da mostrare
     */
    public void dealCommunityCards(int noOfCards)
    {
        deck.burnCard();
        for (int i = 0; i < noOfCards; i++) {
            communityCards.add(deck.dealCard());
        }    
    }
      
    /**
     * Esegue il reset delle carte del banco e del deck terminando la mano 
     * @return true a reset compiuto
     */
    public boolean clear() {
        this.communityCards.clear();
        this.deck.restore();
        return true;
    }
}