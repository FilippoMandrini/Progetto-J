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
public class FullHouse extends Hand{

    private Card three;
    private Card pair;

    public FullHouse(Card three, Card pair) {
        this.three = three;
        this.pair = pair;
    }
    
    @Override
    public double getPoints() {
        return 600 + three.getValue() + (double) pair.getValue() / 100.;
    }

    @Override
    public String toString() {
        return "Full di " + Card.getValueName(three.getValue()) + " e " + Card.getValueName(pair.getValue());
    }

    @Override
    public ArrayList<Card> generateHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
