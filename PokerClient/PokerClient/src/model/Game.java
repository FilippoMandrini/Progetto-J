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
    private Board board;
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
            if (player.isActive())
            {
                if (!players.contains(player))
                    activePlayers.add(player);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public int getTotalPot() {
        return totalPot;
    }

    public void setTotalPot(int totalPot) {
        this.totalPot = totalPot;
    }

    public GameType getSettings() {
        return settings;
    }

    public void setSettings(GameType settings) {
        this.settings = settings;
    }
    
    
    
    
    
}
