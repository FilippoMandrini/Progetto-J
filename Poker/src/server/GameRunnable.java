/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import poker.Game;

/**
 *
 * @author Nickelsilver
 */
public class GameRunnable implements Runnable {
    
    private Game game;
    private ConnectionChecker checker;

    public GameRunnable(Game game) {
        this.game = game;
        this.checker = new ConnectionChecker(game);
    }
    
    @Override
    public void run() {
        Thread gameThread = new Thread(game);
        gameThread.start();
        Thread clientChecker = new Thread(checker);
        clientChecker.start();  
    }
    
    
    
}
