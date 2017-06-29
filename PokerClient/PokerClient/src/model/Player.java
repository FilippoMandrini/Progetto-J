package model;

import actions.Action;
import java.util.ArrayList;

/**
 * Classe che rappresenta il giocatore
 */
public class Player {

    protected String name;
    private int stake;
    private ArrayList<Card> cards;
    private boolean active;
    private Action lastAction; 
    protected int id = 0;
    private boolean hasCards;
    private int currentBet;

    /**
     * Costruttore di Player
     * @param name il nome del giocatore
     * @param stake lo stake di partenza
     */
    public Player(String name, int stake) {
        this.currentBet = 0;
        this.name = name;
        this.stake = stake;
        this.cards = new ArrayList();
        this.hasCards = false;
    }

    /**
     * Restituisce lo stake del giocatore
     * @return lo stake del giocatore
     */
    public int getStake() {
        return stake;
    }

    /**
     * Imposta la quantita' scommessa
     * @param currentBet la quantita' scommessa
     */
    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    /**
     * Imposta lo stake del giocatore
     * @param stake lo stake di partenza del giocatore
     */
    public void setStake(int stake) {
        this.stake = stake;
    }

    /**
     * Restituisce le carte del giocatore
     * @return le carte del giocatore
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Imposta le carte del giocatore
     * @param cards le carte del giocatore
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Controlla se il giocatore è attivo o no
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

    /**
     * Restituisce l'ultima azione compiuta
     * @return l'ultima azione compiuta
     */
    public Action getLastAction() {
        return lastAction;
    }

    /**
     * Imposta l'ultima azione compiuta
     * @param lastAction l'ultima azione compiuta
     */
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
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
     * @param id l'ID del giocatore
     */
    public void setId(int id) {
        this.id = id;
    }

    /** {@inheritDoc} */ 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        return true;
    }

    /**
     * Ritorna una stringa con il nome del giocatore e relativo stake
     * @return una stringa con nome e stake del giocatore
     */
    @Override
    public String toString() {
        return "Player: " + name + " | stake: " + stake;
    }

    /**
     * Restituisce il nome del giocatore
     * @return il nome del giocatore
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta se il giocatore ha carte o no
     * @param hasCards true se ha carte, false altrimenti
     */
    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
    }
    
    /**
     * Ritorna un valore booleano che indica se il giocatore ha le carte
     * @return true se ha le carte, false altrimenti
     */
    public boolean hasCards(){
        return hasCards;
    }    

    /**
     * Restituisce la quantita' scommessa
     * @return la quantita' scommessa
     */
    public int getCurrentBet() {
        return currentBet;
    }

}