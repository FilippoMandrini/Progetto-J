
package mani;

import java.util.ArrayList;
import java.util.Collections;
import poker.Carta;

public class Colore extends Mano{
     

    public Colore(ArrayList<Carta> carte) {
        super(carte);
        Collections.sort(this.carte);
    }
    
    @Override
    public double getPunti() {
        
        double punti = 500;
        double i = 0;
        for (Carta card: this.carte )
        {
            punti = punti + (double)card.getValore() /  (double)Math.pow(100, i);
            i += 1;
        }
        return punti;
    }
    
    @Override
    public String toString()
    {
        return "Colore di " + Carta.getNomeSeme(carte.get(0).getSeme()) + " al " + Carta.getNomeValore(carte.get(0).getValore());
    }


    
}
