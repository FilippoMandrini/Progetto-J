package players;

import actions.Action;
import actions.ActionSet;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.Raise;
import actions.SmallBlind;
import gametypes.GameType;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import poker.Board;

/**
 * Classe di test del giocatore umano
 */
public class HumanTestStrategy extends HumanStrategy {

    /**
     * Costruttore della classe
     */
    public HumanTestStrategy() {
        super();
    }
    
    /**
     * Determina l'azione da eseguire
     * @param minBet la puntata minima
     * @param bet la puntata
     * @param allowedActions le azioni consentite
     * @return l'azione richiesta
     */  
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions)
    {            
        Action action = null;
        System.out.println(this.toString() + " chiede di agire: ");
        Scanner in  = new Scanner(System.in);
        if(in.hasNextLine())
        {
            do {
                String result = in.nextLine();
                String[] tokens = result.split(" ");
                if (tokens[0].equalsIgnoreCase("H")) {
                    action = new Check();
                }
                if (tokens[0].equalsIgnoreCase("R")) {
                    action = new Raise(Integer.parseInt(tokens[1]));
                }
                if (tokens[0].equalsIgnoreCase("C")) {
                    action =  new Call();
                }
                if (tokens[0].equalsIgnoreCase("T")) {
                    action = new Bet(Integer.parseInt(tokens[1]));
                }
                if (tokens[0].equalsIgnoreCase("B")) {
                    action = new BigBlind(0);
                }
                if (tokens[0].equalsIgnoreCase("S")) {
                    action = new SmallBlind(0);
                }
                if (tokens[0].equalsIgnoreCase("F")) {
                    action = new Fold();
                }
            } while (!allowedActions.contains(action.getActionType()));
        }
        return action;
    }

    /** {@inheritDoc} */    
    @Override
    public void boardUpdated(Board board) {
        
    }
    
    /** {@inheritDoc} */
    @Override
    public void playerUpdated(Player player) {

    }
    
    /** {@inheritDoc} */
    @Override
    public void messageUpdated(String message) {

    }

    /** {@inheritDoc} */    
    @Override
    public void handStarted(Player dealer, int dealerPosition) {

    }

    /** {@inheritDoc} */    
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {

    }

    /** {@inheritDoc} */    
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {

    }

    /** {@inheritDoc} */    
    @Override
    public void selfUpdated(Player player) {

    }

    /** {@inheritDoc} */    
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        
    }

    /** {@inheritDoc} */    
    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Restituisce un valore booleano che indica se è connesso
     * @return true se connesso, false altrimenti
     */
    @Override
    public boolean isConnected() {
        return true;
    }

    /**
     * Restituisce un valore booleano che indica se è raggiungibile
     * @param timeout il tempo per il timeout
     * @return true se raggiungibile, false altrimenti
     */
    @Override
    public boolean isReachable(int timeout) {
        return true;
    }

    /** {@inheritDoc} */  
    @Override
    public void setConnected(boolean connected) {
        //non implementato
    }

    /** {@inheritDoc} */  
    @Override
    public void disconnect() {
        // non implementato
    }

    /** {@inheritDoc} */  
    @Override
    public void ping() {
        // non implementato
    }

     /**
     * Restituisce un valore booleano che indica se è bloccato
     * @return true se bloccato, false altrimenti
     */
    @Override
    public boolean isBlocked() {
        return false;
    }
    
}
