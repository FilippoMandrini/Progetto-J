/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nickelsilver
 */
public class HandTester {

    public static void main(String[] args) {

        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        Hand H1 = new TwoPair(new Card(10), new Card(5), new Card(7));
        Hand H2 = new TwoPair(new Card(11), new Card(7), new Card(8));
        Hand H3 = new TwoPair(new Card(11), new Card(7), new Card(6));
        Hand H4 = new ThreeOfAKind(new Card(10), new Card(5), new Card(9));
        Hand H5 = new FullHouse(new Card(4), new Card(5));
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i<5; i++)
        {
            cards.add(new Card(i+2, S3));
            i++;
        }
        Hand H6 = new Flush(cards);
        cards.clear();
        for (int i = 0; i<5; i++)
        {
            cards.add(new Card(i+7, S2));
        }
        
        Hand H7 = new Straight(cards);
        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(H1);
        hands.add(H2);
        hands.add(H3);
        hands.add(H4);
        hands.add(H5);
        hands.add(H6);
        hands.add(H7);
        Collections.sort(hands);
        for (Hand hand : hands) {
            
            System.out.println(hand.toString());
            System.out.println(hand.getPoints());
        }
    }

}
