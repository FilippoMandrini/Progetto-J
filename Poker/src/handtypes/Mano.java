package handtypes;

import java.util.ArrayList;
import poker.Carta;

public abstract class Mano implements Comparable {
    
    protected ArrayList<Carta> cards;
    
    public abstract double getPoints();

    public Mano(ArrayList<Carta> cards) {
        this.cards = new ArrayList<Carta>();
        for (Carta carta : cards)
        {
            this.cards.add(carta);
        }
    }

    
    @Override
    public int compareTo(Object o) {
        Mano other =  (Mano)o;
        return (int)(1000000 * (other.getPoints() - this.getPoints()));
    }

    public ArrayList<Carta> getCards() {
        return cards;
    }
    
    public String getFullDescription()
    {
        String result = "";
        for (Carta carta : cards)
        {
            result += carta.toString() + "\n";
        }
        return result;
    }
        
}
