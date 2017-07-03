package handtypes;

import java.util.List;
import poker.Card;

/**
 * Mano che rappresenta la combinazione "Scala Colore"
 * Mano formata da una "Scala" le cui carte hanno lo stesso seme
 */
public class ScalaColore extends Hand {
    
    private Card high;
    
    /**
     * Costruttore della classe
     * @param cards la lista delle carte
     */
    public ScalaColore(List<Card> cards) {
        super(cards);
        this.high = this.cards.get(0);
    }
    
    /**
     * Calcola il punteggio della singola mano 
     * @return il numero rappresentante il punteggio
     */
    @Override
    public double getPoints() {
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return 800 + this.cards.get(1).getRank();
        }
        return 800 + this.cards.get(0).getRank();
    }
    
    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString() {
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            return "Scala Colore al " + Card.getRankName(this.cards.get(1).getRank());
        }
        return "Scala Colore al " + Card.getRankName(this.cards.get(0).getRank());
    }
}