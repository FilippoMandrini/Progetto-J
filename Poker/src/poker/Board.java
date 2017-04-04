package poker;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int[][] mazzo = new int[4][13];
    private ArrayList<Player> giocatori = new ArrayList();
    

    public Points evaluateSingle(ArrayList<Card> cards) 
    {
        if (checkColor(cards)) {    // Colore
            System.out.println("COLORE");
            Points points = new Points(6, cards.get(0).getSeed().getValue());
            return points;
        }
        
        int[] cardCounter = new int[13];    // Poker
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
    
    public Card giveCard(){
        Random random = new Random();
        int seed, value;
        while(true){
            seed = random.nextInt(4);
            value = random.nextInt(13);
            if(mazzo[seed][value] == 0){
                mazzo[seed][value] = 1;
                return new Card(value+1,new Seed(seed));
            }
        }
    }
}
