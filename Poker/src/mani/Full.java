/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mani;

import java.util.ArrayList;
import poker.Carta;

/**
 *
 * @author Nickelsilver
 */
public class Full extends Mano{

    private Carta tris;
    private Carta coppia;

    public Full(ArrayList<Carta> carte) {
        super(carte);
        this.tris = this.carte.get(0);
        this.coppia = this.carte.get(3);
    }
    
    @Override
    public double getPunti() {
        return 600 + tris.getValore() + (double) coppia.getValore() / 100.;
    }

    @Override
    public String toString() {
        return "Full di " + Carta.getNomeValore(tris.getValore()) + " e " + Carta.getNomeValore(coppia.getValore());
    }
}
