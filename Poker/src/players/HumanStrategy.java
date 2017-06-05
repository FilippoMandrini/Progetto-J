/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.ActionSet;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nickelsilver
 */
public abstract class HumanStrategy implements Client {

    
    protected Socket socket;
    protected Scanner in = null;
    protected PrintStream out = null;

    public HumanStrategy(Socket socket) {
        this.socket = socket;
        try {
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
        } catch (IOException ex) {
        }   
    }
    
    

}