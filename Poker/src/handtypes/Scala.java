package handtypes;

import java.util.ArrayList;
import poker.Card;

public class Scala extends Hand {

    public Scala(ArrayList<Card> cards) {
        super(cards);
    }

    @Override
    public double getPoints() {
        if (cards.get(0).getValue() == 12 && cards.get(1).getValue() == 3) {
            return 400 + this.cards.get(1).getValue();
        }
        return 400 + this.cards.get(0).getValue();
    }

    @Override
    public String toString() {
        if (cards.get(0).getValue() == 12 && cards.get(1).getValue() == 3) {
            return "Scala al " + Card.getValueName(this.cards.get(1).getValue());
        }
        return "Scala al " + Card.getValueName(this.cards.get(0).getValue());
    }
}
