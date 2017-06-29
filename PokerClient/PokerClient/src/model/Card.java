package model;

import java.util.Objects;

/**
 * Classe rappresentante la carta
 */
public class Card implements Comparable<Card> {

    private int rank;
    private Suit suit;
    private static final String[] SUIT_NAMES = {"Picche", "Fiori", "Denari", "Cuori"};
    private static final String[] RANK_NAMES = {"Due", "Tre", "Quattro", "Cinque", "Sei",
        "Sette", "Otto", "Nove", "Dieci", "Fante", "Regina", "Re", "Asso"};

    /**
     * Costruttore di Card
     * @param rank valore della carta
     * @param suit seme della carta
     */
    public Card(int rank, Suit suit) {
        
        if (rank > 12 || rank < 0)
        {
            throw new IllegalArgumentException("Valore della carta non valido");
        }
        if (suit.getValue() > 3 || suit.getValue() < 0)
        {
            throw new IllegalArgumentException("Seme della carta non valido");
        }
        this.rank = rank;
        this.suit = suit;
    }
  
    /**
     * Ritorna il valore della carta
     * @return valore della carta
     */
    public int getRank() {
        return rank;
    }
    
    /**
     * Ritorna il seme della carta
     * @return seme della carta
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Ritorna il nome del valore della carta
     * @param rank valore della carta
     * @return nome del valore della carta
     */
    public static String getRankName(int rank) {
        return RANK_NAMES[rank];
    }
 
    /**
     * Ritorna il nome del seme della carta
     * @param suit seme della carta
     * @return il nome del seme della carta
     */
    public static String getSuitName(Suit suit) {
        return SUIT_NAMES[suit.getValue()];
    }

    /**
     * Restituisce il nome della carta
     * @return il nome della carta
     */
    @Override
    public String toString() {
        return RANK_NAMES[rank] + " di " + SUIT_NAMES[suit.getValue()];
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.rank != other.rank) {
            return false;
        }
        if (!Objects.equals(this.suit, other.suit)) {
            return false;
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.rank;
        hash = 53 * hash + Objects.hashCode(this.suit);
        return hash;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(Card t) {
        return t.getRank() - this.getRank();
    }

}