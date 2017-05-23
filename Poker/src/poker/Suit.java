package poker;

import static poker.SuitType.*;

/**
 * Classe che rappresenta il seme della carta
 */
public class Suit {
    
    private SuitType seed;
    private int value;

    /**
     * Costruttore di Seed
     * @param seed il seme della carta
     * @param value il valore simbolico del seme
     */
    public Suit(SuitType seed, int value) {
        this.seed = seed;
        this.value = value;
    }

    /**
     * Costruttore di Seed
     * @param value il valore simbolico del seme
     */
    public Suit(int value) {
        this.seed = seedConvert(value);
        this.value = value;
    }

    /**
     * Restituisce il valore simbolico del seme
     * @return il valore simbolico del seme
     */
    public int getValue() {
        return value;
    }
   
    /**
     * Restituisce il seme della carta
     * @return il seme della carta 
     */
    public SuitType getSeed() {
        return seed;
    }
    
    /**
     * Converte il valore simbolico del seme nel relativo seme come enumerazione
     * @param value valore simbolico del seme
     * @return il seme come enumerazione
     */
    private SuitType seedConvert(int value){
        switch (value){
            case 0:
                return HEARTS;
            case 1:
                return SPADES;
            case 2:
                return CLUBS;
            case 3:
                return DIAMONDS;
        }
        return null;
    }
    
}
