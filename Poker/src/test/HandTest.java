package test;

import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import poker.Card;
import players.TestPlayer;
import players.Player;
import poker.Suit;
import poker.SuitType;

public class HandTest {

    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();
        Suit S3 = new Suit(SuitType.HEARTS, 3);
        Suit S2 = new Suit(SuitType.DIAMONDS, 2);
        Suit S1 = new Suit(SuitType.CLUBS, 1);
        Suit S0 = new Suit(SuitType.SPADES, 0);
        cards.clear();
        cards.add(new Card(4, S1));
        cards.add(new Card(4, S2));
        cards.add(new Card(3, S0));
        cards.add(new Card(3, S2));
        cards.add(new Card(2, S3));
        Hand H1 = new DoppiaCoppia(cards);
        cards.clear();
        cards.add(new Card(2, S1));
        cards.add(new Card(2, S2));
        cards.add(new Card(10, S0));
        cards.add(new Card(4, S2));
        cards.add(new Card(3, S3));
        Hand H2 = new Coppia(cards);
        cards.clear();
        cards.add(new Card(4, S1));
        cards.add(new Card(4, S2));
        cards.add(new Card(4, S0));
        cards.add(new Card(3, S0));
        cards.add(new Card(2, S3));
        Hand H3 = new Tris(cards);
        cards.clear();
        cards.add(new Card(6, S1));
        cards.add(new Card(6, S2));
        cards.add(new Card(6, S0));
        cards.add(new Card(10, S2));
        cards.add(new Card(10, S3));
        Hand H4 = new Full(cards);
        cards.clear();
        cards.add(new Card(10, S1));
        cards.add(new Card(10, S2));
        cards.add(new Card(10, S0));
        cards.add(new Card(6, S2));
        cards.add(new Card(6, S3));
        Hand H5 = new Full(cards);
        cards.clear();
        cards.add(new Card(12, S3));
        cards.add(new Card(12, S1));
        cards.add(new Card(12, S0));
        cards.add(new Card(12, S2));
        cards.add(new Card(6, S3));
        Hand H6 = new PokerHand(cards);
        cards.clear();
        cards.add(new Card(12, S0));
        cards.add(new Card(11, S0));
        cards.add(new Card(10, S0));
        cards.add(new Card(8, S0));
        cards.add(new Card(9, S0));
        Hand H7 = new ScalaColore(cards);
        cards.clear();
        for (int i = 0; i<5; i++)
        {
            cards.add(new Card(i+2, S3));
        }
        Hand H8 = new Colore(cards);
        System.out.println(H8.getFullDescription());
        cards.clear();
        for (int i = 12; i>=8; i--)
        {
            cards.add(new Card(i, S2));
        }
        Hand H9 = new Scala(cards);
        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(H1);
        hands.add(H2);
        hands.add(H3);
        hands.add(H4);
        hands.add(H5);
        hands.add(H6);
        hands.add(H7);
        hands.add(H8);
        hands.add(H9);
        Collections.sort(hands);
//        for (Hand hand : hands) {
//            System.out.println(hand.toString());
//            System.out.println(hand.getPoints());
//        }
        Map<Hand, List<Player>> ranking = new TreeMap<>();
        Player P1 = new TestPlayer("Luca");
        Player P5 = new TestPlayer("Giacomo");
        Player P6 = new TestPlayer("Giuda");
        Player P2 = new TestPlayer("Matteo");
        Player P3 = new TestPlayer("Marco");
        Player P4 = new TestPlayer("Giovanni");
        P1.setCurrentHand(H1);
        P5.setCurrentHand(H1);
        P2.setCurrentHand(H3);
        P3.setCurrentHand(H9);
        P4.setCurrentHand(H6);
        P6.setCurrentHand(H9);
        Set<Player> activePlayers = new HashSet<>();
        activePlayers.add(P1);
        activePlayers.add(P5);
        activePlayers.add(P6);
        activePlayers.add(P4);
        activePlayers.add(P3);
        activePlayers.add(P2);
        for (Player player : activePlayers)
        {
            List<Player> currentHandPlayers = ranking.get(player.getCurrentHand());

            if (currentHandPlayers == null)
            {
                currentHandPlayers = new ArrayList<>();
            }
            currentHandPlayers.add(player);
            ranking.put(player.getCurrentHand(), currentHandPlayers);
        }
        for (Hand hand : ranking.keySet())
        {
            System.out.println(hand.toString());
            for (Player player : ranking.get(hand))
            {
                System.out.println(player.toString());
            }
        }
        


        
    }

}