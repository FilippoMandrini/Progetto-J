/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gametypes.StandardGame;
import players.*;
import poker.Game;

/**
 *
 * @author Nickelsilver
 */
public class GameTest {
    
    public static void main(String[] args) {
        
        HumanPlayer H1 = new HumanTestPlayer("Filippo");
        H1.setStake(500);
        HumanPlayer H2 = new HumanTestPlayer("Alice");
        H2.setStake(1500);
        HumanPlayer H3 = new HumanTestPlayer("Luca");
        H3.setStake(500);
        Game MAIN = new Game(new StandardGame(5000));
        MAIN.addPlayer(H1);
        MAIN.addPlayer(H2);
        MAIN.addPlayer(H3);
        MAIN.playGame();
    }
    
}
