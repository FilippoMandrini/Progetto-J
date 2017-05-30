/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.HashSet;
import java.util.List;
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
    
    public void notifyBoardUpdated(Board board)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.boardUpdated(board);
            }
        }
        clearChanged();
    }
    
    public void notifyMessageUpdated(String message)
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
    
    public void notifyHandStarted(Player dealer)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.handStarted(dealer.getShadowCopy());
            }
        }
        clearChanged();
    }
    
    public void notifyBettingUpdate(int bet, int minBet, int totalPot)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.bettingUpdated(bet, minBet, totalPot);
            }
        }
        clearChanged();
    }
    
    public void notifyCurrentPlayerUpdated(Player currentPlayer)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.currentPlayerUpdated(currentPlayer.getShadowCopy());
            }
        }
        clearChanged();
    }
    
    public void notifyCurrentPlayerActed(Player currentPlayer)
    {
        if (hasChanged()) {
            for (GameObserver observer : observers) {
                observer.currentPlayerActed(currentPlayer.getShadowCopy());
            }
        }
        clearChanged();
    }
    
    public void notifyHiddenPlayersUpdated(List<Player> players)
    {
        for (Player notifyPlayer : players)
        {
            if (hasChanged()) {
                for (Player player : players)
                {
                    if(!notifyPlayer.equals(player))
                    {
                        notifyPlayer.getClient().playerUpdated(player.getShadowCopy());
                    }
                    else
                    {
                        notifyPlayer.getClient().selfUpdated(player);
                    }
                }
            }
        }
        clearChanged();
    }

    public void notifyPlayersUpdated(List<Player> players)
    {
        for (Player notifyPlayer : players)
        {
            if (hasChanged()) {
                for (Player player : players)
                {
                    notifyPlayer.getClient().playerUpdated(player);                    
                }
            }
        }
        clearChanged();
    }

    
    // implementazione stupida, correggere il resto
    @Override
    protected synchronized void clearChanged() {
        setChanged(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
