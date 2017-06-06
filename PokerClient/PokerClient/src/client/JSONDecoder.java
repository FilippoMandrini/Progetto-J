/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import actions.Action;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.Raise;
import actions.SmallBlind;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    
    
    
}
