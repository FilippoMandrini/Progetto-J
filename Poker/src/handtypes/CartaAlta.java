package handtypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import poker.Card;

/**
 * Mano in cui non si hanno combinazioni e si considera solo la carta 
 * di valore maggiore
 */
public class CartaAlta extends Hand{

    public CartaAlta(List<Card> cards) {
        super(cards);
        Collections.sort(cards);
    }
    
    @Override
    public double getPoints() {
        double points = 0;
        double i = 1;
        for (Card card: this.cards)
        {
            points = points + (double)card.getRank() / (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    @Override
    public String toString()
    {
        return "Carta Alta: " + Card.getRankName(cards.get(0).getRank());
    }
}