package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Board {
    
    private Deck mazzo;
    private ArrayList<HumanPlayer> giocatori;
    
    public Hand evaluateSingle(ArrayList<Card> cards) 
    {
        Collections.sort(cards);
        ArrayList<Card> sortedCards = new ArrayList<>();
        HashMap<Card, Integer> cardMap = new HashMap<>();
        int secondMaxIndex = 0;
        int maxIndex =0;
        int max = 0;
        int[] cardCounter = new int[13];
        for (Card carta : cards) {
            cardCounter[carta.getValue()]++;
        }
        for (int i = cardCounter.length-1; i >= 0; i--) {
            if (cardCounter[i] > max) {
                max = cardCounter[i];
                maxIndex = i;
            }
        }
        cardCounter[maxIndex] = 0;
        int secondMax = 0;
        for (int i = cardCounter.length-1; i >= 0; i--) {
            if (cardCounter[i] > secondMax) {
                secondMax = cardCounter[i];
                secondMaxIndex = i;
            }
        }        
        for (int i = 0; i<cards.size(); i++) {
            if (cards.get(i).getValue() == maxIndex)
            {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        for (int i = 0; i<cards.size(); i++) {
            if (cards.get(i).getValue() == secondMaxIndex)
            {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        sortedCards.addAll(cards);
        cards.clear();
        if (checkFlush(sortedCards) && checkStraight(sortedCards))
        {
            return new StraightFlush(sortedCards);
        }
        if (max == 4)
        {

            return new FourOfAKind(sortedCards);
        }
        if (max == 3) 
        {
            if (secondMax == 2) 
            {
                return new FullHouse(sortedCards);
            }
        }
        if (checkFlush(sortedCards))
        {
            return new Flush(sortedCards);
        }
        if (checkStraight(sortedCards))
        {
            return new Straight(sortedCards);
        }
        if (max == 3)
        {
            if (secondMax != 2)
            {
                return new ThreeOfAKind(sortedCards);
            }
        }
        if (max == 2)
        {
            if (secondMax == 2)
            {
                return new TwoPair(sortedCards);
            }
            else
            {
                return new OnePair(sortedCards);
            }
        }
        if (max == 1)
        {
            return new HighCard(sortedCards);
        }
        return null; 
    }
        
     
    private boolean checkFlush(ArrayList<Card> cards) {
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
    
    private boolean checkStraight(ArrayList<Card> cards)
    {
        boolean spy = true;
        for (int i= 0; i<cards.size()-1; i++) 
        {
            if (cards.get(i).getValue()- cards.get(i+1).getValue() != 1 )
            {
                spy = false;
            }
        }
        if (cards.get(0).getValue() == 12 && cards.get(1).getValue() == 3)
        {
            spy = true;
            for (int i = 1; i < cards.size() - 1; i++) {
                if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                    spy = false;
                }
            }
        }
        return spy;

    }
    
    public Card giveCard(){
        
        return mazzo.chooseCard();
        
    }
//    public Card giveCard(){
//        Random random = new Random();
//        int seed, value;
//        while(true){
//            seed = random.nextInt(4);
//            value = random.nextInt(13);
//            if(mazzo[seed][value] == 0){
//                mazzo[seed][value] = 1;
//                return new Card(value,new Seed(seed));
//            }
//        }
//    }

    public Board(){
        mazzo = new Deck();
        giocatori = new ArrayList();
    }

    public Deck getMazzo() {
        return mazzo;
    }

    public ArrayList<HumanPlayer> getGiocatori() {
        return giocatori;
    }
}