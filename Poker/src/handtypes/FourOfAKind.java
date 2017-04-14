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
public class FourOfAKind extends Hand{

    private Card poker;
    private Card firstKicker;

    public FourOfAKind(Card poker, Card firstKicker) {
        this.poker = poker;
        this.firstKicker = firstKicker;
    }

    @Override
    public double getPoints() {
        return 700 + poker.getValue() + (double) firstKicker.getValue()/100;
    }
    
    @Override
    public String toString()
    {
        return "Poker di " + Card.getValueName(poker.getValue());
    }
    
}
