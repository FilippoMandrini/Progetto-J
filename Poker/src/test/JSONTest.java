package test;

import actions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gametypes.*;
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
 * Classe di test per la codifica JSON
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
        JsonParser parser = new JsonParser();
        RuntimeTypeAdapterFactory<Action> afactory = RuntimeTypeAdapterFactory
                .of(Action.class, "type")
                .registerSubtype(Bet.class, "bet")
                .registerSubtype(Call.class, "call")
                .registerSubtype(Raise.class, "raise")
                .registerSubtype(Check.class, "check")
                .registerSubtype(BigBlind.class, "big_blind")
                .registerSubtype(Fold.class, "fold")
                .registerSubtype(SmallBlind.class, "small_blind");
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
        players = gson.fromJson(parser.parse(JSONEncoder.getInstance().encodeGameStarted(riccanza, new StandardGame(1000))).getAsJsonObject().get("players"), playerListType);
        System.out.println(players.toString());
        JsonElement jsonElement = gson.toJsonTree(new Bet(1000));
        jsonElement.getAsJsonObject().addProperty("objectType", new Bet(1000).getClass().getSimpleName());
        JsonObject toSend = new JsonObject();
        toSend.add("action", jsonElement);
        String toDecode = gson.toJson(toSend);
        System.out.println(toDecode);
        Action action = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("action"), Action.class);
        System.out.println(action.getName() + " " + action);
    }
}
