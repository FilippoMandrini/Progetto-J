package actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Classe Factory per le azioni
 */
public class ActionFactory {

    /**
     * Crea una Action
     * @param type il tipo dell'azione
     * @param amount la quantità della scommessa
     * @return la azione creata
     * @throws NoSuchMethodException quando si verificano errori durante la creazione dell'azione
     */
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
