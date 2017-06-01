/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import actions.Action;
import gametypes.GameType;
import java.util.List;

/**
 *
 * @author Nickelsilver
 */
public class Game {
    
    private List<Player> players;
    private List<Player> activePlayers;
    private Action currentAction;
    private List<Card> communityCards;
    private int bet;
    private int minBet;
    private int totalPot;
    private GameType settings;

    public Game(GameType settings, List<Player> players) {
        this.settings = settings;
        this.players = players;
    }
    
    public void setActivePlayers()
    {
        for(Player player : players)
        {
            
        }
    }
    
    
    
}
