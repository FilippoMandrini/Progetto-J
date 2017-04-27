package poker;

import exceptions.NotEnoughCardsException;
import mani.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Tavolo {
    
    private Mazzo mazzo;
    private ArrayList<GiocatoreUmano> giocatori;
     
    
    @SuppressWarnings("empty-statement")
    public Mano valutaTutto(ArrayList<Carta> carte) throws NotEnoughCardsException
    {
        if (carte.size() != 7)
        {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        ArrayList<Mano> risultati = new ArrayList<>();
        int k = 5;                           
        int indici[] = new int[k];              
        if (k <= carte.size()) 
        {
            for (int i = 0; i <= k - 1; i++)
            {
                indici[i] = i;
            }
            risultati.add(valutaSingolo(getSubset(carte, indici)));
            while(true) 
            {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && indici[i] == carte.size() - k + i; i--);
                if (i < 0) 
                {
                    break;
                }
                indici[i]++;                    
                for (++i; i < k; i++) 
                { 
                    indici[i] = indici[i - 1] + 1;
                }
                risultati.add(valutaSingolo(getSubset(carte, indici)));
            }
        }
        Collections.sort(risultati);
        return risultati.get(0);  
    }


// generate actual subset by index sequence
    ArrayList<Carta> getSubset(List<Carta> input, int[] subset) 
    {
        ArrayList<Carta> allaMano = new ArrayList(subset.length); 
        allaMano.clear(); 
        for (int i = 0; i < subset.length; i++) 
        {
            allaMano.add(input.get(subset[i]));
        }
        return allaMano;
    }

    
    public Mano valutaSingolo(ArrayList<Carta> carte) throws NotEnoughCardsException 
    {
        if (carte.size() != 5)
        {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        Collections.sort(carte);
        ArrayList<Carta> carteOrdinate = new ArrayList<>();
        HashMap<Carta, Integer> mappaCarte = new HashMap<>();
        int secondoMaxIndice = 0;
        int indiceMassimo =0;
        int massimo = 0;
        int[] contatoreCarte = new int[13];
        for (Carta carta : carte) {
            contatoreCarte[carta.getValore()]++;
        }
        for (int i = contatoreCarte.length-1; i >= 0; i--) {
            if (contatoreCarte[i] > massimo) {
                massimo = contatoreCarte[i];
                indiceMassimo = i;
            }
        }
        contatoreCarte[indiceMassimo] = 0;
        int massimoSecondo = 0;
        for (int i = contatoreCarte.length-1; i >= 0; i--) {
            if (contatoreCarte[i] > massimoSecondo) {
                massimoSecondo = contatoreCarte[i];
                secondoMaxIndice = i;
            }
        }        
        for (int i = 0; i<carte.size(); i++) {
            if (carte.get(i).getValore() == indiceMassimo)
            {
                carteOrdinate.add(carte.get(i));
                carte.remove(carte.get(i));
                i--;
            }
        }
        for (int i = 0; i<carte.size(); i++) {
            if (carte.get(i).getValore() == secondoMaxIndice)
            {
                carteOrdinate.add(carte.get(i));
                carte.remove(carte.get(i));
                i--;
            }
        }
        carteOrdinate.addAll(carte);
        carte.clear();
        if (checkColore(carteOrdinate) && checkScala(carteOrdinate))
        {
            return new ScalaColore(carteOrdinate);
        }
        if (massimo == 4)
        {

            return new Poker(carteOrdinate);
        }
        if (massimo == 3) 
        {
            if (massimoSecondo == 2) 
            {
                return new Full(carteOrdinate);
            }
        }
        if (checkColore(carteOrdinate))
        {
            return new Colore(carteOrdinate);
        }
        if (checkScala(carteOrdinate))
        {
            return new Scala(carteOrdinate);
        }
        if (massimo == 3)
        {
            if (massimoSecondo != 2)
            {
                return new Tris(carteOrdinate);
            }
        }
        if (massimo == 2)
        {
            if (massimoSecondo == 2)
            {
                return new DoppiaCoppia(carteOrdinate);
            }
            else
            {
                return new Coppia(carteOrdinate);
            }
        }
        if (massimo == 1)
        {
            return new CartaAlta(carteOrdinate);
        }
        return null; 
    }
        
     
    private boolean checkColore(ArrayList<Carta> carte) {
        int[] contatoreSeme = new int[4];
        for (Carta carta : carte) {
            contatoreSeme[carta.getSeme().getValore()]++;
        }
        for (int i = 0; i < contatoreSeme.length; i++) {
            if (contatoreSeme[i] == 5) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkScala(ArrayList<Carta> cards)
    {
        boolean spy = true;
        for (int i= 0; i<cards.size()-1; i++) 
        {
            if (cards.get(i).getValore()- cards.get(i+1).getValore() != 1 )
            {
                spy = false;
            }
        }
        if (cards.get(0).getValore() == 12 && cards.get(1).getValore() == 3)
        {
            spy = true;
            for (int i = 1; i < cards.size() - 1; i++) {
                if (cards.get(i).getValore() - cards.get(i + 1).getValore() != 1) {
                    spy = false;
                }
            }
        }
        return spy;

    }
    
    public Carta daiCarta(){
        
        return mazzo.estraiCarta();
        
    }
//    public Card giveCard(){
//        Random random = new Random();
//        int seed, value;
//        while(true){
//            seed = random.nextInt(4);
//            value = random.nextInt(13);
//            if(mazzo[seed][value] == 0){
//                mazzo[seed][value] = 1;
//                return new Card(value,new Seed(seed));
//            }
//        }
//    }

    public Tavolo(){
        mazzo = new Mazzo();
        giocatori = new ArrayList();
    }

    public Mazzo getMazzo() {
        return mazzo;
    }

    public ArrayList<GiocatoreUmano> getGiocatori() {
        return giocatori;
    }
}