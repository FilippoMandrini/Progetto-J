package poker;

import mani.Colore;
import mani.Scala;
import mani.Mano;
import mani.ScalaColore;
import mani.Coppia;
import mani.DoppiaCoppia;
import mani.CartaAlta;
import mani.Tris;
import mani.Full;
import mani.Poker;
import exceptions.NotEnoughCardsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Board {
    
    private Deck mazzo;
    private ArrayList<HumanPlayer> giocatori;
     
    
    @SuppressWarnings("empty-statement")
    public Mano evaluateFull(ArrayList<Carta> cards) throws NotEnoughCardsException
    {
        if (cards.size() != 7)
        {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        ArrayList<Mano> results = new ArrayList<>();
        int k = 5;                           
        int indices[] = new int[k];              
        if (k <= cards.size()) 
        {
            for (int i = 0; i <= k - 1; i++)
            {
                indices[i] = i;
            }
            results.add(evaluateSingle(getSubset(cards, indices)));
            while(true) 
            {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && indices[i] == cards.size() - k + i; i--);
                if (i < 0) 
                {
                    break;
                }
                indices[i]++;                    
                for (++i; i < k; i++) 
                { 
                    indices[i] = indices[i - 1] + 1;
                }
                results.add(evaluateSingle(getSubset(cards, indices)));
            }
        }
        Collections.sort(results);
        return results.get(0);  
    }


// generate actual subset by index sequence
    ArrayList<Carta> getSubset(List<Carta> input, int[] subset) 
    {
        ArrayList<Carta> toHand = new ArrayList(subset.length); 
        toHand.clear(); 
        for (int i = 0; i < subset.length; i++) 
        {
            toHand.add(input.get(subset[i]));
        }
        return toHand;
    }

    
    public Mano evaluateSingle(ArrayList<Carta> cards) throws NotEnoughCardsException 
    {
        if (cards.size() != 5)
        {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        Collections.sort(cards);
        ArrayList<Carta> sortedCards = new ArrayList<>();
        HashMap<Carta, Integer> cardMap = new HashMap<>();
        int secondMaxIndex = 0;
        int maxIndex =0;
        int max = 0;
        int[] cardCounter = new int[13];
        for (Carta carta : cards) {
            cardCounter[carta.getValore()]++;
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
            if (cards.get(i).getValore() == maxIndex)
            {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        for (int i = 0; i<cards.size(); i++) {
            if (cards.get(i).getValore() == secondMaxIndex)
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
            return new ScalaColore(sortedCards);
        }
        if (max == 4)
        {

            return new Poker(sortedCards);
        }
        if (max == 3) 
        {
            if (secondMax == 2) 
            {
                return new Full(sortedCards);
            }
        }
        if (checkFlush(sortedCards))
        {
            return new Colore(sortedCards);
        }
        if (checkStraight(sortedCards))
        {
            return new Scala(sortedCards);
        }
        if (max == 3)
        {
            if (secondMax != 2)
            {
                return new Tris(sortedCards);
            }
        }
        if (max == 2)
        {
            if (secondMax == 2)
            {
                return new DoppiaCoppia(sortedCards);
            }
            else
            {
                return new Coppia(sortedCards);
            }
        }
        if (max == 1)
        {
            return new CartaAlta(sortedCards);
        }
        return null; 
    }
        
     
    private boolean checkFlush(ArrayList<Carta> cards) {
        int[] seedCounter = new int[4];
        for (Carta carta : cards) {
            seedCounter[carta.getSeme().getValue()]++;
        }
        for (int i = 0; i < seedCounter.length; i++) {
            if (seedCounter[i] == 5) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkStraight(ArrayList<Carta> cards)
    {
        boolean spy = true;
        for (int i= 0; i<cards.size()-1; i++) 
        {
            if (cards.get(i).getValore()- cards.get(i+1).getValore() != 1 )
            {
                spy = false;
            }
        }
        if (cards.get(0).getValore() == 12 && cards.get(1).getValore() == 3)
        {
            spy = true;
            for (int i = 1; i < cards.size() - 1; i++) {
                if (cards.get(i).getValore() - cards.get(i + 1).getValore() != 1) {
                    spy = false;
                }
            }
        }
        return spy;

    }
    
    public Carta giveCard(){
        
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