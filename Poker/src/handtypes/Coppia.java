package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Coppia"
 * Due carte dello stesso valore
 */
public class Coppia extends Hand {

    private Card pair;
    private Card firstKicker;
    private Card secondKicker;
    private Card thirdKicker;

    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public Coppia(List<Card> cards) {
        super(cards);
        this.pair = this.cards.get(0);
        this.secondKicker = this.cards.get(3);
        this.thirdKicker = this.cards.get(4);
        this.firstKicker = this.cards.get(2);
    }

    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        return 100 + pair.getRank() + (double) firstKicker.getRank() / 100. + (double) secondKicker.getRank() / 10000. + (double) thirdKicker.getRank() / 1000000.;
    }

    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString()
    {
        return "Coppia di " + Card.getRankName(pair.getRank());
    }
}