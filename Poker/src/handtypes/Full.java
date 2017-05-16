package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Full"
 * Mano formata da un tris e una coppia
 */
public class Full extends Hand{

    private Card three;
    private Card pair;

    public Full(List<Card> cards) {
        super(cards);
        this.three = this.cards.get(0);
        this.pair = this.cards.get(3);
    }
    
    @Override
    public double getPoints() {
        return 600 + three.getValue() + (double) pair.getValue() / 100.;
    }

    @Override
    public String toString() 
    {
        return "Full di " + Card.getValueName(three.getValue()) + " e " + Card.getValueName(pair.getValue());
    }
}