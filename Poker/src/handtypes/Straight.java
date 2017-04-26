/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handtypes;

import java.util.ArrayList;
import java.util.Collections;
import poker.Card;

/**
 *
 * @author Nickelsilver
 */
public class Straight extends Hand {

    public Straight(ArrayList<Card> cards) {
        super(cards);
    }
    
    @Override
    public double getPoints() {
        return 400 + this.cards.get(0).getValue();
    }

    @Override
    public String toString()
    {
        return "Scala al " + Card.getValueName(this.cards.get(0).getValue());
    }
}
