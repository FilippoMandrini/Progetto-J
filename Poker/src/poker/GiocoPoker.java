package poker;

import mani.Mano;
import java.util.ArrayList;
import java.util.Collections;

public class GiocoPoker {

    public static void main(String[] args) {

        ArrayList<Carta> carte = new ArrayList<>();
        Seme S3 = new Seme(TipoSeme.CUORI, 3);
        Seme S2 = new Seme(TipoSeme.DENARI, 2);
        Seme S1 = new Seme(TipoSeme.FIORI, 1);
        Seme S0 = new Seme(TipoSeme.PICCHE, 0);
        for (int i = 0; i<4; i++)
        {
            carte.add(new Carta(1, new Seme(i)));
        }
        carte.add(new Carta(2, S1));
        Collections.sort(carte);
        for (int i = 0; i<5; i++)
        {
            System.out.println(carte.get(i));
        }
        Tavolo B1 = new Tavolo();
        Mano points = B1.valutaSingolo(carte);        
        System.out.println(points);
        carte.clear();        
        System.out.println("");
        carte.add(new Carta(1, S1));
        carte.add(new Carta(6, S2));
        carte.add(new Carta(4, S0));
        carte.add(new Carta(11, S2));
        carte.add(new Carta(3, S3));
        for (int i = 0; i<5; i++)
        {
            System.out.println(carte.get(i));
        }
        points = B1.valutaSingolo(carte);
        System.out.println(points);
        carte.clear();        
        System.out.println("");
        carte.add(new Carta(11, S1));
        carte.add(new Carta(11, S2));
        carte.add(new Carta(11, S3));
        carte.add(new Carta(12, S2));
        carte.add(new Carta(12, S0));
        carte.add(new Carta(12, S1));
        carte.add(new Carta(10, S2));
        points = B1.valutaTutto(carte);
        System.out.println(points);
        System.out.println("");
        System.out.println(points.getDescrizione());
        System.out.println("\nConsegna carte\n");
        
        Tavolo B2 = new Tavolo();
        ArrayList<Carta> carteDate = new ArrayList<>();
        for (int i = 0; i<5; i++){
            carteDate.add(B2.daiCarta());
        }
        for(Carta carta: carteDate){
            System.out.println(carta);
        }
        
//        System.out.println("\nStampa tutte le carte");
//        Board B3 = new Board();
//        for(Card carta: B3.getMazzo().getMazzo()){
//            System.out.println(carta);
//        }*/
        
    }

    GiocoPoker(ArrayList<Carta> carteOrdinate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}