package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Doppia coppia"
 * Mano che contiene due coppie di valori diversi.
 */
public class DoppiaCoppia extends Hand{

    private Card firstPair;
    private Card secondPair;
    private Card firstKicker;

    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public DoppiaCoppia(List<Card> cards) {
        super(cards);
        this.firstPair = this.cards.get(0);
        this.secondPair = this.cards.get(2);
        this.firstKicker = this.cards.get(4);
        this.points = 200 + firstPair.getRank() + (double) secondPair.getRank() /100 + (double)firstKicker.getRank() / 10000;

    }

    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        return points;
    }
    
    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString()
    {
        return "Doppia Coppia di " + Card.getRankName(firstPair.getRank()) + " e " + Card.getRankName(secondPair.getRank());
    }
}