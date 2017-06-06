/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import gametypes.GameType;
import java.util.*;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public abstract class GameObservable {

    private final Set<GameObserver> observers = new HashSet<>();

    public synchronized int countObservers() {
        return observers.size();
    }

    public synchronized void deleteObserver(GameObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    public synchronized void addObserver(GameObserver observer) {
        observers.add(observer);

    }

    public void notifyBoardUpdated(Board board) {
        for (GameObserver observer : observers) {
            observer.boardUpdated(board);
        }
    }

    public void notifyMessageUpdated(String message) {
        for (GameObserver observer : observers) {
            observer.messageUpdated(message);
        }
    }

    public synchronized void notifyGameStarted(List<Player> players, GameType settings) {
        for (GameObserver observer : observers) {
            observer.gameStarted(players, settings);

        }
    }

    public void notifyHandStarted(Player dealer) {
        for (GameObserver observer : observers) {
            observer.handStarted(dealer.getShadowCopy());
        }
    }

    public void notifyBettingUpdate(int bet, int minBet, int totalPot) {
        for (GameObserver observer : observers) {
            observer.bettingUpdated(bet, minBet, totalPot);
        }
    }

    public void notifyCurrentPlayerUpdated(Player currentPlayer) {
        for (GameObserver observer : observers) {
            observer.currentPlayerUpdated(currentPlayer.getShadowCopy());
        }
    }

    public void notifyCurrentPlayerActed(Player currentPlayer) {
        for (GameObserver observer : observers) {
            observer.currentPlayerActed(currentPlayer.getShadowCopy());
        }
    }

    public void notifyHiddenPlayersUpdated(List<Player> players) {
        for (Player notifyPlayer : players) {
            for (Player player : players) {
                if (!notifyPlayer.equals(player)) {
                    notifyPlayer.getClient().playerUpdated(player.getShadowCopy());
                } else {
                    notifyPlayer.getClient().selfUpdated(player);
                }
            }
        }
    }

    public void notifyPlayersUpdated(List<Player> players) {
        for (Player notifyPlayer : players) {
            for (Player player : players) {
                notifyPlayer.getClient().playerUpdated(player);
            }
        }
    }

}
