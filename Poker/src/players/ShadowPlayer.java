/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

/**
 *
 * @author Nickelsilver
 */
public class ShadowPlayer extends Player{
    
    private boolean hasCards;
    
    public ShadowPlayer(String name, int stake) {
        super(name, stake, null);   
        
    }

    public boolean hasCards() {
        return hasCards;
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
    }
    
    
    
    
    
    
}
