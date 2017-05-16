package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Doppia coppia"
 * Mano che contiene due coppie di valori diversi.
 */
public class DoppiaCoppia extends Hand{

    private Card firstPair;
    private Card secondPair;
    private Card firstKicker;

    public DoppiaCoppia(List<Card> cards) {
        super(cards);
        this.firstPair = this.cards.get(0);
        this.secondPair = this.cards.get(2);
        this.firstKicker = this.cards.get(4);
    }

    @Override
    public double getPoints() {
        return 200 + firstPair.getValue() + (double) secondPair.getValue() /100 + (double)firstKicker.getValue() / 10000;
    }
    
    @Override
    public String toString()
    {
        return "Doppia Coppia di " + Card.getValueName(firstPair.getValue()) + " e " + Card.getValueName(secondPair.getValue());
    }
}