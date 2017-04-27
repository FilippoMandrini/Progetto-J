/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mani;

import java.util.ArrayList;
import java.util.Collections;
import poker.Carta;

/**
 *
 * @author Nickelsilver
 */
public class CartaAlta extends Mano{


    public CartaAlta(ArrayList<Carta> carte) {
        super(carte);
        Collections.sort(carte);
    }
    
    @Override
    public double getPunti() {
        
        double points = 0;
        double i = 1;
        for (Carta carta: this.carte )
        {
            points = points + (double)carta.getValore() / (double)Math.pow(100, i);
            i += 1;
        }
        return points;
    }
    
    @Override
    public String toString()
    {
        return "Carta Alta: " + Carta.getNomeValore(carte.get(0).getValore());
    }
}
