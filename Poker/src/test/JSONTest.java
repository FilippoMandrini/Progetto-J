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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import players.AIBasicStrategy;
import players.HumanTestStrategy;
import players.Player;
import poker.Board;
import poker.Card;
import poker.Suit;
import json.JSONEncoder;
import json.RuntimeTypeAdapterFactory;

/**
 *
 * @author Nickelsilver
 */
public class JSONTest {

    public static void main(String args[]) {
        Player A1 = new Player("Luca", new AIBasicStrategy(50, 50));
        Player A2 = new Player("Mandro", new HumanTestStrategy());
        A1.setLastAction(new Bet(100));
        A1.setStake(100);
        A2.setStake(10000);
        A2.setLastAction(new Call());
        List<Player> riccanza = new ArrayList<>();
        riccanza.add(A2);
        riccanza.add(A1);
        Card C1 = new Card(1, new Suit(0));
        Card C2 = new Card(2, new Suit(1));
        A1.addCard(C2);
        A1.addCard(C1);
        Board B1 = new Board();
        B1.dealCommunityCards(3);
        for (Card card : B1.getCommunityCards()) {
            System.out.println(card.toString());
        }
        Set<ActionSet> allowedActions = new HashSet<>();
        allowedActions.add(ActionSet.BET);
        allowedActions.add(ActionSet.CALL);
        JsonParser parser = new JsonParser();
        RuntimeTypeAdapterFactory<Action> afactory = RuntimeTypeAdapterFactory
                .of(Action.class, "type")
                .registerSubtype(Bet.class, "be")
                .registerSubtype(Call.class, "ca")
                .registerSubtype(Raise.class, "ra")
                .registerSubtype(Check.class, "ch")
                .registerSubtype(BigBlind.class, "bb")
                .registerSubtype(Fold.class, "fo")
                .registerSubtype(SmallBlind.class, "sb");
        RuntimeTypeAdapterFactory<GameType> gtfactory = RuntimeTypeAdapterFactory
                .of(GameType.class, "type")
                .registerSubtype(StandardGame.class, "standard")
                .registerSubtype(CustomGame.class, "custom");
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(afactory)
                .registerTypeAdapterFactory(gtfactory)
                .create();
        System.out.println(JSONEncoder.getInstance().encodeGameStarted(riccanza, new StandardGame(1000)));
        Type playerListType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        List<Player> players;
        //players = gsonBuilder.create().fromJson(parser.parse(JSONEncoder.getInstance().encodeGameStarted(riccanza, new StandardGame(1000))).getAsJsonObject().get("players"), playerListType);
        //System.out.println(players.toString());
        JsonElement jsonElement = gson.toJsonTree(new Bet(1000));
        jsonElement.getAsJsonObject().addProperty("objectType", new Bet(1000).getClass().getSimpleName());
        JsonObject toSend = new JsonObject();
        toSend.add("action", jsonElement);
        String toDecode = gson.toJson(toSend);
        System.out.println(toDecode);
        Action action = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("action"), Action.class);
        System.out.println("fine");
    }
}
