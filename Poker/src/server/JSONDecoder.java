/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import actions.Action;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.GenericAction;
import actions.Raise;
import actions.SmallBlind;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private static JSONDecoder instance;
    private final Gson gson;
   
    private JSONDecoder(){
        RuntimeTypeAdapterFactory<Action> factory = RuntimeTypeAdapterFactory
                .of(Action.class, "actionType")
                .registerSubtype(Bet.class, "BET")
                .registerSubtype(Call.class, "CALL")
                .registerSubtype(Raise.class, "RAISE")
                .registerSubtype(Check.class, "CHECK")
                .registerSubtype(BigBlind.class, "BIGBLIND")
                .registerSubtype(Fold.class, "FOLD")
                .registerSubtype(SmallBlind.class, "SMALLBLIND");
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(factory)
                .create();      
    }
    
    public static synchronized JSONDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new JSONDecoder();
        }
        return instance;
    }
    
    public Action decodeAct(String toDecode)
    {
        JsonParser parser = new JsonParser();
        if (parser.parse(toDecode).getAsJsonObject().get("methodInvoked").getAsString().equalsIgnoreCase("ACt"))
        {
            Action action = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("action"), Action.class);
            return action;
        }
        else
        {
              throw new IllegalArgumentException("Errore messaggio ricevuto dal client");
        }
    }
    
//    public String decodeGameStarted(String toDecode)
//    {
//        
//    }
}
