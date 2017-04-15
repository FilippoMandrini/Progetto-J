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

    private ArrayList<Card> cards;

    public Straight(ArrayList<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }
    
    @Override
    public double getPoints() {
        return 400 +cards.get(0).getValue();
    }

    @Override
    public String toString()
    {
        return "Scala al " + Card.getValueName(cards.get(0).getValue());
    }

    @Override
    public ArrayList<Card> generateHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
