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
    
    public int getValue()
    {
        int total = bet * members.size();
        return total;
    }
            
    public void reset()
    {
        members.clear();
        bet = 0;
    }
    
    public Pot getSidePot(int amount, Player player)
    {
        members.add(player);
        Pot sidePot = new Pot(bet - amount);
        this.bet = amount;
        for (Player member : members)
        {
            sidePot.addMember(member);
        }
        return sidePot;
           
    }
    
    
    
}