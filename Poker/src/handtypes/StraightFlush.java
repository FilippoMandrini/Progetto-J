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
public class StraightFlush extends Hand {
    
    private Card high;
    
    public StraightFlush(Card high) {
        this.high = high;
    }
    
    @Override
    public double getPoints() {
        return 800 +high.getValue();
    }
    
    @Override
    public String toString()
    {
        return "Scala Colore di " + Card.getValueName(high.getValue());
    }

    @Override
    public ArrayList<Card> generateHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
