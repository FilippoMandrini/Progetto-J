
package handtypes;

import java.util.ArrayList;
import poker.Card;

public class Tris extends Hand {

    private Card three;
    private Card firstKicker;
    private Card secondKicker;

    public Tris(ArrayList<Card> cards) {
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
