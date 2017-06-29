/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import actions.Action;
import actions.ActionSet;
import gametypes.GameType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nickelsilver
 */
public class Game extends GameObservable {
    
    private List<Player> players;
    private List<Player> activePlayers;
    private Action currentAction;
    private Board board;
    private int bet;
    private int minBet;
    private int totalPot;
    private GameType settings;
    private Player dealer;
    private int dealerPosition;
    private int currentPlayerPosition;
    private Player currentPlayer;
    private String lastMessage;
    private Set<ActionSet> allowedActions;
    private boolean inTurn;
    private int noOfHands;

    public Game(GameType settings, List<Player> players) {
        this.settings = settings;
        this.players = players;
        noOfHands = 1;
    }
    
    public void setActivePlayers()
    {
        activePlayers.clear();
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

    public Game() {
        this.activePlayers = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    public Player getDealer() {
        return dealer;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    
    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public int getDealerPosition() {
        return dealerPosition;
    }

    public void setDealerPosition(int dealerPosition) {
        this.dealerPosition = dealerPosition;
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void updateBoard(Board board) {
        this.board = board;
        notifyBoardUpdated(board);
    }
    
    public void updateMessage(String message)
    {
        this.lastMessage = message;
        notifyMessageUpdated(message);
    }
    
    public void updateBetting(int bet, int minBet, int totalPot)
    {
        this.bet = bet;
        this.minBet = minBet;
        this.totalPot = totalPot;
        notifyBettingUpdate(bet, minBet, totalPot);
    }
    
    public void updateGameStarted(GameType settings, List<Player> players)
    {
        this.settings = settings;
        this.players = players;
        setActivePlayers();
        notifyGameStarted(players, settings);
    }
    
    public void updatePlayer(Player player)
    {
        int itemIndex = players.indexOf(player);
        if (itemIndex != -1) {
            players.set(itemIndex, player);
        }
        itemIndex = activePlayers.indexOf(player);
        if (itemIndex != -1) {
            activePlayers.set(itemIndex, player);
        }
        notifyPlayerUpdated(player);
    }
    
    public void updateCurrentAction(Player currentPlayer)
    {
        this.currentAction = currentPlayer.getLastAction();
        this.currentPlayer = currentPlayer;
        notifyCurrentPlayerActed(currentPlayer);
    }
    
    public void updateCurrentPlayer(Player player, int currentPlayerPosition)
    {
        if (players.contains(player))
        {
            currentPlayer = player;
            this.currentPlayerPosition = currentPlayerPosition;
            notifyCurrentPlayerUpdated(currentPlayer, currentPlayerPosition);

        }
    }
    
    public void updateDealer(Player dealer, int dealerPosition)
    {
        if (players.contains(dealer))
        {
            this.dealer = dealer;
            this.dealerPosition = dealerPosition;
            notifyHandStarted(dealer, dealerPosition);
        }
    }

    public void actionRequested(int bet, int minBet, Set<ActionSet> allowedActions)
    {
        this.inTurn = true;
        this.bet = bet;
        this.minBet = minBet;
        this.allowedActions = allowedActions;
        notifyActionRequest(bet, minBet, allowedActions);
    }

    public boolean isInTurn() {
        return inTurn;
    }

    public void setInTurn(boolean isInTurn) {
        this.inTurn = isInTurn;
    }

    public void updateHandStarted(Player dealer, int dealerPosition) {
        updateDealer(dealer, dealerPosition);
        noOfHands++;
    }

    public int getNoOfHands() {
        return noOfHands;
    }
    
    
    
    
    
}
    
