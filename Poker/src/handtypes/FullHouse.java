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

    public FullHouse(ArrayList<Card> cards) {
        super(cards);
        this.three = this.cards.get(0);
        this.pair = this.cards.get(3);
    }
    
    @Override
    public double getPoints() {
        return 600 + three.getValue() + (double) pair.getValue() / 100.;
    }

    @Override
    public String toString() {
        return "Full di " + Card.getValueName(three.getValue()) + " e " + Card.getValueName(pair.getValue());
    }
}
