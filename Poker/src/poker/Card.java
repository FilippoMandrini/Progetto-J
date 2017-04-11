package poker;

import java.util.Objects;

public class Card implements Comparable {
    
    private int value;
    private Seed seed;
    private static final String seedName[] = {"Cuori","Picche","Fiori","Denari"};
    private static final String valueName[] = {"Due","Tre","Quattro","Cinque","Sei",
        "Sette","Otto","Nove","Dieci","Fante","Regina","Re","Asso"};
    

    public int getValue() {
        return value;
    }

    public Seed getSeed() {
        return seed;
    }

    public Card(int value, Seed seed) {
        this.value = value;
        this.seed = seed;
    }

    @Override
    public String toString() {
        return valueName[value] + " di " + seedName[seed.getValue()];
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.seed, other.seed)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object t) {
        
        final Card other = (Card) t;
        return other.getValue()- this.getValue();
    }
    
    
    
    
    
    
    
}
