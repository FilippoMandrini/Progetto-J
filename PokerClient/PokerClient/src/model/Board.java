package model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe che rappresenta il banco
 */
public class Board {

    private List<Card> communityCards;
    
    /**
     * Costruttore di Board
     */
    public Board() {
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
     * Imposta le carte comuni
     * @param communityCards le carte comuni
     */
    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }
    
    /**
     * Esegue il reset delle carte del banco e del deck terminando la mano
     */
    public boolean clear() {
        this.communityCards.clear();
        return true;
    }

    /**
     * Ritorna i nomi delle carte comuni
     * @return i nomi delle carte comuni
     */
    @Override
    public String toString() {
        
        String report = new String();
        for (Card cc : communityCards)
        {
            report += cc.toString() + "\n";
        }
        return report;
    }
      
}