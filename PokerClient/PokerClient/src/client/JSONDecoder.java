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
import java.util.List;
import model.*;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private final Gson gson;
    private Game game;
   
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
    }
    
    private void decode(String toDecode)
    {
        JsonParser parser = new JsonParser();
        String method = parser.parse(toDecode).getAsJsonObject().get("methodInvoked").getAsString();
        switch (method)
        {
            case "ACT":
                decodeAct(toDecode);
                break;
            case "BOARDUPDATED":
                decodeBoardUpdated(toDecode);
                break;
            case "PLAYERUPDATED":
                decodePlayerUpdated(toDecode);
                break;  
            case "SELFUPDATED":
                decodeSelfUpdated(toDecode);
                break;
            case "CURRENTPLAYERUPDATED":
                decodeCurrentPlayerUpdated(toDecode);
                break;
            case "MESSAGEUPDATED":
                decodeMessageUpdated(toDecode);
                break;
            case "BETTINGUPDATED":
                decodeBettingUpdated(toDecode);
                break;
            case "HANDSTARTED":
                decodeHandStarted(toDecode);
                break;
            case "GAMESTARTED":
                decodeGameStarted(toDecode);
                break;
            case "CURRENTPLAYERACTED":
                decodeCurrentPlayerActed(toDecode);
                break;        }
    }
    
    private void decodeAct(String toDecode)
    {

    }
    
    private void decodeBoardUpdated(String toDecode)
    {
        
    }
    
    private void decodeSelfUpdated(String toDecode)
    {
        
    }
    
    private void decodePlayerUpdated(String toDecode)
    {
        
    }
    
    private void decodeCurrentPlayerUpdated(String toDecode)
    {
        
    }
    
    private void decodeCurrentPlayerActed(String toDecode)
    {
        
    }    
    
    private void decodeMessageUpdated(String toDecode)
    {
        
    }
    
    private void decodeBettingUpdated(String toDecode)
    {
        
    }
    
    private void decodeHandStarted(String toDecode)
    {
        
    }
    
    private void decodeGameStarted(String toDecode)
    {
        Type playerListType = new TypeToken<ArrayList<Player>>(){}.getType();
        JsonParser parser = new JsonParser();
        game.setSettings(gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("settings"), GameType.class));
        List<Player> players = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("players"), playerListType);
        game.setPlayers(players);
        
    }
}
