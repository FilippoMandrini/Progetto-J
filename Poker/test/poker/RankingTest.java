/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import gametypes.StandardGame;
import handtypes.Full;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import players.HumanTestStrategy;
import players.Player;
import utilities.HandEvaluator;

/**
 *
 * @author Nickelsilver
 */
public class RankingTest {
    
    private List<Card> cards;
    private Suit SPADES;
    private Suit CLUBS;
    private Suit DIAMONDS;
    private Suit HEARTS;
    
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
    public void testBasic()
    {
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        Hand first = HandEvaluator.evaluateSingle(cards);
        cards.add(new Card(10, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(8, HEARTS));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(6, DIAMONDS));
        Hand second = HandEvaluator.evaluateSingle(cards);
        Assert.assertEquals(first, second);  
        cards.clear();
        cards.add(new Card(6, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(10, HEARTS));
        first = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(6, CLUBS));
        cards.add(new Card(6, SPADES));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(10, HEARTS));
        Hand H4 = new Full(cards);
        second = HandEvaluator.evaluateSingle(cards);
        Assert.assertEquals(second, first);
        
        
    }
    
    @Test 
    public void testOrder()
    {
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(10, CLUBS));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(7, DIAMONDS));
        Hand H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(0, DIAMONDS));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(2, DIAMONDS));
        cards.add(new Card(3, HEARTS));
        Hand H1 = HandEvaluator.evaluateSingle(cards);   
        Assert.assertTrue(H0.compareTo(H1) > 0);
        
        cards.clear();
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(9, HEARTS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(1, CLUBS));
        cards.add(new Card(1, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);
        
        cards.clear();
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(11, SPADES));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, HEARTS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(1, DIAMONDS));
        cards.add(new Card(2, DIAMONDS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);
        
        cards.clear();
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, DIAMONDS));
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, DIAMONDS));
        cards.add(new Card(9, HEARTS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(1, DIAMONDS));
        cards.add(new Card(2, SPADES));
        cards.add(new Card(3, DIAMONDS));
        cards.add(new Card(4, HEARTS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);
        
        cards.clear();
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(10, SPADES));
        cards.add(new Card(9, DIAMONDS));
        cards.add(new Card(8, HEARTS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, SPADES));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(2, SPADES));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);        
        
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, SPADES));
        cards.add(new Card(11, SPADES));
        cards.add(new Card(10, SPADES));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(0, DIAMONDS));
        cards.add(new Card(0, SPADES));
        cards.add(new Card(1, SPADES));
        cards.add(new Card(1, HEARTS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);    

        cards.clear();
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(12, CLUBS));
        cards.add(new Card(12, SPADES));
        cards.add(new Card(11, DIAMONDS));
        cards.add(new Card(11, HEARTS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(0, DIAMONDS));
        cards.add(new Card(1, DIAMONDS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);         
        
        cards.clear();
        cards.add(new Card(0, SPADES));
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(0, CLUBS));
        cards.add(new Card(0, DIAMONDS));
        cards.add(new Card(1, DIAMONDS));
        H0 = HandEvaluator.evaluateSingle(cards);
        cards.clear();
        cards.add(new Card(0, HEARTS));
        cards.add(new Card(1, HEARTS));
        cards.add(new Card(2, HEARTS));
        cards.add(new Card(3, HEARTS));
        cards.add(new Card(4, HEARTS));
        H1 = HandEvaluator.evaluateSingle(cards);
        Assert.assertTrue(H0.compareTo(H1) > 0);
    }

    @Test
    public void testRanking() {
        Player P0 = new Player("P0", new HumanTestStrategy());
        Player P1 = new Player("P1", new HumanTestStrategy());
        Player P2 = new Player("P2", new HumanTestStrategy());
        Player P3 = new Player("P3", new HumanTestStrategy());
        Player P4 = new Player("P3", new HumanTestStrategy());
        Game G0 = new Game(new StandardGame(5000));
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        Hand H0 = HandEvaluator.evaluateSingle(cards);
        P0.setCurrentHand(H0);   
        P0.setId(0);
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(11, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        Hand H1 = HandEvaluator.evaluateSingle(cards);
        P1.setCurrentHand(H1); 
        P1.setId(1);
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(10, HEARTS));
        cards.add(new Card(8, SPADES));
        cards.add(new Card(6, HEARTS));
        cards.add(new Card(3, DIAMONDS));
        Hand H2 = HandEvaluator.evaluateSingle(cards);
        P2.setCurrentHand(H2);
        P2.setId(2);
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(1, CLUBS));
        cards.add(new Card(6, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        Hand H3 = HandEvaluator.evaluateSingle(cards);
        P3.setCurrentHand(H3);
        P3.setId(3);
        cards.clear();
        cards.add(new Card(12, SPADES));
        cards.add(new Card(12, HEARTS));
        cards.add(new Card(8, CLUBS));
        cards.add(new Card(8, DIAMONDS));
        cards.add(new Card(3, DIAMONDS));
        Hand H4 = HandEvaluator.evaluateSingle(cards);
        P4.setCurrentHand(H4);
        P4.setId(4);
        List<Player> players = new ArrayList<>();
        players.add(P0);
        players.add(P1);
        players.add(P2);
        players.add(P3);
        players.add(P4);
        ArrayList<Hand> hands = new ArrayList(G0.getRanking(players).keySet());
        Assert.assertEquals(H4, hands.get(0));
        Assert.assertEquals(H3, hands.get(1));
        Assert.assertEquals(H1, hands.get(2));
        Assert.assertEquals(H0, hands.get(3));
        Assert.assertEquals(H2, hands.get(3));
        Assert.assertEquals(P4, G0.getRanking(players).get(H4).get(0));
        Assert.assertEquals(P3, G0.getRanking(players).get(H3).get(0));
        Assert.assertEquals(P2, G0.getRanking(players).get(H2).get(1));
        Assert.assertEquals(P1, G0.getRanking(players).get(H1).get(0));
        Assert.assertEquals(P0, G0.getRanking(players).get(H2).get(0));
    }

}
