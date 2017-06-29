package server;

import poker.Game;

/**
 * Classe del Game (Runnable)
 */
public class GameRunnable implements Runnable {
    
    private final Game game;
    private final ConnectionChecker checker;
    private final Thread gameThread;
    private final Thread connectionThread;

    /**
     * Costruttore di GameRunnable
     * @param game la partita
     */
    public GameRunnable(Game game) {
        this.game = game;
        checker = new ConnectionChecker(game);
        gameThread = new Thread(game);
        connectionThread = new Thread(checker);
    }
    
    /**
     * Lancia in esecuzione la partita
     */
    @Override
    public void run() {
        gameThread.start();
        connectionThread.start(); 
        try {
            gameThread.join();
        } catch (InterruptedException ex) {
            // niente
        }
        checker.setIsGameRunning(false);
    }
    
}