package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;

public class HandTester {

    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();
        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        cards.clear();
        cards.add(new Card(4, S1));
        cards.add(new Card(4, S2));
        cards.add(new Card(3, S0));
        cards.add(new Card(3, S2));
        cards.add(new Card(2, S3));
        Hand H1 = new DoppiaCoppia(cards);
        cards.clear();
        cards.add(new Card(2, S1));
        cards.add(new Card(2, S2));
        cards.add(new Card(10, S0));
        cards.add(new Card(4, S2));
        cards.add(new Card(3, S3));
        Hand H2 = new Coppia(cards);
        cards.clear();
        cards.add(new Card(4, S1));
        cards.add(new Card(4, S2));
        cards.add(new Card(4, S0));
        cards.add(new Card(3, S0));
        cards.add(new Card(2, S3));
        Hand H3 = new Tris(cards);
        cards.clear();
        cards.add(new Card(6, S1));
        cards.add(new Card(6, S2));
        cards.add(new Card(6, S0));
        cards.add(new Card(10, S2));
        cards.add(new Card(10, S3));
        Hand H4 = new Full(cards);
        cards.clear();
        cards.add(new Card(10, S1));
        cards.add(new Card(10, S2));
        cards.add(new Card(10, S0));
        cards.add(new Card(6, S2));
        cards.add(new Card(6, S3));
        Hand H5 = new Full(cards);
        cards.clear();
        cards.add(new Card(12, S3));
        cards.add(new Card(12, S1));
        cards.add(new Card(12, S0));
        cards.add(new Card(12, S2));
        cards.add(new Card(6, S3));
        Hand H6 = new PokerHand(cards);
        cards.clear();
        cards.add(new Card(12, S0));
        cards.add(new Card(11, S0));
        cards.add(new Card(10, S0));
        cards.add(new Card(8, S0));
        cards.add(new Card(9, S0));
        Hand H7 = new ScalaColore(cards);
        cards.clear();
        for (int i = 0; i<5; i++)
        {
            cards.add(new Card(i+2, S3));
        }
        Hand H8 = new Colore(cards);
        System.out.println(H8.getFullDescription());
        cards.clear();
        for (int i = 12; i>=8; i--)
        {
            cards.add(new Card(i, S2));
        }
        Hand H9 = new Scala(cards);
        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(H1);
        hands.add(H2);
        hands.add(H3);
        hands.add(H4);
        hands.add(H5);
        hands.add(H6);
        hands.add(H7);
        hands.add(H8);
        hands.add(H9);
        Collections.sort(hands);
        for (Hand hand : hands) {
            System.out.println(hand.toString());
            System.out.println(hand.getPoints());
        }
    }

}