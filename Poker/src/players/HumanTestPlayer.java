/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.GenericAction;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 *
 * @author Nickelsilver
 */
public class HumanTestPlayer extends HumanPlayer {
    
    public HumanTestPlayer(String name) {
        super(name);
    }
    
    @Override
    public Action act()
    {
        System.out.println(this.toString() + " chiede di agire: ");
        Scanner in  = new Scanner(System.in);
        return new GenericAction(0);
    }
    
}
