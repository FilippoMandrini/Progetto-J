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
     * @param minBet la puntata minima
     * @param bet la puntata
     * @param allowedActions le azioni consentite
     * @return la stringa codificata del messaggio
     */
    public String encodeAct(int minBet, int bet, Set<ActionSet> allowedActions)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "ACT");
        toSend.addProperty("minBet", minBet);
        toSend.addProperty("bet", bet);
        toSend.add("allowedActions", encodeObject(allowedActions));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento del tavolo
     * @param board il tavolo
     * @return la stringa codificata del messaggio
     */
    public String encodeBoardUpdated(Board board)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "BOARDUPDATED");
        toSend.add("board", encodeObject(board));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento del giocatore
     * @param player il giocatore
     * @return la stringa codificata del messaggio
     */
    public String encodePlayerUpdated(Player player)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "PLAYERUPDATED");
        toSend.add("player", encodeObject(player));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento del giocatore attuale
     * @param currentPlayer il giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     * @return la stringa codificata del messaggio
     */
    public String encodeCurrentPlayerUpdated(Player currentPlayer, int currentPlayerPosition)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "CURRENTPLAYERUPDATED");
        toSend.add("currentPlayer", encodeObject(currentPlayer));
        toSend.addProperty("currentPlayerPosition", currentPlayerPosition);
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento
     * @param message il messaggio
     * @return la stringa codificata del messaggio
     */
    public String encodeMessageUpdated(String message)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "MESSAGEUPDATED");
        toSend.addProperty("message", message);
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento della puntata
     * @param bet la puntata
     * @param minBet la puntata minima
     * @param totalPot il valore del pot complessivo
     * @return la stringa codificata del messaggio
     */
    public String encodeBettingUpdated(int bet, int minBet, int totalPot)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "BETTINGUPDATED");
        toSend.addProperty("bet", bet);
        toSend.addProperty("minBet", minBet);
        toSend.addProperty("totalPot", totalPot);
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di inizio della mano
     * @param dealer il giocatore dealer
     * @param dealerPosition la posizione del giocatore dealer
     * @return la stringa codificata del messaggio
     */
    public String encodeHandStarted(Player dealer, int dealerPosition)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "HANDSTARTED");
        toSend.add("dealer", encodeObject(dealer));
        toSend.addProperty("dealerPosition", dealerPosition);
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di aggiornamento del giocatore stesso
     * @param player il giocatore stesso
     * @return la stringa codificata del messaggio
     */
    public String encodeSelfUpdated(Player player)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "SELFUPDATED");
        toSend.add("self", encodeObject(player));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio dell'azione del giocatore attuale
     * @param shadowCopy una copia del giocatore
     * @return la stringa codificata del messaggio
     */
    public String encodeCurrentPlayerActed(Player shadowCopy)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "CURRENTPLAYERACTED");
        toSend.add("shadowCopy", encodeObject(shadowCopy));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di inizio della partita
     * @param players la lista dei giocatori
     * @param settings le impostazioni della partita
     * @return la stringa codificata del messaggio
     */
    public String encodeGameStarted(List<Player> players, GameType settings)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "GAMESTARTED");
        toSend.add("players", encodeObject(players));
        toSend.add("settings", encodeObject(settings));
        return gson.toJson(toSend);
    }
    
    /**
     * Codifica il messaggio di partecipazione alla partita
     * @return la stringa codificata del messaggio
     */
    public String encodeGameJoined()
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "GAMEJOINED");
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
     * Codifica il messaggio di disconnessione
     * @return la stringa codificata del messaggio
     */
    public String encodeDisconnect() {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "DISCONNECT");
        return gson.toJson(toSend);    
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