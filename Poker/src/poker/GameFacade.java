/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import gametypes.GameType;
import java.util.List;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public class GameFacade {

    private final Game game;

    public GameFacade(Game game) {
        this.game = game;
    }

    public void addPlayer(Player player) {
        game.addPlayer(player);
    }

    public List<Player> getActivePlayers() {
        return game.getActivePlayers();
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public GameType getSettings() {
        return game.getSettings();
    }

    public boolean hasPlayers() {
        return game.hasPlayers();
    }

    public PotHandler getPotHandler() {
        return game.getPotHandler();
    }

    public int getCurrentPlayerPosition() {
        return game.getCurrentPlayerPosition();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public int getBet() {
        return game.getBet();
    }

    public int getMinBet() {
        return game.getMinBet();
    }

    public int getCurrentBigBlind() {
        return game.getCurrentBigBlind();
    }

    public int getRaises() {
        return game.getRaises();
    }

    public void setBet(int bet) {
        game.setBet(bet);
    }

    public void setMinBet(int bet) {
        game.setMinBet(bet);
    }

    public void winLastActivePlayer() {
        getActivePlayers().get(0).win(getPotHandler().getTotalPot());
    }

    public void decreaseCurrentPlayerPosition() {
        setCurrentPlayerPosition(getCurrentPlayerPosition() - 1);
    }

    public void increaseCurrentPlayerPosition() {
        setCurrentPlayerPosition(getCurrentPlayerPosition() + 1);
    }

    public void setCurrentPlayerPosition(int newPos) {
        game.setCurrentPlayerPosition(newPos);
    }

    public void foldCurrentPlayer() {
        removeCurrentPlayerFromActive();
        getCurrentPlayer().foldCards();
        decreaseCurrentPlayerPosition();
    }

    public void removeCurrentPlayerFromActive() {
        removeFromActive(getCurrentPlayer());
    }

    public void removeFromActive(Player player) {
        if (getActivePlayers().contains(player)) {
            getActivePlayers().remove(player);
        }
    }

    public int getNoOfActive() {
        return getActivePlayers().size();
    }

    public int getCurrentPlayerStake() {
        return getCurrentPlayer().getStake();
    }

    public int getCurrentPlayerBet() {
        return getCurrentPlayer().getCurrentBet();
    }

    public void setCurrentPlayerBet(int amount) {
        getCurrentPlayer().setCurrentBet(amount);
    }

    public void payCurrentPlayer(int amount) {
        getCurrentPlayer().pay(amount);
        getPotHandler().addToPot(amount, getCurrentPlayer());
    }

    public void setCurrentAsLastAggressor() {
        setLastAggressor(getCurrentPlayer());
    }

    public void setLastAggressor(Player player) {
        game.setLastAggressor(player);
    }
}