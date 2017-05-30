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
        
        HumanPlayer H1 = new HumanTestPlayer("Luca");
        AIPlayer A1 = new AIPlayer("Bot_1", new AIBasicStrategy(0, 50));
        AIPlayer A2 = new AIPlayer("Bot_2", new AIBasicStrategy(100, 50));
//        HumanPlayer H2 = new HumanTestPlayer("Alice");
//        H2.setStake(1000);
//        HumanPlayer H3 = new HumanTestPlayer("Luca");
//        H3.setStake(1000);
        Game MAIN = new Game(new StandardGame(5000));
        MAIN.addPlayer(H1);
        MAIN.addObserver(H1.getClient());
        MAIN.addPlayer(A1);
        MAIN.addObserver(A1.getClient());
        MAIN.addPlayer(A2);
        MAIN.addObserver(A2.getClient());
        MAIN.playGame();
    }
    
}
