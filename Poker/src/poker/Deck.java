package poker;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> mazzo;
    private int carteRimaste;
    
    
    public Deck() {
        this.carteRimaste = 52;
        this.mazzo = new ArrayList();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                mazzo.add(new Card(j,new Seed(i)));
            }
        }
    }
    
    public Card chooseCard() {
        Random random = new Random();
        Card cartaEstratta = mazzo.get(random.nextInt(carteRimaste));
        mazzo.remove(cartaEstratta);
        carteRimaste--;
        return cartaEstratta;
    }

    public ArrayList<Card> getMazzo() {
        return mazzo;
    }

    public int getCarteRimaste() {
        return carteRimaste;
    }

}