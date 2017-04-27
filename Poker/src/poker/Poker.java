package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;

public class Poker {

    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();
        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        for (int i = 0; i<4; i++)
        {
            cards.add(new Card(1, new Seed(i)));
        }
        cards.add(new Card(2, S1));
        Collections.sort(cards);
        for (int i = 0; i<5; i++)
        {
            System.out.println(cards.get(i));
        }
        Board B1 = new Board();
        Hand points = B1.evaluateSingle(cards);        
        System.out.println(points);
        cards.clear();        
        System.out.println("");
        cards.add(new Card(1, S1));
        cards.add(new Card(6, S2));
        cards.add(new Card(4, S0));
        cards.add(new Card(11, S2));
        cards.add(new Card(3, S3));
        for (int i = 0; i<5; i++)
        {
            System.out.println(cards.get(i));
        }
        points = B1.evaluateSingle(cards);
        System.out.println(points);
        cards.clear();        
        System.out.println("");
        cards.add(new Card(11, S1));
        cards.add(new Card(11, S2));
        cards.add(new Card(11, S3));
        cards.add(new Card(12, S2));
        cards.add(new Card(12, S0));
        cards.add(new Card(12, S1));
        cards.add(new Card(10, S2));
        points = B1.evaluateFull(cards);
        System.out.println(points);
        System.out.println("");
        System.out.println(points.getFullDescription());
        System.out.println("\nConsegna carte\n");
        
        Board B2 = new Board();
        ArrayList<Card> carteDate = new ArrayList<>();
        for (int i = 0; i<5; i++){
            carteDate.add(B2.giveCard());
        }
        for(Card carta: carteDate){
            System.out.println(carta);
        }
        
//        System.out.println("\nStampa tutte le carte");
//        Board B3 = new Board();
//        for(Card carta: B3.getMazzo().getMazzo()){
//            System.out.println(carta);
//        }*/
        
    }

}