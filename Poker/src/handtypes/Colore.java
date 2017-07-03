package handtypes;

import java.util.Collections;
import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Colore"
 * Cinque carte dello stesso seme 
 */
public class Colore extends Hand{
     
    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public Colore(List<Card> cards) {
        super(cards);
        Collections.sort(this.cards);
        double pts = 500;
        double i = 0;
        for (Card card: this.cards)
        {
            pts = pts + (double)card.getRank() /  (double)Math.pow(100, i);
            i += 1;
        }
        this.points = pts;
    }
    
    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        double points = 500;
        double i = 0;
        for (Card card: this.cards)
        {
            points = points + (double)card.getRank() /  (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString()
    {
        return "Colore di " + Card.getSuitName(cards.get(0).getSuit()) + " al " + Card.getRankName(cards.get(0).getRank());
    }  
}