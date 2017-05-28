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
public enum ActionSet {
    
    CALL("Call"),
    RAISE("Raise"),
    BET("Bet"),
    CHECK("Check"),
    FOLD("Fold"),
    BIG_BLIND("Big Blind"),
    SMALL_BLIND("Small Blind");
    
    private String name;
    
    ActionSet(String name)
    {
        this.name = name;
    }
    
}
