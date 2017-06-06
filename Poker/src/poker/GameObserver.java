/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import gametypes.*;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import players.Player;
import players.ShadowPlayer;

/**
 *
 * @author Nickelsilver
 */
public abstract interface GameObserver {

    public void boardUpdated(Board board);
    
    public void playerUpdated(Player player);
    
    public void messageUpdated(String message);
    
    public void gameStarted(List<Player> players, GameType settings);
    
    public void handStarted(Player dealer);
    
    public void currentPlayerUpdated(Player currentPlayer);
    
    public void bettingUpdated(int bet, int minBet, int totalPot);
    
    public void selfUpdated(Player player);

    public void currentPlayerActed(ShadowPlayer shadowCopy);
    
    
}
