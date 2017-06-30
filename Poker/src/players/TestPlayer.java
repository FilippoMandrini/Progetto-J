package players;

/**
 * Classe per un giocatore di prova
 */
public class TestPlayer extends Player {
    
    public TestPlayer(String name) {
        super(name, new HumanTestStrategy());
    } 
}
