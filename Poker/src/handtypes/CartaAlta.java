package handtypes;

import java.util.Collections;
import java.util.List;
import poker.Card;

/**
 * Mano in cui non si hanno combinazioni e si considera solo la carta 
 * di valore maggiore
 */
public class CartaAlta extends Hand{

    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public CartaAlta(List<Card> cards) {
        super(cards);
        Collections.sort(cards);
        double pts = 0;
        double i = 1;
        for (Card card: this.cards)
        {
            pts = pts + (double)card.getRank() / (double)Math.pow(100, i);
            i += 1;
        }
        this.points = pts;
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
        return "Carta Alta: " + Card.getRankName(cards.get(0).getRank());
    }
}