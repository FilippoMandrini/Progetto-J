package gui;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Costanti per la GUI
 */
public abstract class GUIConstants {
    
    protected static int MAX_BOARD_CARDS = 5;
    protected static int MAX_HOLE_CARDS = 2;
    protected static int MAX_PLAYERS = 5;
    protected static Color TABLE_COLOR = new Color(0, 102, 51);
    protected static Color CURRENT_COLOR = new Color(255, 0, 0);
    protected static Color STANDARD_COLOR = new Color(255, 205, 51);
    protected static Border PANEL_BORDER = new LineBorder(new Color(95, 63, 43), 4);
}
