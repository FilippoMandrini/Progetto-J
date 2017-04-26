/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handtypes;

import java.util.ArrayList;
import poker.Card;

/**
 *
 * @author Nickelsilver
 */
public class OnePair extends Hand {

    private Card pair;
    private Card firstKicker;
    private Card secondKicker;
    private Card thirdKicker;

    public OnePair(ArrayList<Card> cards) {
        super(cards);
        this.pair = this.cards.get(0);
        this.secondKicker = this.cards.get(3);
        this.thirdKicker = this.cards.get(4);
        this.firstKicker = this.cards.get(2);
    }



    @Override
    public double getPoints() {
        return 100 + pair.getValue() + (double) firstKicker.getValue() / 100. + (double) secondKicker.getValue() / 10000. + (double) thirdKicker.getValue() / 1000000.;
    }

    @Override
    public String toString()
    {
        return "Coppia di " + Card.getValueName(pair.getValue());
    }
}
