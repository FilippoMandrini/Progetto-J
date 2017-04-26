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
public class TwoPair extends Hand{

    private Card firstPair;
    private Card secondPair;
    private Card firstKicker;

    public TwoPair(ArrayList<Card> cards) {
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
