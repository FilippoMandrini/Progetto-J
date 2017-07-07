package poker;

import handtypes.*;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.HandEvaluator;

/**
 * Classe di test della valutazione delle mani
 */
public class HandEvaluatorTest {
    
    private List<Card> cards;
    private Suit SPADES;
    private Suit CLUBS;
    private Suit DIAMONDS;
    private Suit HEARTS;
    private Hand hand;
    
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
    public void testCartaAlta() {
        double first, second;
        
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(2, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof CartaAlta);
        first = hand.getPoints();
        
        cards.clear();
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(3, SPADES));
        cards.add(new Card(2, CLUBS));
        cards.add(new Card(0, SPADES));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof CartaAlta);
        second = hand.getPoints();
        Assert.assertTrue(first == second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(2, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof CartaAlta);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(1, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof CartaAlta);
        double third = hand.getPoints();
        Assert.assertTrue(third < second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(2, HEARTS));
        cards.add(new Card(1, CLUBS));        
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof CartaAlta);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testCoppia() {
        double first, second;

        cards.clear();
        cards.add(new Card(7, CLUBS));
        cards.add(new Card(5, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(0, HEARTS));  
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Coppia);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(9, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(7, CLUBS));
        cards.add(new Card(5, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(0, HEARTS));     
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Coppia);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(6, CLUBS));
        cards.add(new Card(5, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Coppia);
        second = hand.getPoints();
        Assert.assertTrue(first > second);
        
        cards.clear();
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(7, CLUBS));
        cards.add(new Card(5, CLUBS));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(0, HEARTS)); 
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Coppia);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(7, SPADES));
        cards.add(new Card(5, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(1, HEARTS));  
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Coppia);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testDoppiaCoppia() {    
        double first, second;

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(3, CLUBS));  
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof DoppiaCoppia);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));  
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof DoppiaCoppia);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));  
        cards.add(new Card(1, HEARTS));
        cards.add(new Card(1, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof DoppiaCoppia);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));  
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof DoppiaCoppia);
        second = hand.getPoints();
        Assert.assertTrue(first < second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));  
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof DoppiaCoppia);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testTris() {
        double first, second;
        
        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, HEARTS));  
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Tris);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(9, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(9, CLUBS));
        cards.add(new Card(8, HEARTS));  
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Tris);
        second = hand.getPoints();
        Assert.assertTrue(first > second);
        
        cards.clear();
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, HEARTS));  
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Tris);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(7, HEARTS));  
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Tris);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, HEARTS));  
        cards.add(new Card(5, SPADES));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Tris);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testScala() {
        double first, second;

        cards.clear();
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(7, SPADES));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(5, CLUBS));  
        cards.add(new Card(4, HEARTS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(8, CLUBS));  
        cards.add(new Card(4, HEARTS));
        cards.add(new Card(2, CLUBS));    
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        second = hand.getPoints();
        Assert.assertTrue(first < second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(7, DIAMONDS));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(5, DIAMONDS));  
        cards.add(new Card(4, SPADES));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        second = hand.getPoints();
        Assert.assertTrue(first == second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(7, DIAMONDS));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(5, DIAMONDS));
        cards.add(new Card(4, SPADES));  
        cards.add(new Card(3, CLUBS));
        cards.add(new Card(2, DIAMONDS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(7, SPADES));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(5, CLUBS));  
        cards.add(new Card(4, HEARTS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        second = hand.getPoints();
        Assert.assertTrue(first == second);

        cards.clear();
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(2, CLUBS));  
        cards.add(new Card(1, HEARTS));
        cards.add(new Card(0, CLUBS));        
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);
        second = hand.getPoints();
        Assert.assertTrue(first > second);
    }

    @Test
    public void testColore() {   
        double first, second;

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, SPADES));  
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(0, CLUBS));      
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(4, DIAMONDS));  
        cards.add(new Card(2, CLUBS));
        cards.add(new Card(0, HEARTS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        second = hand.getPoints();
        Assert.assertTrue(first == second);

        cards.clear();
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(4, HEARTS));  
        cards.add(new Card(3, SPADES));
        cards.add(new Card(1, HEARTS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, SPADES));  
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(3, SPADES));  
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(4, SPADES));  
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testFull() {
        double first, second;

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, CLUBS));  
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(4, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Full);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(9, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(9, CLUBS));
        cards.add(new Card(8, CLUBS));  
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Full);
        second = hand.getPoints();
        Assert.assertTrue(first > second);
        
        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(7, CLUBS));  
        cards.add(new Card(7, DIAMONDS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Full);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(9, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(9, CLUBS));
        cards.add(new Card(11, CLUBS));  
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Full);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, CLUBS));  
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(2, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Full);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testPoker() {
        double first, second;
        
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(10, SPADES));  
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(6, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof PokerHand);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(11, SPADES));
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(11, CLUBS));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, SPADES));  
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(6, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof PokerHand);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(9, SPADES));  
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(6, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof PokerHand);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(10, SPADES));  
        cards.add(new Card(1, DIAMONDS));
        cards.add(new Card(0, CLUBS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof PokerHand);
        second = hand.getPoints();
        Assert.assertTrue(first == second);
    }

    @Test
    public void testScalaColore() {
        double first, second;
        
        cards.clear();
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(2, SPADES));  
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(8, DIAMONDS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof ScalaColore);
        first = hand.getPoints();

        cards.clear();
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(2, SPADES));  
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(7, DIAMONDS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof ScalaColore);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(3, SPADES));  
        cards.add(new Card(1, CLUBS));
        cards.add(new Card(8, DIAMONDS));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof ScalaColore);
        second = hand.getPoints();
        Assert.assertTrue(first == second);

        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(2, SPADES));  
        cards.add(new Card(0, SPADES));
        cards.add(new Card(3, SPADES));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof ScalaColore);
        second = hand.getPoints();
        Assert.assertTrue(first > second);

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(2, SPADES));  
        cards.add(new Card(0, SPADES));
        cards.add(new Card(3, SPADES));
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Scala);

        cards.clear();
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(9, HEARTS));
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(2, HEARTS));  
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(7, HEARTS));    
        hand = HandEvaluator.evaluateFull(cards);
        Assert.assertTrue(hand instanceof Colore);
    }

}