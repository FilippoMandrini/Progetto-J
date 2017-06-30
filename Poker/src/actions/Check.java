package actions;

import poker.GameFacade;

/**
 * Classe che rappresenta il Check
 */
public class Check extends Action{
    
    /**
     * Costruttore della classe Check
     */
    public Check() {
        super("Check", "checka");
        this.actionType = ActionSet.CHECK;
    }

    @Override
    /**
     * Esegue il Check
     * @param game la partita
     * @param playersLeft il numero di giocatori che devono ancora agire
     * @return il numero di giocatori che devono ancora agire dopo questa azione
     */
    public int execute(GameFacade game, int playersLeft) {
        return playersLeft -1;
    }
}
