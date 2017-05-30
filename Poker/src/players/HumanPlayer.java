package players;

import actions.Action;

/**
 * Classe del giocatore umano
 */
public class HumanPlayer extends Player {
    
    /**
     * Costruttore di HumanPlayer
     * @param name nome del giocatore
     * @param client
     */
    
    public HumanPlayer(String name, HumanStrategy client) {
        super(name, client);
    }
    
    
    

//    @Override
//    public Client getClient() {
//        return client; //To change body of generated methods, choose Tools | Templates.
//    }
    

}