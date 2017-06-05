package players;

import actions.Action;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import poker.Card;
import annotations.JSONExclude;

/**
 * Classe astratta che rappresenta un generico giocatore
 */
public class Player implements Comparable {

    protected String name;
    private int stake;
    private ArrayList<Card> cards;
    @JSONExclude
    private Hand currentHand;
    private boolean active;
    private int currentBet;
    private Action lastAction; 
    @JSONExclude
    protected final Client client;
    protected int id = 0;

    /**
     * Costruttore di Player
     * @param name nome del giocatore
     * @param client
     */
    public Player(String name, Client client) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.active = true;
        this.client = client;
    }

    public Player(String name, int stake, Client client) {
        this.name = name;
        this.stake = stake;
        this.client = client;
        this.cards = new ArrayList<>();

    }
    
    protected Player(String name, int stake){
        this.name = name;
        this.stake = stake;
        this.client = null;
        this.cards = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del giocatore
     * @return nome del giocatore
     */
    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }
    /**
     * Restituisce lo stake del giocatore
     * @return lo stake del giocatore
     */
    public int getStake()  {
        return stake;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    public Action getLastAction() {
        return lastAction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
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
    public void addCard(Card card) 
    {
        this.cards.add(card);
    }
    
    public boolean reset() 
    {
        this.cards.clear();
        this.active = true;
        this.lastAction = null;
        return true;
    }
    
    public ShadowPlayer getShadowCopy()
    {
        ShadowPlayer copy = new ShadowPlayer(name, stake);
        copy.setHasCards(!this.cards.isEmpty());
        copy.setCurrentBet(currentBet);
        copy.setLastAction(lastAction);
        return copy;
    }
    /**
     * Restituisce le carte del giocatore
     * @return le carte del giocatore
     */
    public List<Card> getCards() {
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
    
    @Override
    public String toString() {
        return "Giocatore: " + name;
    }
    
    public void foldCards()
    {
        this.cards.clear();
        setActive(false);
    }
    
     
}