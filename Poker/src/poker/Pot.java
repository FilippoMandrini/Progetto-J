package poker;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe che rappresenta il Pot
 */
public class Pot {
    
    private int bet;
    private final Set<Player> members;

    public Pot(int bet) 
    {
        members = new HashSet<>();
        bet = bet;
    }

    public int getBet() {
        return bet;
    }

    public Set<Player> getMembers() {
        return members;
    }

    public void addMember(Player player)
    {
        members.add(player);
    }
    
    public boolean hasMember(Player player)
    {
        return members.contains(player);
    }
            
    public void reset()
    {
        members.clear();
        bet = 0;
    }
    
    
    
    
    
}