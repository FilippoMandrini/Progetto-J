
package mani;

import java.util.ArrayList;
import poker.Carta;


public class Coppia extends Mano {

    private Carta coppia;
    private Carta primoKicker;
    private Carta secondoKicker;
    private Carta terzoKicker;

    public Coppia(ArrayList<Carta> carte) {
        super(carte);
        this.coppia = this.carte.get(0);
        this.secondoKicker = this.carte.get(3);
        this.terzoKicker = this.carte.get(4);
        this.primoKicker = this.carte.get(2);
    }



    @Override
    public double getPunti() {
        return 100 + coppia.getValore() + (double) primoKicker.getValore() / 100. + (double) secondoKicker.getValore() / 10000. + (double) terzoKicker.getValore() / 1000000.;
    }

    @Override
    public String toString()
    {
        return "Coppia di " + Carta.getNomeValore(coppia.getValore());
    }
}
