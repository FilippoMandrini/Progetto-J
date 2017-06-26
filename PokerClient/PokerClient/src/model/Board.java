package model;


import java.util.ArrayList;
import java.util.List;
import annotations.JSONExclude;

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

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }
    
    /**
     * Esegue il reset delle carte del banco e del deck terminando la mano 
     * @return 
     */
    public boolean clear() {
        this.communityCards.clear();
        return true;
    }

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
        
