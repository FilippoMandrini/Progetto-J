/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.ActionSet;
import java.net.Socket;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author Nickelsilver
 */
public abstract class HumanStrategy implements Client {

    
    protected Socket socket;

    public HumanStrategy(Socket socket) {
        this.socket = socket;
    }
    
    

}