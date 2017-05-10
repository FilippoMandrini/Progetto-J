package poker;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> mazzo;
   
    public Deck() {
        this.mazzo = new ArrayList();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                mazzo.add(new Card(j,new Seed(i)));
            }
        }
    }
    
    public boolean restore() {
        this.mazzo.clear();
        for(int i=0; i<4 ; i++){
            for(int j=0; j<13 ; j++){
                mazzo.add(new Card(j,new Seed(i)));
            }
        }
        return true;
    }    
 
    public Card getCard() {
        Random random = new Random();
        Card cartaEstratta = mazzo.get(random.nextInt(mazzo.size()));
        mazzo.remove(cartaEstratta);
        return cartaEstratta;
    }
    
    public void burnCard() {
        Random random = new Random();
        Card cartaEstratta = mazzo.get(random.nextInt(mazzo.size()));
        mazzo.remove(cartaEstratta);
    }

    public ArrayList<Card> getMazzo() {
        return mazzo;
    }

}