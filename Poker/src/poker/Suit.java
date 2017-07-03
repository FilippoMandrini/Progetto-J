package poker;

import java.util.Objects;
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
        this.seed = getSuitType(value);
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
    public static SuitType getSuitType(int value){
        switch (value){
            case 0:
                return SPADES;
            case 1:
                return CLUBS;
            case 2:
                return DIAMONDS;
            case 3:
                return HEARTS;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.seed);
        hash = 53 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suit other = (Suit) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.seed != other.seed) {
            return false;
        }
        return true;
    }
    
    
    
}
