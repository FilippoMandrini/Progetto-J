/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handtypes;

import poker.Card;

/**
 *
 * @author Nickelsilver
 */
public class ThreeOfAKind extends Hand {

    private Card three;
    private Card firstKicker;
    private Card secondKicker;

    public ThreeOfAKind(Card three, Card firstKicker, Card secondKicker) {
        this.three = three;
        this.firstKicker = firstKicker;
        this.secondKicker = secondKicker;
    }

    @Override
    public double getPoints() {
        return 300 + three.getValue() + (double) firstKicker.getValue() / 100 + (double) secondKicker.getValue() / 10000;
    }
    
    @Override
    public String toString()
    {
        return "Tris di " + Card.getValueName(three.getValue());
    }
    
}
