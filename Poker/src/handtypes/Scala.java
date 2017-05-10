package handtypes;

import java.util.ArrayList;
import poker.Card;

public class Scala extends Hand {

    public Scala(ArrayList<Card> cards) {
        super(cards);
    }
    
    @Override
    public double getPoints() {
        return 400 + this.cards.get(0).getValue();
    }

    @Override
    public String toString()
    {
        return "Scala al " + Card.getValueName(this.cards.get(0).getValue());
    }
}