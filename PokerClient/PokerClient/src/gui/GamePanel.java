/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;
import model.Game;

/**
 *
 * @author Nickelsilver
 */
public class GamePanel extends JPanel{
    
    protected Game game;

    public GamePanel(Game game) {
        this.game = game;
    }
    
}
