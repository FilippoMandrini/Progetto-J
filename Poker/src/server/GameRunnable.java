/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import poker.Game;

/**
 *
 * @author Nickelsilver
 */
public class GameRunnable implements Runnable {
    
    private final Game game;
    private final ConnectionChecker checker;
    private final Thread gameThread;
    private final Thread connectionThread;

    public GameRunnable(Game game) {
        this.game = game;
        checker = new ConnectionChecker(game);
        gameThread = new Thread(game);
        connectionThread = new Thread(checker);
    }
    
    @Override
    public void run() {
        connectionThread.start(); 
        gameThread.start();
        try {
            gameThread.join();
        } catch (InterruptedException ex) {
            // niente
        }
        checker.setIsGameRunning(false);
    }
    
    
    
}
