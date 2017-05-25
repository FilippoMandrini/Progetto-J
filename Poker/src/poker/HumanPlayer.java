package poker;

/**
 * Classe del giocatore umano
 */
public class HumanPlayer extends Player implements Client {
    
    private String password;

    /**
     * Costruttore di HumanPlayer
     * @param name nome del giocatore
     * @param password password del giocatore
     */
    public HumanPlayer(String name, String password) {
        super(name);
        this.password = password;
    }

    /**
     * Ritorna la password
     * @return la password
     */
    public String getPassword() {
        return password;
    }
}