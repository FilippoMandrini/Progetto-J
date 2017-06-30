package gui;

import javax.swing.JPanel;
import model.Game;

/**
 * Pannello del gioco
 */
public class GamePanel extends JPanel{
    
    protected Game game;

    /**
     * Costruttore di GamePanel
     * @param game la partita
     */
    public GamePanel(Game game) {
        this.game = game;
        setBackground(GUIConstants.TABLE_COLOR);
    }
    
}