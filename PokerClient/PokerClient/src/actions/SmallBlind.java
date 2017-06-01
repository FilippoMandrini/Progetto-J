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
public class SmallBlind extends Action {

    public SmallBlind(int amount) {
        super("Small Blind", "paga small blind", amount);
        this.actionType = ActionSet.SMALL_BLIND;
     
    }

}
