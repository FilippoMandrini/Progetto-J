package handtypes;

import exceptions.WrongCardNumberException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import poker.Card;

/**
 * Classe astratta della mano di carte
 */
public abstract class Hand implements Comparable {
    
    protected List<Card> cards;
    protected double points;
    
    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    public abstract double getPoints();

    /**
     * Costruttore della mano
     * @param cards la lista di carte
     */
    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>();
        try 
        {
            if (cards.size() != 5)
                throw new WrongCardNumberException("Max 5 carte");
            for (Card carta : cards) {
                if (carta == null)
                    throw new WrongCardNumberException("carta nulla");
                this.cards.add(carta);
            }
        }
        catch (NullPointerException ex)
        {
            throw new WrongCardNumberException("Cards è null");
        }
    }
    
    /**
     * Override di compareTo(Object o) per poter poi ricavare la mano migliore possibile
     */
    @Override
    public int compareTo(Object o) {
        Hand other =  (Hand)o;
        return (int)(1000000 * (other.getPoints() - this.getPoints()));
    }

    /**
     * Ritorna le carte della mano
     * @return le carte della mano
     */
    public List<Card> getCards() {
        return cards;
    }
    
    /**
     * Stampa la descrizione delle carte nella mano
     * @return la stringa descrittiva delle carte nella mano
     */
    public String getFullDescription(){
        String result = "";
        for (Card carta : cards)
        {
            result += carta.toString() + "\n";
        }
        return result;
    }       

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.points) ^ (Double.doubleToLongBits(this.points) >>> 32));
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
        final Hand other = (Hand) obj;
        if (Double.doubleToLongBits(this.points) != Double.doubleToLongBits(other.points)) {
            return false;
        }
        return true;
    }
    
    
}