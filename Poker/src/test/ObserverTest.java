/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import actions.Action;
import actions.Bet;
import java.util.ArrayList;
import players.TestPlayer;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public class ObserverTest {
    public static void main(String[] args) {
        Player P1 = new TestPlayer("Luca");
        P1.setLastAction(new Bet(100));
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(P1.getLastAction());
        actions.get(0).setAmount(200);
        System.out.println(P1.getLastAction().getAmount());
    }
}
