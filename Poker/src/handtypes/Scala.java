package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Scala"
 * Mano formata da cinque carte con ordine consecutivo di valore
 */
public class Scala extends Hand {

    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public Scala(List<Card> cards) {
        super(cards);
    }

    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return 400 + this.cards.get(1).getRank();
        }
        return 400 + this.cards.get(0).getRank();
    }

    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString() {
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return "Scala al " + Card.getRankName(this.cards.get(1).getRank());
        }
        return "Scala al " + Card.getRankName(this.cards.get(0).getRank());
    }
}
