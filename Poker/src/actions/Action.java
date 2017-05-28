/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

/**
 *
 * @author Ludovico
 */
public abstract class Action {
  
    protected String name;
    protected String description;
    protected int amount;
    protected ActionSet actionType;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ActionSet getActionType() {
        return actionType;
    }
    
    
    
    

    public Action(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Action(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
    
    
    
    
    
    
}

