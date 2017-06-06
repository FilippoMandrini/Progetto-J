/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.ActionSet;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.GenericAction;
import actions.Raise;
import actions.SmallBlind;
import gametypes.GameType;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;
import poker.Board;
import poker.GameObservable;

/**
 *
 * @author Nickelsilver
 */
public class HumanTestStrategy extends HumanStrategy {

    public HumanTestStrategy() {
        super(null);
    }
    
    
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

    @Override
    public void boardUpdated(Board board) {
        
    }

    @Override
    public void playerUpdated(Player player) {

    }

    @Override
    public void messageUpdated(String message) {

    }

    @Override
    public void handStarted(Player dealer) {

    }

    @Override
    public void currentPlayerUpdated(Player currentPlayer) {

    }

    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {

    }

    @Override
    public void selfUpdated(Player player) {

    }

    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        
    }

    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
