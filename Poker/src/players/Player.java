package players;

import actions.Action;
import actions.Fold;
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
    protected int id;

    /**
     * Costruttore di Player
     * @param name nome del giocatore
     * @param client il client del giocatore
     */
    public Player(String name, Client client) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.active = true;
        this.client = client;
    }

    /**
     * Costruttore di Player
     * @param name nome del giocatore
     * @param stake lo stake di partenza del giocatore
     * @param client il client del giocatore
     */
    public Player(String name, int stake, Client client) {
        this.name = name;
        this.stake = stake;
        this.client = client;
        this.cards = new ArrayList<>();

    }
    
    /**
     * Costruttore di Player
     * @param name nome del giocatore
     * @param stake lo stake di partenza del giocatore
     */
    protected Player(String name, int stake){
        this.name = name;
        this.stake = stake;
        this.client = null;
        this.cards = new ArrayList<>();
    }

    /**
     * Ritorna l'ID del giocatore
     * @return l'ID del giocatore
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'ID del giocatore
     * @param id  l'ID del giocatore
     */
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
    
    /**
     * Restituisce il client associato al giocatore
     * @return il client associato al giocatore
     */
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

    /**
     * Imposta l'ultima azione compiuta
     * @param lastAction l'ultima azione compiuta
     */
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * Restituisce l'ultima azione compiuta
     * @return l'ultima azione compiuta
     */
    public Action getLastAction() {
        return lastAction;
    }

    /** {@inheritDoc} */      
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.id;
        return hash;
    }

    /** {@inheritDoc} */      
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
    
    /**
     * Paga la quota della scommessa richiesta per giocare
     * @param amount la quota della scommessa
     * @throws IllegalArgumentException se lo stake e' insufficiente
     */
    public void pay(int amount)
    {
        if (amount > stake)
        {
            throw new IllegalArgumentException("Stake insufficiente per coprire puntata");
        }
        this.stake -= amount;
    }
    
    /**
     * Consegna la somma vinta al giocatore
     * @param amount la somma vinta
     */
    public void win(int amount)
    {
        stake += amount;
    }

    /**
     * Imposta la quantita' scommessa corrente
     * @param bet la quantita' scommessa
     */
    public void setCurrentBet(int bet) {
        this.currentBet = bet;
    }

    /**
     * Restituisce la quantita' scommessa corrente
     * @return la quantita' scommessa corrente
     */
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
    
    /**
     * Resetta il giocatore
     * @return true se resetta, altrimenti false
     */
    public boolean reset() 
    {
        this.cards.clear();
        this.active = true;
        this.lastAction = null;
        return true;
    }
    
    /**
     * Restituisce una copia del giocatore senza informazioni sensibili
     * @return una copia del giocatore
     */
    public ShadowPlayer getShadowCopy()
    {
        ShadowPlayer copy = new ShadowPlayer(name, stake, id);
        copy.setHasCards(!this.cards.isEmpty());
        copy.setCurrentBet(currentBet);
        copy.setLastAction(lastAction);
        copy.setActive(active);
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
    
    /** {@inheritDoc} */      
    @Override
    public String toString() {
        return "Giocatore: " + name;
    }
    
    /**
     * Folda le carte del giocatore
     */
    public void foldCards()
    {
        this.cards.clear();
        setActive(false);
        lastAction = new Fold();
    }  

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean hasCards()
    {
        return !this.cards.isEmpty();
    }
    
    
     
}