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

    public OnePair(Card pair, Card firstKicker, Card secondKicker, Card thirdKicker) {
        this.pair = pair;
        this.firstKicker = firstKicker;
        this.secondKicker = secondKicker;
        this.thirdKicker = thirdKicker;
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

    @Override
    public ArrayList<Card> generateHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
