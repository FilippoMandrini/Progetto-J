package handtypes;

import java.util.ArrayList;
import java.util.List;
import poker.Card;

/**
 * Classe astratta della mano di carte
 */
public abstract class Hand implements Comparable {
    
    protected List<Card> cards;
    
    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    public abstract double getPoints();

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card carta : cards)
        {
            this.cards.add(carta);
        }
    }
    
    @Override
    public int compareTo(Object o) {
        Hand other =  (Hand)o;
        return (int)(1000000 * (other.getPoints() - this.getPoints()));
    }

    public List<Card> getCards() {
        return cards;
    }
    
    /**
     * Stampa la descrizione delle carte nella mano
     * @return la stringa descrittiva delle carte nella mano
     */
    public String getFullDescription(){
        String result = "";
        for (Card carta : cards)
        {
            result += carta.toString() + "\n";
        }
        return result;
    }       
}