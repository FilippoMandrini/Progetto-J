/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.ActionSet;
import java.net.Socket;
import java.util.Observable;
import java.util.Set;
import poker.Board;
import poker.GameObservable;

/**
 *
 * @author Nickelsilver
 */
public class HumanCompleteStrategy extends HumanStrategy{

    public HumanCompleteStrategy(Socket socket) {
        super(socket);
    }
    
    @Override
    public Action act(Set<ActionSet> allowedActions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void boardUpdated(Board board) {

    }

    @Override
    public void playerActionUpdated(Player player) {

    }

    @Override
    public void messageUpdated(String message) {

    }

    @Override
    public void gameStarted(GameObservable game) {

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
    public void update(Observable o, Object arg) {

    }
    
}
