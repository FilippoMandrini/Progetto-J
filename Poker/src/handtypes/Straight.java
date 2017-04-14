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
public class Straight extends Hand {

    private Card high;
    
    public Straight(Card high) {
        this.high = high;
    }
    
    @Override
    public double getPoints() {
        return 400 +high.getValue();
    }

    @Override
    public String toString()
    {
        return "Scala al " + Card.getValueName(high.getValue());
    }
    
}
