
package handtypes;

import java.util.ArrayList;
import poker.Card;

public class Poker extends Hand{

    private Card poker;
    private Card firstKicker;

    public Poker(ArrayList<Card> cards) {
        super(cards);
        this.poker = cards.get(0);
        this.firstKicker = cards.get(4);
    }

    @Override
    public double getPoints() {
        return 700 + poker.getValue() + (double) firstKicker.getValue()/100;
    }
    
    @Override
    public String toString()
    {
        return "Poker di " + Card.getValueName(poker.getValue());
    }
}