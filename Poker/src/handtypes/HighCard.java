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
public class HighCard extends Hand{

    private ArrayList<Card> cards;

    public HighCard(ArrayList<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }
    
    @Override
    public double getPoints() {
        
        double points = 0;
        double i = 1;
        for (Card card: cards )
        {
            points = points + (double)card.getValue() / (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    @Override
    public String toString()
    {
        return "Carta Alta: " + Card.getValueName(cards.get(0).getValue());
    }
}
