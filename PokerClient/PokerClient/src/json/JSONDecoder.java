/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import actions.*;
import client.Sender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;
import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.*;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
    
    private static JSONDecoder instance;
    private final Gson gson;
    private final Game game;
    private final HashMap<String, JSONCommand> commands;

   
    private JSONDecoder(Game game){
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
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(afactory)
                .registerTypeAdapterFactory(gtfactory)
                .create();      
        
        this.game = game;
        commands = new HashMap<>();
        
        commands.put("ACT", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                int bet = (parser.parse(toDecode).getAsJsonObject().get("bet").getAsInt());
                int minBet = (parser.parse(toDecode).getAsJsonObject().get("minBet").getAsInt());
                Type actionSetListType = new TypeToken<HashSet<ActionSet>>() {
                }.getType();
                Set<ActionSet> allowedActions = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("allowedActions"), actionSetListType);
                game.actionRequested(bet, minBet, allowedActions);
                return null;
            }
        });
        commands.put("GAMESTARTED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                Type playerListType = new TypeToken<ArrayList<Player>>() {
                }.getType();
                JsonParser parser = new JsonParser();
                GameType settings = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("settings"), GameType.class);
                List<Player> players = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("players"), playerListType);
                game.updateGameStarted(settings, players);
                return null;
            }
        });
        commands.put("HANDSTARTED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updateDealer(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("dealer"), Player.class), parser.parse(toDecode).getAsJsonObject().get("dealerPosition").getAsInt());            
                return null;
            }
        });
        commands.put("BOARDUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updateBoard(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("board"), Board.class));
                return null;
            }
        });
        commands.put("PLAYERUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updatePlayer(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("player"), Player.class));
                return null;
            }
        });
        commands.put("SELFUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updatePlayer(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("self"), Player.class));            
                return null;
            }
        });
        commands.put("CURRENTPLAYERUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updateCurrentPlayer(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("currentPlayer"), Player.class), parser.parse(toDecode).getAsJsonObject().get("currentPlayerPosition").getAsInt());            
                return null;
            }
        });
        commands.put("MESSAGEUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updateMessage(parser.parse(toDecode).getAsJsonObject().get("message").getAsString());
                return null;
            }
        });
        commands.put("BETTINGUPDATED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                int bet = parser.parse(toDecode).getAsJsonObject().get("bet").getAsInt();
                int minBet = parser.parse(toDecode).getAsJsonObject().get("minBet").getAsInt();
                int totalPot = parser.parse(toDecode).getAsJsonObject().get("totalPot").getAsInt();
                game.updateBetting(bet, minBet, totalPot);
                return null;
            }
        });
        commands.put("CURRENTPLAYERACTED", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.updateCurrentAction(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("shadowCopy"), Player.class));            
                return null;
            }
        });
        commands.put("GAMEJOINED", new JSONCommand<Boolean>(){
            @Override
            public Boolean execute(String toDecode) {
                return true;
            }
        });
        commands.put("DISCONNECT", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) throws InterruptedIOException {
                game.notifyDisconnect();
                throw new InterruptedIOException();
            }
        });
        commands.put("PING", new JSONCommand<Void>(){
            @Override
            public Void execute(String toDecode) {
                return null;
            }
        });
    }
    public Object decode(String toDecode) throws InterruptedIOException, NullPointerException
    {
        JsonParser parser = new JsonParser();            
        String method = parser.parse(toDecode).getAsJsonObject().get("methodInvoked").getAsString();
        if (commands.containsKey(method))
        {
            return commands.get(method).execute(toDecode);
        }
        else
        {
            throw new IllegalArgumentException("Comunicazione sconosciuta!");
        }
    }
    
        public static synchronized JSONDecoder getInstance(Game game)
    {
        if (instance == null)
        {
            instance = new JSONDecoder(game);
        }
        return instance;
    }

    
}
   