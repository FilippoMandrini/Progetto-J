package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Tris"
 * Mano formata da tre carte dello stesso valore
 */
public class Tris extends Hand {

    private Card three;
    private Card firstKicker;
    private Card secondKicker;

    public Tris(List<Card> cards) {
        super(cards);
        this.three = this.cards.get(0);
        this.firstKicker = this.cards.get(3);
        this.secondKicker = this.cards.get(4);
    }

    @Override
    public double getPoints() {
        return 300 + three.getValue() + (double) firstKicker.getValue() / 100 + (double) secondKicker.getValue() / 10000;
    }
    
    @Override
    public String toString()
    {
        return "Tris di " + Card.getValueName(three.getValue());
    }
}