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
import actions.Raise;
import actions.SmallBlind;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 *
 * @author Nickelsilver
 */
public class JSONDecoder {
     
    private static JSONDecoder instance;
    private final Gson gson;
    private final HashMap<String, JSONCommand> commands;

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
        
        commands = new HashMap<>();
        
        commands.put("ACT", new JSONCommand<Action>(){
            @Override
            public Action execute(String toDecode) {
                JsonParser parser = new JsonParser();            
                Action action = gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("action"), Action.class);
                return action;
            }   
        });
        commands.put("GAMEJOINED", new JSONCommand<String>(){
            @Override
            public String execute(String toDecode) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }
    
    public Object decode(String toDecode)
    {
        JsonParser parser = new JsonParser();            
        String method = parser.parse(toDecode).getAsJsonObject().get("methodInvoked").getAsString();
        if (commands.containsKey(method))
        {
            return commands.get(method).execute(toDecode);
        }
        return null;
    }
    
    public static synchronized JSONDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new JSONDecoder();
        }
        return instance;
    }

}
