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
public class Scala extends Mano {

    public Scala(ArrayList<Carta> carte) {
        super(carte);
    }
    
    @Override
    public double getPunti() {
        return 400 + this.carte.get(0).getValore();
    }

    @Override
    public String toString()
    {
        return "Scala al " + Carta.getNomeValore(this.carte.get(0).getValore());
    }
}
