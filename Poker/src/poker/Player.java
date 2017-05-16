package poker;

import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Comparable {

    private String name;
    private int stake;
    private ArrayList<Card> cards;
    private Hand currentHand;
    private boolean active;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.active = true;
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
    
    public boolean reset() {
        if (!this.cards.isEmpty())
            return false;
        this.cards.clear();
        this.active = true;
        return true;
    }
    
    public List<Card> getPlayerCards() {
        return this.cards;
    }

    public Hand getCurrent() {
        return currentHand;
    }

    public void setCurrent(Hand current) {
        this.currentHand = current;
    }
    
    public double getHandPoints() {
        return currentHand.getPoints();
    }    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int fold()
    {
        this.active = false;
        return 0;
    }
    
    public int call(int amount)
    {
        this.stake-=amount;
        return amount;
    }
    
    public int check()
    {
        return 0;
    }
    
    public int raise(int amount)
    {
        this.stake-=amount;
        return amount;
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