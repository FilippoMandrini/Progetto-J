package poker;

public class Card {
    
    private int value;
    private Seed seed;

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
    
    private String seedNameConvert(){
        switch (seed.getValue()){
            case 0:
                return "Cuori";
            case 1:
                return "Picche";
            case 2:
                return "Fiori";
            case 3:
                return "Denari";
        }
        return null;
    }

    private String valueNameConvert(){
        switch (value){
            case 1:
                return "Due";
            case 2:
                return "Tre";
            case 3:
                return "Quattro";
            case 4:
                return "Cinque";
            case 5:
                return "Sei";
            case 6:
                return "Sette";
            case 7:
                return "Otto";
            case 8:
                return "Nove";
            case 9:
                return "Dieci";
            case 10:
                return "Fante";
            case 11:
                return "Regina";
            case 12:
                return "Re";
            case 13:
                return "Asso";
        }
        return null;
    }
    @Override
    public String toString() {
        return valueNameConvert() + " di " + seedNameConvert();
    }
    
}
