package poker;

import actions.Action;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe astratta che rappresenta un generico giocatore
 */
public abstract class Player implements Comparable {

    private String name;
    private int stake;
    private ArrayList<Card> cards;
    private Hand currentHand;
    private boolean active;
    private int currentBet;
    private Action lastAction; 

    /**
     * Costruttore di Player
     * @param name nome del giocatore
     */
    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.active = true;
    }

    public Player(String name, int stake) {
        this.name = name;
        this.stake = stake;
    }
    
    

    /**
     * Restituisce il nome del giocatore
     * @return nome del giocatore
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce lo stake del giocatore
     * @return lo stake del giocatore
     */
    public int getStake() {
        return stake;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    
    
    /**
     * Imposta lo stake del giocatore
     * @param stake lo stake di partenza del giocatore
     */
    public void setStake(int stake) {
        this.stake = stake;
    }
    
    public void pay(int amount)
    {
        if (amount > stake)
        {
            throw new IllegalArgumentException("Stake insufficiente per coprire puntata");
        }
        this.stake -= amount;
    }
    
    public void win(int amount)
    {
        stake += amount;
    }

    public void setCurrentBet(int bet) {
        this.currentBet = bet;
    }

    public int getCurrentBet() {
        return currentBet;
    }
    
    /**
     * Dà una carta al giocatore
     * @param card la carta del giocatore
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    /**
     * Resetta le carte del giocatore e reinizializza il giocatore come attivo
     * @return true se aveva delle carte da resettare, altrimenti false
     */
    public boolean reset() {
        if (!this.cards.isEmpty())
            return false;
        this.cards.clear();
        this.active = true;
        this.lastAction = null;
        return true;
    }
    
    /**
     * Restituisce le carte del giocatore
     * @return le carte del giocatore
     */
    public List<Card> getPlayerCards() {
        return this.cards;
    }

    /**
     * Restituisce la mano migliore del giocatore
     * @return la mano migliore
     */
    public Hand getCurrentHand() {
        return currentHand;
    }

    /**
     * Imposta la mano migliore del giocatore
     * @param current la mano migliore del giocatore
     */
    public void setCurrentHand(Hand current) {
        this.currentHand = current;
    }
    
    /**
     * Restituisce il punteggio della mano del giocatore
     * @return il punteggio della mano del giocatore
     */
    public double getHandPoints() {
        return currentHand.getPoints();
    }    

    /**
     * Controlla se giocatore attivo o no
     * @return true se attivo, false altrimenti
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Imposta il giocatore come attivo o non attivo
     * @param active true se attivo, false altrimenti
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /** {@inheritDoc} */
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
    
    public void foldCards()
    {
        this.cards = null;
        setActive(false);
    }
    
     
}