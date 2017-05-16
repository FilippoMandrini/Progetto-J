package poker;

import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Comparable {

    private String name;
    private int stake;
    private ArrayList<Card> cards;
    private Hand current;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }
    
    public boolean addCard(Card card) {
        this.cards.add(card);
        return this.cards.contains(card);
    }
    
    public boolean clearCards() {
        if (!this.cards.isEmpty())
            return false;
        this.cards.clear();
        return true;
    }
    
    public List<Card> getPlayerCards() {
        return this.cards;
    }

    public Hand getCurrent() {
        return current;
    }

    public void setCurrent(Hand current) {
        this.current = current;
    }
    
    public double getHandPoints() {
        return current.getPoints();
    }    
    
    @Override
    public int compareTo(Object t) {
        final Player other = (Player) t;
        double diff = other.getHandPoints() - this.getHandPoints();
        if(diff>0)
            return 1;
        if(diff==0)
            return 0;
        if(diff<0)
            return -1;
        
        return 0;
    }
    
}