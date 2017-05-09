
package poker;

import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    
    private String name;
    private int stake;
    private ArrayList<Card> cards;
    private Hand current;
    
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStake() {
        return stake;
    }
    
    public boolean addCard(Card card)
    {
        this.cards.add(card);
        return this.cards.contains(card);
    }
    
    public boolean clearCards()
    {
        if (!this.cards.isEmpty())
            return false;
        this.cards.clear();
        return true;
    }
    
    public List<Card> getPlayerCards()
    {
        return this.cards;
    }

    public Hand getCurrent() {
        return current;
    }

    public void setCurrent(Hand current) {
        this.current = current;
    }
    
    public double getHandPoints()
    {
        return current.getPoints();
    }
}