package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {
    
    private Deck mazzo;
    private ArrayList<HumanPlayer> giocatori;
    
    public void sortHand()
    {
        
    }
    
    public Hand evaluateSingle(ArrayList<Card> cards) 
    {
        Collections.sort(cards);
        int secondMaxIndex = 0;
        int maxIndex =0;
        int max = 0;
        int[] cardCounter = new int[13];
        for (Card carta : cards) {
            cardCounter[carta.getValue()]++;
        }
        for (int i = 0; i < cardCounter.length; i++) {
            if (cardCounter[i] > max) {
                max = cardCounter[i];
                maxIndex = i;
            }
        }
        cardCounter[maxIndex] = 0;
        int secondMax = 0;
        for (int j = 0; j < cardCounter.length; j++) {
            if (cardCounter[j] > secondMax) {
                secondMax = cardCounter[j];
                secondMaxIndex = j;
            }
        }
        if (checkFlush(cards) && checkStraight(cards))
        {
            return new StraightFlush(cards.get(0));
        }
        if (max == 4)
        {
            ArrayList<Card> tohand = new ArrayList<>();
            int spy = 0;
            for (Card inhand : cards)
            {
                if (inhand.getValue() == maxIndex && spy == 0)
                {
                    tohand.add(inhand);
                    spy = 1;
                }
            }
            spy = 0;
            for (Card inhand : cards)
            {
                if (inhand.getValue() == secondMaxIndex && spy == 0)
                {
                    tohand.add(inhand);
                    spy = 1;
                }
            }
            return new FourOfAKind(tohand.get(0), tohand.get(1));
        }
        if (max == 3) 
        {
            if (secondMax == 2) 
            {
                ArrayList<Card> tohand = new ArrayList<>();
                int spy = 0;
                for (Card inhand : cards) 
                {
                    if (inhand.getValue() == maxIndex && spy == 0) 
                    {
                        tohand.add(inhand);
                        spy = 1;
                    }
                }
                spy = 0;
                for (Card inhand : cards) 
                {
                    if (inhand.getValue() == secondMaxIndex && spy == 0) 
                    {
                        tohand.add(inhand);
                        spy = 1;
                    }
                }
                return new FullHouse(tohand.get(0), tohand.get(1));
            }
        }
        if (checkFlush(cards))
        {
            return new Flush(cards);
        }
        if (checkStraight(cards))
        {
            return new Straight(cards);
        }
        if (max == 3)
        {
            if (secondMax != 2)
            {
                // nuovo punteggio Tris con carta trovata maxindex come tris e carta alta secondMaxIndex
            }
        }
        if (max == 2)
        {
            if (secondMax == 2)
            {
                // nuovo punteggio Doppia coppia con carta trovata a maxindex come tris e seconda carta a secondMaxIndex
            }
            else
            {
                // nuovo punteggio Coppia con carta trovata a maxindex come tris e carta alta a secondMaxIndex
            }
        }
        if (max == 1)
        {
            return new HighCard(cards);
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