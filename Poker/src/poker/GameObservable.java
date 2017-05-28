/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public abstract class GameObservable extends Observable {
    
    private final Set<GameObserver> observers = new HashSet<>(); 

    @Override
    public synchronized int countObservers() {
        return observers.size();
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged(); 
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged(); 
    }

    @Override
    public synchronized void deleteObservers() {
        observers.clear();
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        if (!(o instanceof GameObserver))
        {
            throw new IllegalArgumentException("Observer non della classe giusta");
        }
        GameObserver observer = (GameObserver)o;
        if (observers.contains(observer))
        {
            observers.remove(observer);
        }

    }

    @Override
    public synchronized void addObserver(Observer o) {
        if (!(o instanceof GameObserver))
        {
            throw new IllegalArgumentException("Observer non della classe giusta");
        }
        GameObserver observer = (GameObserver)o;
        observers.add(observer);
        
    }
    
    public synchronized void notifyBoardUpdated(Board board)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.boardUpdated(board);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyPlayerUpdated(Player player)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.playerActionUpdated(player);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifySelfUpdated(Player player)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.selfUpdated(player);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyMessageUpdated(String message)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.messageUpdated(message);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyGameStarted()
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.gameStarted(this);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyHandStarted(Player dealer)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.handStarted(dealer);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyBettingUpdate(int bet, int minBet, int totalPot)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.bettingUpdated(bet, minBet, totalPot);
            }
        }
        clearChanged();
    }
    
    public synchronized void currentPlayerUpdated(Player currentPlayer)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.currentPlayerUpdated(currentPlayer);
            }
        }
        clearChanged();
    }
    
    public synchronized void notifyPlayersUpdated(ArrayList<Player> players)
    {
        if (hasChanged()) {
            for (Player player : players)
            {
                notifyPlayerUpdated(player);
            }
        }
        clearChanged();
    }
    
    
    
}
