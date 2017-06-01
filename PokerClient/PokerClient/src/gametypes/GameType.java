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
public abstract class GameType {
    
    protected int maxPlayers;
    protected int maxRaises;
    protected int bigBlind;
    protected boolean isBigBlindFixed;
    protected int roundsForDoubling;
    protected boolean alwaysDoShowdown;
    protected int startingStake;
    
    public abstract String getDescription();
    
    public abstract String toString();
    
    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMaxRaises() {
        return maxRaises;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public boolean isIsBigBlindFixed() {
        return isBigBlindFixed;
    }

    public int getStartingStake() {
        return startingStake;
    }

    public void setStartingStake(int startingStake) {
        this.startingStake = startingStake;
    }
    
    
}
