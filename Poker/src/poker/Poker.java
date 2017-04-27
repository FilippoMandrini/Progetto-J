package poker;

import mani.Mano;
import java.util.ArrayList;
import java.util.Collections;

public class Poker {

    public static void main(String[] args) {

        ArrayList<Carta> cards = new ArrayList<>();
        Seed S3 = new Seed(SeedType.HEARTS, 3);
        Seed S2 = new Seed(SeedType.DIAMONDS, 2);
        Seed S1 = new Seed(SeedType.CLUBS, 1);
        Seed S0 = new Seed(SeedType.SPADES, 0);
        for (int i = 0; i<4; i++)
        {
            cards.add(new Carta(1, new Seed(i)));
        }
        cards.add(new Carta(2, S1));
        Collections.sort(cards);
        for (int i = 0; i<5; i++)
        {
            System.out.println(cards.get(i));
        }
        Board B1 = new Board();
        Mano points = B1.evaluateSingle(cards);        
        System.out.println(points);
        cards.clear();        
        System.out.println("");
        cards.add(new Carta(1, S1));
        cards.add(new Carta(6, S2));
        cards.add(new Carta(4, S0));
        cards.add(new Carta(11, S2));
        cards.add(new Carta(3, S3));
        for (int i = 0; i<5; i++)
        {
            System.out.println(cards.get(i));
        }
        points = B1.evaluateSingle(cards);
        System.out.println(points);
        cards.clear();        
        System.out.println("");
        cards.add(new Carta(11, S1));
        cards.add(new Carta(11, S2));
        cards.add(new Carta(11, S3));
        cards.add(new Carta(12, S2));
        cards.add(new Carta(12, S0));
        cards.add(new Carta(12, S1));
        cards.add(new Carta(10, S2));
        points = B1.evaluateFull(cards);
        System.out.println(points);
        System.out.println("");
        System.out.println(points.getDescrizione());
        System.out.println("\nConsegna carte\n");
        
        Board B2 = new Board();
        ArrayList<Carta> carteDate = new ArrayList<>();
        for (int i = 0; i<5; i++){
            carteDate.add(B2.giveCard());
        }
        for(Carta carta: carteDate){
            System.out.println(carta);
        }
        
//        System.out.println("\nStampa tutte le carte");
//        Board B3 = new Board();
//        for(Card carta: B3.getMazzo().getMazzo()){
//            System.out.println(carta);
//        }*/
        
    }

}