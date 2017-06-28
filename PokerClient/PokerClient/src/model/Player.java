/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import actions.Action;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Nickelsilver
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

    public Player(String name, int stake) {
        this.currentBet = 0;
        this.name = name;
        this.stake = stake;
        this.cards = new ArrayList();
        this.hasCards = false;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player: " + name + " | stake: " + stake;
    }

    public String getName() {
        return name;
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
    }
    
    public boolean hasCards()
    {
        return hasCards;
    }    

    public int getCurrentBet() {
        return currentBet;
    }
    
    
    
    
    
    
    

}
