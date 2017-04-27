/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import mani.Colore;
import mani.Scala;
import mani.Mano;
import mani.ScalaColore;
import mani.Coppia;
import mani.DoppiaCoppia;
import mani.Poker;
import mani.Full;
import mani.Tris;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nickelsilver
 */
public class HandTester {

    public static void main(String[] args) {

        ArrayList<Carta> cards = new ArrayList<>();
        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        cards.clear();
        cards.add(new Carta(4, S1));
        cards.add(new Carta(4, S2));
        cards.add(new Carta(3, S0));
        cards.add(new Carta(3, S2));
        cards.add(new Carta(2, S3));
        Mano H1 = new DoppiaCoppia(cards);
        cards.clear();
        cards.add(new Carta(2, S1));
        cards.add(new Carta(2, S2));
        cards.add(new Carta(10, S0));
        cards.add(new Carta(4, S2));
        cards.add(new Carta(3, S3));
        Mano H2 = new Coppia(cards);
        cards.clear();
        cards.add(new Carta(4, S1));
        cards.add(new Carta(4, S2));
        cards.add(new Carta(4, S0));
        cards.add(new Carta(3, S0));
        cards.add(new Carta(2, S3));
        Mano H3 = new Tris(cards);
        cards.clear();
        cards.add(new Carta(6, S1));
        cards.add(new Carta(6, S2));
        cards.add(new Carta(6, S0));
        cards.add(new Carta(10, S2));
        cards.add(new Carta(10, S3));
        Mano H4 = new Full(cards);
        cards.clear();
        cards.add(new Carta(10, S1));
        cards.add(new Carta(10, S2));
        cards.add(new Carta(10, S0));
        cards.add(new Carta(6, S2));
        cards.add(new Carta(6, S3));
        Mano H5 = new Full(cards);
        cards.clear();
        cards.add(new Carta(12, S3));
        cards.add(new Carta(12, S1));
        cards.add(new Carta(12, S0));
        cards.add(new Carta(12, S2));
        cards.add(new Carta(6, S3));
        Mano H6 = new Poker(cards);
        cards.clear();
        cards.add(new Carta(12, S0));
        cards.add(new Carta(11, S0));
        cards.add(new Carta(10, S0));
        cards.add(new Carta(8, S0));
        cards.add(new Carta(9, S0));
        Mano H7 = new ScalaColore(cards);
        cards.clear();
        for (int i = 0; i<5; i++)
        {
            cards.add(new Carta(i+2, S3));
        }
        Mano H8 = new Colore(cards);
        System.out.println(H8.getDescrizione());
        cards.clear();
        for (int i = 12; i>=8; i--)
        {
            cards.add(new Carta(i, S2));
        }
        Mano H9 = new Scala(cards);
        ArrayList<Mano> hands = new ArrayList<>();
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
        for (Mano hand : hands) {
            
            System.out.println(hand.toString());
            System.out.println(hand.getPunti());
        }
    }

}
