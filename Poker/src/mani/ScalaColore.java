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
public class ScalaColore extends Mano {
    
    private Carta alta;
    
    public ScalaColore(ArrayList<Carta> carte) {
        super(carte);
        this.alta = this.carte.get(0);
    }
    
    @Override
    public double getPunti() {
        return 800 +alta.getValore();
    }
    
    @Override
    public String toString()
    {
        return "Scala Colore di " + Carta.getNomeValore(alta.getValore());
    }
    
}
