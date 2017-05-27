package players;

import actions.Action;

/**
 * Classe del giocatore umano
 */
public class HumanPlayer extends Player implements Client {
    
    /**
     * Costruttore di HumanPlayer
     * @param name nome del giocatore
     * @param password password del giocatore
     */
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Giocatore: " + name;
    }

    @Override
    public Action act() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}