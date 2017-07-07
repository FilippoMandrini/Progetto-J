package poker;

import exceptions.WrongCardNumberException;
import handtypes.CartaAlta;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe di test della mano
 */
public class HandTest {
     
    private List<Card> cards;
    private Suit SPADES;
    private Suit CLUBS;
    private Suit DIAMONDS;
    private Suit HEARTS;
    private Card P3 ;
    private Card C1 ;
    private Card D12;
    private Card C12;
    private Card H10;
    
    @Before 
    public void init()
    {
        SPADES = new Suit(0);
        CLUBS = new Suit(1);
        DIAMONDS = new Suit(2);
        HEARTS = new Suit(3);
        cards = new ArrayList<>();
    }
    
    @Test
    public void testBasic() {
        cards.add(new Card(1, 0));
        cards.add(new Card(3, 1));
        cards.add(new Card(5, 2));
        cards.add(new Card(7, 3));
        cards.add(new Card(9, 0));
        Hand hand = new CartaAlta(cards);
        Assert.assertNotNull(hand);
    }
    
    @Test
    public void testConstructors() {
        Hand hand;
        
        cards.clear();
        try {
            hand = new CartaAlta(cards);
            Assert.fail("Nessuna Eccezione lanciata");
        } catch (WrongCardNumberException e) {
        }

        for (int i= 0; i< 10; i++)
        {
            cards.add(new Card(10, 1));
        }
        try {
            hand = new CartaAlta(cards);
            Assert.fail("Nessuna Eccezione lanciata");
        } catch (WrongCardNumberException e) {
        }

        try {
            cards = null;
            hand = new CartaAlta(cards);
            Assert.fail("Nessuna Eccezione lanciata");
        } catch (WrongCardNumberException e) {
        }

        try {
            cards = new ArrayList<>();
            for (int i= 0; i<4; i++)
            {
                cards.add(new Card(10, 1));
            }
            cards.add(null);
            hand = new CartaAlta(cards);
            Assert.fail("Nessuna Eccezione lanciata");
        } catch (WrongCardNumberException e) {
        }
    }
}
