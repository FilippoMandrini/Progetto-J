/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Nickelsilver
 */
public abstract class GUIConstants {
    
    protected static int MAX_PLAYERS = 5;
    protected static Color TABLE_COLOR = new Color(0, 128, 0); // Dark green
    protected static Color TEXT_COLOR = Color.GREEN;
    protected static Border LABEL_BORDER = new LineBorder(Color.BLACK, 1);
    protected static Border PANEL_BORDER = new CompoundBorder(new LineBorder(Color.BLACK, 1), new EmptyBorder(10, 10, 10, 10));    
}
