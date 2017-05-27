/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.Action;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.GenericAction;
import actions.Raise;
import actions.SmallBlind;
import java.util.Scanner;

/**
 *
 * @author Nickelsilver
 */
public class TestHumanStrategy extends HumanStrategy {
    
    @Override
    public Action act()
    {
        System.out.println(this.toString() + " chiede di agire: ");
        Scanner in  = new Scanner(System.in);
        if(in.hasNextLine())
        {
            String result = in.nextLine();
            String[] tokens = result.split(" ");
            if (tokens[0].equalsIgnoreCase("H"))
            {
                return new Check();
            }
            if (tokens[0].equalsIgnoreCase("R"))
            {
                return new Raise(Integer.parseInt(tokens[1]));
            }
            if (tokens[0].equalsIgnoreCase("C"))
            {
                return new Call();
            }
            if (tokens[0].equalsIgnoreCase("T"))
            {
                return new Bet(Integer.parseInt(tokens[1]));

            }
            if (tokens[0].equalsIgnoreCase("B"))
            {
                return new BigBlind(0);
            }
            if (tokens[0].equalsIgnoreCase("S"))
            {
                return new SmallBlind(0);
            }
            if (tokens[0].equalsIgnoreCase("F"))
            {
                return new Fold();
            }
        }
        return new GenericAction(0);
    }    
}

  
