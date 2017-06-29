/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gui.LoginFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Nickelsilver
 */
public class ClientTest {

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
