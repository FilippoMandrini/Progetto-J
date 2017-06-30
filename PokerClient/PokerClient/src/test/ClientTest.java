package test;

import gui.LoginFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Classe che lancia il Client
 */
public class ClientTest {

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
