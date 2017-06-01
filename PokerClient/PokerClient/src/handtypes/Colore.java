package handtypes;

import java.util.Collections;
import java.util.List;
import model.Card;

/**
 * Mano che rappresenta la combinazione "Colore"
 * Cinque carte dello stesso seme 
 */
public class Colore extends Hand{
     
    public Colore(List<Card> cards) {
        super(cards);
        Collections.sort(this.cards);
    }
    
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
    
    @Override
    public String toString()
    {
        return "Colore di " + Card.getSuitName(cards.get(0).getSuit()) + " al " + Card.getRankName(cards.get(0).getRank());
    }  
}