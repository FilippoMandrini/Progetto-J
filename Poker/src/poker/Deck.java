package poker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe che rappresenta il mazzo
 */
public class Deck {
    
    private ArrayList<Card> mazzo;
   
    /**
     * Costruttore di Deck
     */
    public Deck() {
        this.mazzo = new ArrayList();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                mazzo.add(new Card(j,new Seed(i)));
            }
        }
    }
    
    /**
     * Resetta il mazzo 
     */
    public void restore() {
        this.mazzo.clear();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                mazzo.add(new Card(j,new Seed(i)));
            }
        }
    }    
 
    /**
     * Estrae una carta dal mazzo casualmente
     * @return la carta estratta
     */
    public Card getCard() {
        Random random = new Random();
        Card cartaEstratta = mazzo.get(random.nextInt(mazzo.size()));
        mazzo.remove(cartaEstratta);
        return cartaEstratta;
    }
    
    /**
     * Elimina una carta scelta casualmente dal mazzo
     */
    public void burnCard() {
        Random random = new Random();
        Card cartaEstratta = mazzo.get(random.nextInt(mazzo.size()));
        mazzo.remove(cartaEstratta);
    }

    /**
     * Restituisce il mazzo
     * @return il mazzo
     */
    public ArrayList<Card> getMazzo() {
        return mazzo;
    }

}