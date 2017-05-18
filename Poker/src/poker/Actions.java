/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author Ludovico
 */
public class Actions {

    public boolean check(Player player) {

        return true;

    }

    public boolean raise(Player player, int amount) {
        return true;

    }

    public boolean call(Player player,int amount){
        return true;
    }    
    
    public boolean fold(Player player){
        player.setActive(false);
        return true;
    }
}
