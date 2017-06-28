/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import actions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;
import java.io.InterruptedIOException;
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
        
        commands = new HashMap<>();
        
        commands.put("ACT", new JSONCommand<Action>(){
            @Override
            public Action execute(String toDecode) {
                JsonParser parser = new JsonParser();            
                return gson.fromJson(parser.parse(toDecode).getAsJsonObject().get("action"), Action.class); 
            }   
        });
        commands.put("PING", new JSONCommand<Boolean>(){
            @Override
            public Boolean execute(String toDecode) {
                return true;
            }   
        });
        commands.put("GAMEJOINED", new JSONCommand<String>(){
            @Override
            public String execute(String toDecode) {
                JsonParser parser = new JsonParser();
                return parser.parse(toDecode).getAsJsonObject().get("playerName").getAsString();
            }

        });
    }
    
    public Object decode(String toDecode) throws InterruptedIOException
    {
        JsonParser parser = new JsonParser();  
        if (toDecode == null)
        {
            throw new InterruptedIOException("Errore");
        }
        //System.out.println(toDecode);
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
