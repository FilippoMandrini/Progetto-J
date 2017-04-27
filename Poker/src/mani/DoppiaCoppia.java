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
public class DoppiaCoppia extends Mano{

    private Carta primaCoppia;
    private Carta secondaCoppia;
    private Carta primoKicker;

    public DoppiaCoppia(ArrayList<Carta> carte) {
        super(carte);
        this.primaCoppia = this.carte.get(0);
        this.secondaCoppia = this.carte.get(2);
        this.primoKicker = this.carte.get(4);

    }

    @Override
    public double getPunti() {
        return 200 + primaCoppia.getValore() + (double) secondaCoppia.getValore() /100 + (double)primoKicker.getValore() / 10000;
    }
    
    @Override
    public String toString()
    {
        return "Doppia Coppia di " + Carta.getNomeValore(primaCoppia.getValore()) + " e " + Carta.getNomeValore(secondaCoppia.getValore());
    }
}
