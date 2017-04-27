package mani;

import java.util.ArrayList;
import poker.Carta;

public abstract class Mano implements Comparable {
    
    protected ArrayList<Carta> carte;
    
    public abstract double getPunti();

    public Mano(ArrayList<Carta> carte) {
        this.carte = new ArrayList<Carta>();
        for (Carta carta : carte)
        {
            this.carte.add(carta);
        }
    }

    
    @Override
    public int compareTo(Object o) {
        Mano other =  (Mano)o;
        return (int)(1000000 * (other.getPunti() - this.getPunti()));
    }

    public ArrayList<Carta> getCarte() {
        return carte;
    }
    
    public String getDescrizione()
    {
        String result = "";
        for (Carta carta : carte)
        {
            result += carta.toString() + "\n";
        }
        return result;
    }
        
}
