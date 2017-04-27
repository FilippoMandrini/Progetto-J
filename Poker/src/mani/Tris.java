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
public class Tris extends Mano {

    private Carta tris;
    private Carta primoKicker;
    private Carta secondoKicker;

    public Tris(ArrayList<Carta> carte) {
        super(carte);
        this.tris = this.carte.get(0);
        this.primoKicker = this.carte.get(3);
        this.secondoKicker = this.carte.get(4);

    }

    @Override
    public double getPunti() {
        return 300 + tris.getValore() + (double) primoKicker.getValore() / 100 + (double) secondoKicker.getValore() / 10000;
    }
    
    @Override
    public String toString()
    {
        return "Tris di " + Carta.getNomeValore(tris.getValore());
    }
    
}
