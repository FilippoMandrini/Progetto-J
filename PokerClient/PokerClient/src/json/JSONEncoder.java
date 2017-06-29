package json;

import actions.*;
import annotations.JSONExclude;
import com.google.gson.*;
import gametypes.CustomGame;
import gametypes.GameType;
import gametypes.StandardGame;

/**
 * Classe dell'Encoder JSON
 */
public class JSONEncoder {

    private static JSONEncoder instance;
    private Gson gson;
   
    /**
     * Costruttore dell'encoder
     */
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
    
    /**
     * Restituisce l'istanza dell'encoder
     * @return l'istanza dell'encoder
     */
    public static synchronized JSONEncoder getInstance()
    {
        if (instance == null)
        {
            instance = new JSONEncoder();
        }
        return instance;
    }
    
    /**
     * Codifica il messaggio di azione
     * @param action l'azione scelta
     * @return la stringa codificata del messaggio
     */
    public String encodeAct(Action action)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "ACT");
        toSend.add("action", encodeObject(action));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di partecipazione alla partita
     * @param playerName il nome del giocatore
     * @return la stringa codificata del messaggio
     */
    public String encodeGameJoined(String playerName)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "GAMEJOINED");
        toSend.addProperty("playerName", playerName);
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica un oggetto
     * @param toEncode l'oggetto da codificare
     * @return l'oggetto codificato con json
     */
    public JsonElement encodeObject(Object toEncode)
    {
        JsonElement jsonElement = gson.toJsonTree(toEncode);
        if(jsonElement.isJsonObject())
        {
            jsonElement.getAsJsonObject().addProperty("objectType", toEncode.getClass().getSimpleName());
        }
        return jsonElement;
    }
    
    /**
     * Codifica il messaggio di Ping
     * @return la stringa codificata del messaggio
     */
    public String encodePing() {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "PING");
        return gson.toJson(toSend);    
    }     
}
