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
public class Poker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();
        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        for (int i = 0; i<4; i++)
        {
            cards.add(new Card(1, S0));
        }
        cards.add(new Card(2, S1));
        Board B1 = new Board();
        Points points = B1.evaluateSingle(cards);
        if (points== null)
            System.out.println("NON PREVISTO");
        else
            System.out.println("Punteggio:" + points.getType() + " " + points.getMain());
    }

}
