package gui;

import javax.swing.JPanel;
import model.Game;

/**
 * Pannello del gioco
 */
public class GamePanel extends JPanel{
    
    protected Game game;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(GUIConstants.TABLE_COLOR);
    }
    
}