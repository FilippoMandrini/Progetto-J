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
public class Flush extends Hand{
     

    public Flush(ArrayList<Card> cards) {
        super(cards);
        Collections.sort(this.cards);
    }
    
    @Override
    public double getPoints() {
        
        double points = 500;
        double i = 0;
        for (Card card: this.cards )
        {
            points = points + (double)card.getValue() /  (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    @Override
    public String toString()
    {
        return "Colore di " + Card.getSeedName(cards.get(0).getSeed()) + " al " + Card.getValueName(cards.get(0).getValue());
    }


    
}
