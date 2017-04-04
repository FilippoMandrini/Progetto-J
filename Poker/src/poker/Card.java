package poker;

public class Card {
    
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
    
}
