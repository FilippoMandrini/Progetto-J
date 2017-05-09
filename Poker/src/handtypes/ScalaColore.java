
package handtypes;

import java.util.ArrayList;
import poker.Card;

public class ScalaColore extends Hand {
    
    private Card high;
    
    public ScalaColore(ArrayList<Card> cards) {
        super(cards);
        this.high = this.cards.get(0);
    }
    
    @Override
    public double getPoints() {
        return 800 +high.getValue();
    }
    
    @Override
    public String toString()
    {
        return "Scala Colore di " + Card.getValueName(high.getValue());
    }
}