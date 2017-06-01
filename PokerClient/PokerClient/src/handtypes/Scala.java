package handtypes;

import java.util.List;
import model.Card;

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
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return 400 + this.cards.get(1).getRank();
        }
        return 400 + this.cards.get(0).getRank();
    }

    @Override
    public String toString() {
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return "Scala al " + Card.getRankName(this.cards.get(1).getRank());
        }
        return "Scala al " + Card.getRankName(this.cards.get(0).getRank());
    }
}
