/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import actions.*;
import annotations.JSONExclude;
import actions.ActionSet;
import com.google.gson.*;
import java.util.Set;
import model.Board;
/**
 *
 * @author Nickelsilver
 */
public class JSONEncoder {

    private static JSONEncoder instance;
    private Gson gson;
   
    private JSONEncoder(){
        
        gson = new GsonBuilder()
                .setPrettyPrinting()
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
    
    public String encodeAct(Action action)
    {
        JsonObject toSend = new JsonObject();
        toSend.addProperty("methodInvoked", "ACT");
        toSend.add("action", encodeObject(action));
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
    
    
}
