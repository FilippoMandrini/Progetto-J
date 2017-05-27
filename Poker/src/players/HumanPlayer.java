package players;

import actions.Action;

/**
 * Classe del giocatore umano
 */
public class HumanPlayer extends Player {
    
    protected HumanStrategy client;
    /**
     * Costruttore di HumanPlayer
     * @param name nome del giocatore
     * @param password password del giocatore
     */
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Client getClient() {
        return client; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public String toString() {
        return "Giocatore: " + name;
    }
}