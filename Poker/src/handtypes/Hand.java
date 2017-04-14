package handtypes;

public abstract class Hand implements Comparable {
    
    public abstract double getPoints();

    @Override
    public int compareTo(Object o) {
        Hand other =  (Hand)o;
        return (int)(1000000 * (other.getPoints() - this.getPoints()));
    }
    
}
