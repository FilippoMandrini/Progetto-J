/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import mani.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nickelsilver
 */
public class TestMano {

    public static void main(String[] args) {

        ArrayList<Carta> carte = new ArrayList<>();
        Seme S3 = new Seme(TipoSeme.CUORI, 3);
        Seme S2 = new Seme(TipoSeme.DENARI, 2);
        Seme S1 = new Seme(TipoSeme.FIORI, 1);
        Seme S0 = new Seme(TipoSeme.PICCHE, 0);
        carte.clear();
        carte.add(new Carta(4, S1));
        carte.add(new Carta(4, S2));
        carte.add(new Carta(3, S0));
        carte.add(new Carta(3, S2));
        carte.add(new Carta(2, S3));
        Mano H1 = new DoppiaCoppia(carte);
        carte.clear();
        carte.add(new Carta(2, S1));
        carte.add(new Carta(2, S2));
        carte.add(new Carta(10, S0));
        carte.add(new Carta(4, S2));
        carte.add(new Carta(3, S3));
        Mano H2 = new Coppia(carte);
        carte.clear();
        carte.add(new Carta(4, S1));
        carte.add(new Carta(4, S2));
        carte.add(new Carta(4, S0));
        carte.add(new Carta(3, S0));
        carte.add(new Carta(2, S3));
        Mano H3 = new Tris(carte);
        carte.clear();
        carte.add(new Carta(6, S1));
        carte.add(new Carta(6, S2));
        carte.add(new Carta(6, S0));
        carte.add(new Carta(10, S2));
        carte.add(new Carta(10, S3));
        Mano H4 = new Full(carte);
        carte.clear();
        carte.add(new Carta(10, S1));
        carte.add(new Carta(10, S2));
        carte.add(new Carta(10, S0));
        carte.add(new Carta(6, S2));
        carte.add(new Carta(6, S3));
        Mano H5 = new Full(carte);
        carte.clear();
        carte.add(new Carta(12, S3));
        carte.add(new Carta(12, S1));
        carte.add(new Carta(12, S0));
        carte.add(new Carta(12, S2));
        carte.add(new Carta(6, S3));
        Mano H6 = new Poker(carte);
        carte.clear();
        carte.add(new Carta(12, S0));
        carte.add(new Carta(11, S0));
        carte.add(new Carta(10, S0));
        carte.add(new Carta(8, S0));
        carte.add(new Carta(9, S0));
        Mano H7 = new ScalaColore(carte);
        carte.clear();
        for (int i = 0; i<5; i++)
        {
            carte.add(new Carta(i+2, S3));
        }
        Mano H8 = new Colore(carte);
        System.out.println(H8.getDescrizione());
        carte.clear();
        for (int i = 12; i>=8; i--)
        {
            carte.add(new Carta(i, S2));
        }
        Mano H9 = new Scala(carte);
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
