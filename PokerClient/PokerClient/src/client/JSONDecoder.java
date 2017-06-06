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
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private static JSONDecoder instance;
    private final Gson gson;
    private Game game;
   
    private JSONDecoder(){
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
                
        }
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
        
    }
}
