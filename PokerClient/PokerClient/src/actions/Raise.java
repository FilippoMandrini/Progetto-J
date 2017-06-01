/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

/**
 *
 * @author Nickelsilver
 */
public class Raise extends Action {
    
    public Raise(int amount) {
        super("Raise", "rilancia", amount);
        this.actionType = ActionSet.RAISE;

    }
    
}
