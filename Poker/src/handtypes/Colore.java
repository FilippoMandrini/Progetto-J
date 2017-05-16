package handtypes;

import java.util.Collections;
import java.util.List;
import poker.Card;

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
            points = points + (double)card.getValue() /  (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    @Override
    public String toString()
    {
        return "Colore di " + Card.getSeedName(cards.get(0).getSeed()) + " al " + Card.getValueName(cards.get(0).getValue());
    }  
}