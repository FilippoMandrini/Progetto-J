/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Nickelsilver
 */
public class ActionFactory {


    public Action createAction(ActionSet type, int amount) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException
    {
        Class<?> c = Class.forName("actions." + type.getName());
        Constructor<?> cons = null;
        Action action;
        try {
            cons = c.getConstructor(int.class);
            action = (Action)cons.newInstance(amount);
        } catch (NoSuchMethodException ex) {
            try {
                cons = c.getConstructor();
            } catch (NoSuchMethodException ex1) {
                System.err.println("Errore creazione classe con factory");
            }
            action = (Action) cons.newInstance();       
        }
        return action;
    }
}
