package poker;

import static poker.SeedType.*;

public class Seed {
    
    private SeedType seed;
    private int value;

    
    public SeedType getSeed() {
        return seed;
    }

    public int getValue() {
        return value;
    }

    public Seed(SeedType seed, int value) {
        this.seed = seed;
        this.value = value;
    }

    public Seed(int value) {
        this.seed = seedConvert(value);
        this.value = value;
    }
    
    private SeedType seedConvert(int number){
        switch (number){
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
