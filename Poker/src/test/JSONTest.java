/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import actions.Action;
import actions.ActionSet;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.Raise;
import actions.SmallBlind;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import java.util.HashSet;
import java.util.Set;
import players.AIBasicStrategy;
import players.Player;
import poker.Board;
import poker.Card;
import poker.Suit;
import server.JSONEncoder;
import server.RuntimeTypeAdapterFactory;

/**
 *
 * @author Nickelsilver
 */
public class JSONTest {
    
    public static void main(String args[])
    {
        Player A1 = new Player("Luca", new AIBasicStrategy(50, 50));
        A1.setLastAction(new Bet(100));
        A1.setStake(100);
        Card C1 = new Card(1, new Suit(0));
        Card C2 = new Card(2, new Suit(1));
        A1.addCard(C2);
        A1.addCard(C1);
        Board B1 = new Board();
        B1.flop();
        for (Card card : B1.getCommunityCards())
        {
            System.out.println(card.toString());
        }
        Set<ActionSet> allowedActions = new HashSet<>();
        allowedActions.add(ActionSet.BET);
        allowedActions.add(ActionSet.CALL);
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonParser parser = new JsonParser();
        RuntimeTypeAdapterFactory<Action> factory = RuntimeTypeAdapterFactory
                .of(Action.class, "actionType")
                .registerSubtype(Bet.class, "BET")
                .registerSubtype(Call.class, "CALL")
                .registerSubtype(Raise.class, "RAISE")
                .registerSubtype(Check.class, "CHECK")
                .registerSubtype(BigBlind.class, "BIGBLIND")
                .registerSubtype(Fold.class, "FOLD")
                .registerSubtype(SmallBlind.class, "SMALLBLIND");
        gsonBuilder.registerTypeAdapterFactory(factory);
        System.out.println(JSONEncoder.getInstance().encodeBoardUpdated(B1));
    }
}
