package poker;

import players.Player;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe che rappresenta il Pot
 */
public class Pot {
    
    private int bet;
    private final Set<Player> members;
  
    /**
     * Costruttore di Pot 
     * @param bet valore della scommessa
     */
    public Pot(int bet) {
        members = new HashSet<>();
        this.bet = bet;
    }
    
    /**
     * Restituisce il valore della scommessa    
     * @return il valore della scommessa
     */
    public int getBet() {
        return bet;
    }
    
    /**
     * Restituisce la lista dei giocatori
     * @return la lista dei giocatori
     */
    public Set<Player> getMembers() {
        return members;
    }
    
    /**
     * Aggiunge un contribuente al pot
     * @param player il giocatore che contribuisce
     */
    public void addMember(Player player) {
        members.add(player);
    }
    
    /**
     * Ritorna se e' o no presente un contribuente al pot
     * @param player il giocatore
     * @return true se ha contribuito, false altrimenti
     */
    public boolean hasMember(Player player) {
        return members.contains(player);
    }
    
    /**
     * Restituisce il valore complessivo di un pot singolo
     * @return  il valore complessivo del singolo pot
     */
    public int getValue() {
        int total = bet * members.size();
        return total;
    }
            
    /**
     * Resetta il pot
     */
    public void reset() {
        members.clear();
        bet = 0;
    }
    
    /**
     * Restituisce un side pot se il bet minimo e' maggiore dell'ammontare del giocatore
     * @param amount lo stake del giocatore
     * @param player il giocatore
     * @return il side pot corrispondente a quell'ammontare
     */
    public Pot getSidePot(int amount, Player player) {
        Pot sidePot = new Pot(bet - amount);
        this.bet = amount;
        for (Player member : members)
        {
            sidePot.addMember(member);
        }
        members.add(player);
        return sidePot;     
    }
    
}