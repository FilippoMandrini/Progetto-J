package handtypes;

import java.util.ArrayList;
import poker.Card;

public abstract class Hand implements Comparable {
    
    protected ArrayList<Card> cards;
    
    public abstract double getPoints();

    public Hand(ArrayList<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card carta : cards)
        {
            this.cards.add(carta);
        }
    }

    
    @Override
    public int compareTo(Object o) {
        Hand other =  (Hand)o;
        return (int)(1000000 * (other.getPoints() - this.getPoints()));
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public String getFullDescription()
    {
        String result = "";
        for (Card carta : cards)
        {
            result += carta.toString() + "\n";
        }
        return result;
    }       
}