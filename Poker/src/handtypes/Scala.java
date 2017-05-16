package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Scala"
 * Mano formata da cinque carte con ordine consecutivo di valore
 */
public class Scala extends Hand {

    public Scala(List<Card> cards) {
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
