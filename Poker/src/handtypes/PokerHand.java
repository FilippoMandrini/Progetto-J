package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Poker"
 * Mano formata da quattro carte dello stesso valore
 */
public class PokerHand extends Hand{

    private Card poker;
    private Card firstKicker;

    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public PokerHand(List<Card> cards) {
        super(cards);
        this.poker = cards.get(0);
        this.firstKicker = cards.get(4);
        this.points = 700 + poker.getRank() + (double) firstKicker.getRank()/100;

    }

    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        return this.points;
    }
    
    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString()
    {
        return "Poker di " + Card.getRankName(poker.getRank());
    }
}