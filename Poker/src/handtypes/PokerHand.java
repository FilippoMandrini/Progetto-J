package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Poker"
 * Mano formata da quattro carte dello stesso valore
 */
public class PokerHand extends Hand{

    private Card poker;
    private Card firstKicker;

    public PokerHand(List<Card> cards) {
        super(cards);
        this.poker = cards.get(0);
        this.firstKicker = cards.get(4);
    }

    @Override
    public double getPoints() {
        return 700 + poker.getRank() + (double) firstKicker.getRank()/100;
    }
    
    @Override
    public String toString()
    {
        return "Poker di " + Card.getRankName(poker.getRank());
    }
}