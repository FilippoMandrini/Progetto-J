/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import actions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.*;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private final Gson gson;
    private Game game;
    private final HashMap<String, JSONCommand> commands;

   
    public JSONDecoder(Game game){
        RuntimeTypeAdapterFactory<Action> afactory = RuntimeTypeAdapterFactory
                .of(Action.class, "actionType")
                .registerSubtype(Bet.class, "BET")
                .registerSubtype(Call.class, "CALL")
                .registerSubtype(Raise.class, "RAISE")
                .registerSubtype(Check.class, "CHECK")
                .registerSubtype(BigBlind.class, "BIGBLIND")
                .registerSubtype(Fold.class, "FOLD")
                .registerSubtype(SmallBlind.class, "SMALLBLIND");
        RuntimeTypeAdapterFactory<GameType> gtfactory = RuntimeTypeAdapterFactory
                .of(GameType.class, "type")
                .registerSubtype(StandardGame.class, "standard")
                .registerSubtype(CustomGame.class, "custom");
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(afactory)
                .registerTypeAdapterFactory(gtfactory)
                .create();      
        
        commands = new HashMap<>();
        
        commands.put("ACT", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("GAMESTARTED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                Type playerListType = new TypeToken<ArrayList<Player>>() {
                }.getType();
                JsonParser parser = new JsonParser();
                game.setSettings(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("settings"), GameType.class));
                List<Player> players = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("players"), playerListType);
                game.setPlayers(players);
                game.setActivePlayers();
            }
        });
        commands.put("HANDSTARTED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                JsonParser parser = new JsonParser();
                game.setDealer(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("dealer"), Player.class));
                game.setDealerPosition(game.getActivePlayers().indexOf(game.getDealer()));
            }
        });
        commands.put("BOARDUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("PLAYERUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("SELFUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("CURRENTPLAYERUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("MESSAGEUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("BETTINGUPDATED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commands.put("CURRENTPLAYERACTED", new JSONCommand(){
            @Override
            public void execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
    }
    public void decode(String toDecode)
    {
        JsonParser parser = new JsonParser();            
        String method = parser.parse(toDecode).getAsJsonObject().get("methodInvoked").getAsString();
        if (commands.containsKey(method))
        {
            commands.get(method).execute(toDecode);
        }
        else
        {
            throw new IllegalArgumentException("Comunicazione sconosciuta!");
        }
    }
}
   