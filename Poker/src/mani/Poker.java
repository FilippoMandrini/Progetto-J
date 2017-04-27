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
public class Poker extends Mano{

    private Carta poker;
    private Carta primoKicker;

    public Poker(ArrayList<Carta> carte) {
        super(carte);
        this.poker = carte.get(0);
        this.primoKicker = carte.get(4);
    }

    @Override
    public double getPunti() {
        return 700 + poker.getValore() + (double) primoKicker.getValore()/100;
    }
    
    @Override
    public String toString()
    {
        return "Poker di " + Carta.getNomeValore(poker.getValore());
    }


 
}
