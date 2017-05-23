package poker;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Classe che rappresenta il deck
 */
public class Deck {
    
    private ArrayList<Card> deck;
   
    /**
     * Costruttore di Deck
     */
    public Deck() {
        this.deck = new ArrayList();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                deck.add(new Card(j,new Suit(i)));
            }
        }
    }
    
    /**
     * Resetta il deck 
     */
    public void restore() {
        this.deck.clear();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                deck.add(new Card(j,new Suit(i)));
            }
        }
    }    
 
    /**
     * Estrae una carta dal deck casualmente
     * @return la carta estratta
     */
    public Card dealCard() {
        SecureRandom random = new SecureRandom();
        Card cartaEstratta = deck.get(random.nextInt(deck.size()));
        deck.remove(cartaEstratta);
        return cartaEstratta;
    }
    
    /**
     * Elimina una carta scelta casualmente dal deck
     */
    public void burnCard() {
        SecureRandom random = new SecureRandom();
        Card cartaEstratta = deck.get(random.nextInt(deck.size()));
        deck.remove(cartaEstratta);
    }

    /**
     * Restituisce il deck
     * @return il deck
     */
    public ArrayList<Card> getMazzo() {
        return deck;
    }

}