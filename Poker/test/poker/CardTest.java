package poker;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


public class CardTest {
    
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
        P3 = new Card(3, SPADES);
        C1 = new Card(1, CLUBS);
        D12 = new Card(12, DIAMONDS);
        C12 = new Card(12, CLUBS);
        H10 = new Card(10, HEARTS); 
    }
    
    

    @Test
    public void testBasic() {
        Card card = new Card(10, HEARTS);
        Assert.assertNotNull(card);
        Assert.assertEquals(10, card.getRank());
        Assert.assertEquals(HEARTS, card.getSuit());
        Assert.assertEquals(3, card.getSuit().getValue());
        Assert.assertEquals("Regina di Cuori", card.toString());
        card = new Card(0, SPADES); // Automatic trimming.
        Assert.assertNotNull(card);
        Assert.assertEquals(0, card.getRank());
        Assert.assertEquals(SPADES, card.getSuit());
    }
    
    @Test
    public void testConstructors() {
        Card card;
        
        try {
            card = new Card(-1, 0);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}

        try {
            card = new Card(60, 0);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}
        
        try {
            card = new Card(4, 9);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}
        
        try {
            card = new Card(60, 3);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}

        try {
            card = new Card(0, 6);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}
        

        try {
            card = new Card(0, -1);
            Assert.fail("Nessuna eccezione lanciata!");
        } catch (IllegalArgumentException e) {}
        
    }

    @Test
    public void testSortingEqualsCompare() {
        
        Assert.assertEquals(D12, D12);
        Assert.assertFalse(C12.equals(D12));
        Assert.assertFalse(H10.equals(P3));
        Assert.assertTrue(C1.compareTo(C1) == 0);
        Assert.assertTrue(D12.compareTo(C1) < 0);
        Assert.assertTrue(C1.compareTo(P3) > 0);
        Assert.assertTrue(C1.compareTo(H10) > 0);
        Assert.assertTrue(C12.compareTo(D12) > 0);
        Assert.assertTrue(D12.compareTo(C12) < 0);
    }

}
