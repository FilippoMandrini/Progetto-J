/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametypes;

/**
 *
 * @author Nickelsilver
 */
public class CustomGame extends GameType{

    @Override
    public String getDescription() {  
        return "Variante personalizzata del poker nella sua versione HoldEm";
    }

    @Override
    public String toString() {
        return "Big Blind di base: " + this.bigBlind + "\nIl Big Blind raddoppia: " + !this.isBigBlindFixed + "\nMax giocatori: " + this.maxPlayers +"\nMax numero di raise: " + this.maxRaises;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setMaxRaises(int maxRaises) {
        this.maxRaises = maxRaises;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    public void setIsBigBlindFixed(boolean isBigBlindFixed) {
        this.isBigBlindFixed = isBigBlindFixed;
    }

    public int getRoundsForDoubling() {
        return roundsForDoubling;
    }

    public void setRoundsForDoubling(int roundsForDoubling) {
        
        if(isBigBlindFixed)
        {
            throw new IllegalArgumentException("Parameter cannot be changed");
        }
        this.roundsForDoubling = roundsForDoubling;
    }

    public boolean isAlwaysDoShowdown() {
        return alwaysDoShowdown;
    }

    public void setAlwaysDoShowdown(boolean alwaysDoShowdown) {
        this.alwaysDoShowdown = alwaysDoShowdown;
    }

    public CustomGame() {
        
    }
    
    
}
