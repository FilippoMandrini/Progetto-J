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
    
    private ArrayList<Card> cards;

    public Flush(ArrayList<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }
    
    @Override
    public double getPoints() {
        
        double points = 500;
        double i = 1;
        for (Card card: cards )
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

    @Override
    public ArrayList<Card> generateHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
