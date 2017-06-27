/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import annotations.JSONExclude;
import actions.*;
import com.google.gson.*;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;
import java.util.List;
import java.util.Set;
import players.Player;
import poker.Board;
/**
 *
 * @author Nickelsilver
 */
public class JSONEncoder {

    private static JSONEncoder instance;
    private Gson gson;
   
    private JSONEncoder(){
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
                .setExclusionStrategies(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(JSONExclude.class) != null;
                    }

                })
                .registerTypeAdapterFactory(afactory)
                .registerTypeAdapterFactory(gtfactory)
                .create();      
    }
    
    public static synchronized JSONEncoder getInstance()
    {
        if (instance == null)
        {
            instance = new JSONEncoder();
        }
        return instance;
    }
    
    public String encodeAct(int minBet, int bet, Set<ActionSet> allowedActions)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "ACT");
        toSend.addProperty("minBet", minBet);
        toSend.addProperty("bet", bet);
        toSend.add("allowedActions", encodeObject(allowedActions));
        return gson.toJson(toSend);
    }
    
    public String encodeBoardUpdated(Board board)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "BOARDUPDATED");
        toSend.add("board", encodeObject(board));
        return gson.toJson(toSend);
    }
    
    public String encodePlayerUpdated(Player player)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "PLAYERUPDATED");
        toSend.add("player", encodeObject(player));
        return gson.toJson(toSend);
    }
    
    public String encodeCurrentPlayerUpdated(Player currentPlayer, int currentPlayerPosition)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "CURRENTPLAYERUPDATED");
        toSend.add("currentPlayer", encodeObject(currentPlayer));
        toSend.addProperty("currentPlayerPosition", currentPlayerPosition);
        return gson.toJson(toSend);
    }
    
    public String encodeMessageUpdated(String message)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "MESSAGEUPDATED");
        toSend.addProperty("message", message);
        return gson.toJson(toSend);
    }
    
    public String encodeBettingUpdated(int bet, int minBet, int totalPot)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "BETTINGUPDATED");
        toSend.addProperty("bet", bet);
        toSend.addProperty("minBet", minBet);
        toSend.addProperty("totalPot", totalPot);
        return gson.toJson(toSend);
    }
    
    public String encodeHandStarted(Player dealer, int dealerPosition)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "HANDSTARTED");
        toSend.add("dealer", encodeObject(dealer));
        toSend.addProperty("dealerPosition", dealerPosition);
        return gson.toJson(toSend);
    }
    
    public String encodeSelfUpdated(Player player)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "SELFUPDATED");
        toSend.add("self", encodeObject(player));
        return gson.toJson(toSend);
    }
    
    public String encodeCurrentPlayerActed(Player shadowCopy)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "CURRENTPLAYERACTED");
        toSend.add("shadowCopy", encodeObject(shadowCopy));
        return gson.toJson(toSend);
    }
    
    public String encodeGameStarted(List<Player> players, GameType settings)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "GAMESTARTED");
        toSend.add("players", encodeObject(players));
        toSend.add("settings", encodeObject(settings));
        return gson.toJson(toSend);
    }
    
    public String encodeGameJoined()
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "GAMEJOINED");
        return gson.toJson(toSend);
    }
    
    public JsonElement encodeObject(Object toEncode)
    {
        JsonElement jsonElement = gson.toJsonTree(toEncode);
        if(jsonElement.isJsonObject())
        {
            jsonElement.getAsJsonObject().addProperty("objectType", toEncode.getClass().getSimpleName());
        }
        return jsonElement;
    }

    public String encodeDisconnect() {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "DISCONNECT");
        return gson.toJson(toSend);    
    }
    
    public String encodePing() {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "PING");
        return gson.toJson(toSend);    
    }    

}
