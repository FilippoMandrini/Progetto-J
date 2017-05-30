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
        
        Player A1 = new Player("Bot_1", new AIBasicStrategy(80, 10));
        Player A2 = new Player("Bot_2", new AIBasicStrategy(70, 10));
        Player A3 = new Player("Bot_3", new AIBasicStrategy(90, 10));
//        HumanPlayer H2 = new TestPlayer("Alice");
//        H2.setStake(1000);
//        HumanPlayer H3 = new TestPlayer("Luca");
//        H3.setStake(1000);
        Game MAIN = new Game(new StandardGame(5000));
        MAIN.addPlayer(A1);
        MAIN.addObserver(A1.getClient());
        MAIN.addPlayer(A2);
        MAIN.addObserver(A2.getClient());
        MAIN.addPlayer(A3);
        MAIN.addObserver(A3.getClient());
        MAIN.playGame();
    }
    
}
