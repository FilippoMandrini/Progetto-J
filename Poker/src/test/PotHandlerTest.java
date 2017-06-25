/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import actions.Action;
import actions.BigBlind;
import actions.Call;
import actions.SmallBlind;
import gametypes.StandardGame;
import handtypes.DoppiaCoppia;
import handtypes.Hand;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import players.AIBasicStrategy;
import players.Player;
import poker.Card;
import poker.Game;
import poker.PotHandler;
import poker.Suit;
import poker.SuitType;
import utilities.HandEvaluator;

/**
 *
 * @author Nickelsilver
 */
public class PotHandlerTest {
    
    public static void main(String[] args) {
    
        Player A1 = new Player("Bot_1", new AIBasicStrategy(80, 10));
        Player A2 = new Player("Bot_2", new AIBasicStrategy(70, 10));
        Player A3 = new Player("Bot_3", new AIBasicStrategy(90, 10));
        ArrayList<Card> cards = new ArrayList<>();
        Suit S3 = new Suit(SuitType.HEARTS, 3);
        Suit S2 = new Suit(SuitType.DIAMONDS, 2);
        Suit S1 = new Suit(SuitType.CLUBS, 1);
        Suit S0 = new Suit(SuitType.SPADES, 0);
        cards.clear();
        cards.add(new Card(2, S0));
        cards.add(new Card(3, S1));
        cards.add(new Card(5, S1));
        cards.add(new Card(6, S2));
        cards.add(new Card(8, S0));
        A1.addCard(new Card(4, S3));
        A1.addCard(new Card(0, S3));
        A2.addCard(new Card(4, S2));
        A2.addCard(new Card(12, S3));
        A3.addCard(new Card(11, S1));
        A3.addCard(new Card(8, S2));
        ArrayList<Player> players = new ArrayList<>();
        players.add(A1);
        players.add(A2);
        players.add(A3);
        for (Player player : players)
        {
            player.setCurrentHand(HandEvaluator.evaluate(player, cards));
            System.out.println(player.toString() + "\t" + player.getCurrentHand() + "\n" + player.getCurrentHand().getFullDescription());
        }
        Map<Hand, List<Player>> ranking = new TreeMap<>();
        for (Player player : players)
        {
            List<Player> currentHandPlayers = ranking.get(player.getCurrentHand());

            if (currentHandPlayers == null)
            {
                currentHandPlayers = new ArrayList<>();
            }
            currentHandPlayers.add(player);
            ranking.put(player.getCurrentHand(), currentHandPlayers);
        }
        PotHandler potHandler = new PotHandler();
        potHandler.clearPots();
        Game G1 = new Game(new StandardGame(5000));
        G1.addPlayer(A1);
        G1.addPlayer(A2);
        G1.addPlayer(A3);
        int dealerPosition = 1;
        for (Player player : G1.getPlayers())
        {
            player.reset();
        }
        A1.setActive(true);
        A2.setActive(true);
        A3.setActive(true);
        G1.setCurrentPlayer(A3);
        Action smallBlind = new SmallBlind(G1.getCurrentBigBlind()/2);
        A3.setLastAction(smallBlind);
        smallBlind.execute(G1.getFacade(), 0);
        G1.setCurrentPlayer(A1);
        Action bigBlind = new BigBlind(G1.getCurrentBigBlind());
        A1.setLastAction(bigBlind);
        bigBlind.execute(G1.getFacade(), 0);
        G1.setCurrentPlayer(A2);
        Action call = new Call();
        A2.setLastAction(call);
        call.execute(G1.getFacade(), 0);
        G1.setCurrentPlayer(A3);
        call = new Call();
        A3.setLastAction(call);
        call.execute(G1.getFacade(), 0);
        System.out.println(G1.getPotHandler().getTotalPot());
        System.out.println(G1.getPotHandler());
        G1.getPotHandler().distributePots(ranking, G1.getPlayers(), dealerPosition);
        System.out.println(A1.getStake());
        System.out.println(A2.getStake());
    }
    
}
