/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;

/**
 *
 * @author Nickelsilver
 */
public class Board {

    public Points evaluateSingle(ArrayList<Card> cards) 
    {
        if (checkColor(cards)) { // colore
            System.out.println("COLORE");
            Points points = new Points(6, cards.get(0).getSeed().getValue());
            return points;
        }
        
        int[] cardCounter = new int[13];  // poker
        for (Card carta : cards) {
            cardCounter[carta.getValue()-1]++;
        }
        for (int i = 0; i < cardCounter.length; i++) {
            if (cardCounter[i] == 4) {
                Points points = new Points(8, i+1);
                return points;
            }
        }
        
        return null;
    }

    private boolean checkColor(ArrayList<Card> cards) {
        int[] seedCounter = new int[4];
        for (Card carta : cards) {
            seedCounter[carta.getSeed().getValue()]++;
        }
        for (int i = 0; i < seedCounter.length; i++) {
            if (seedCounter[i] == 5) {
                return true;
            }
        }
        return false;
    }
}
