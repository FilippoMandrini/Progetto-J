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
        return 800 + high.getRank();
    }
    
    /**
     * Ritorna la descrizione della mano
     * @return la descrizione della mano
     */
    @Override
    public String toString()
    {
        return "Scala Colore di " + Card.getRankName(high.getRank());
    }
}