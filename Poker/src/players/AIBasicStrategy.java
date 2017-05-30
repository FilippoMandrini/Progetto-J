/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import actions.*;
import gametypes.GameType;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import poker.Board;
import poker.Card;
import poker.Game;
import poker.GameObservable;
import utilities.ChenEvaluator;

/**
 *
 * @author Nickelsilver
 */
public class AIBasicStrategy extends AIStrategy {

    private final int aggressiveness;
    
    private final int discipline;
    
    private List<Card> cards;
    
    private GameType settings;
   
    private int stake;
    
    private int ownBet;

    public AIBasicStrategy(int aggressiveness, int discipline) {
        if (aggressiveness > 100 || aggressiveness < 0 )
        {
            throw new IllegalArgumentException("Aggressività del bot fuori dai limiti");
        }
        if (discipline > 100 || discipline < 0 )
        {
            throw new IllegalArgumentException("Disciplina del bot fuori dai limiti");
        }
        this.aggressiveness = aggressiveness;
        this.discipline = discipline;
    }
    
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions){
        Action action = null;
        if (allowedActions.size() == 1 && allowedActions.contains(ActionSet.CHECK))
        {
            action = new Check();
        }
        else
        {
            double chenScore = ChenEvaluator.evaluate(cards);
            double scoreToPlay = discipline * 0.2;
            if (chenScore < scoreToPlay)
            {
                if (allowedActions.contains(ActionSet.CHECK))
                {
                    action = new Check();
                }
                else
                {
                    action = new Fold();
                }
            }
            else
            {
                if ((chenScore - scoreToPlay) >= ((20.0 - scoreToPlay)/2.0))
                {
                    if (aggressiveness == 0)
                    {
                        if (allowedActions.contains(ActionSet.CALL))
                        {
                            action = new Call();
                        }
                        else
                        {
                            action = new Check();
                        }
                    }
                    else if (aggressiveness == 100)
                    {
                        if (allowedActions.contains(ActionSet.BET))
                        {
                            action = new Bet(stake);
                        }      
                        else if(allowedActions.contains(ActionSet.RAISE))
                        {
                            int toPayAmount = bet - ownBet + this.stake;
                            int raiseAmount = this.stake - bet + ownBet;
                            action = new Raise(raiseAmount);
                        }
                        else if (allowedActions.contains(ActionSet.CALL))
                        {
                            action = new Call();
                        }
                        else
                        {
                            action = new Check();
                        }
                    }
                    else
                    {
                        int amount = minBet;
                        int betIndex = aggressiveness / 20;
                        for (int i = 0; i< betIndex; i++)
                        {
                            amount *=2;
                        }
                        if (bet < amount)
                        {
                            if (allowedActions.contains(ActionSet.BET)) 
                            {
                                action = new Bet(amount);
                            } 
                            else if (allowedActions.contains(ActionSet.RAISE)) 
                            {
                                int toPayAmount = bet - ownBet + amount;
                                int raiseAmount = amount - bet + ownBet;
                                action = new Raise(raiseAmount);
                            } 
                            else if (allowedActions.contains(ActionSet.CALL)) 
                            {
                                action = new Call();
                            } 
                            else 
                            {
                                action = new Check();
                            }
                        }
                        else
                        {
                            if (allowedActions.contains(ActionSet.CALL))
                            {
                                if ((bet-ownBet / 2) < amount)
                                {
                                    action = new Call();
                                }
                                else
                                {
                                    if (allowedActions.contains(ActionSet.FOLD))
                                    {
                                        action = new Fold();
                                    }
                                }
                            }
                            if (allowedActions.contains(ActionSet.CHECK)) 
                            {
                                action = new Check();
                            }
                        }
                    }
                }
                else
                {
                    if (allowedActions.contains(ActionSet.CHECK)) 
                    {
                        action = new Check();
                    }
                    else 
                    {
                        if (allowedActions.contains(ActionSet.CALL))
                        {
                            if ((bet-ownBet / 2) < minBet)
                            {
                                action = new Call();
                            }
                            else
                            {
                                if (allowedActions.contains(ActionSet.FOLD))
                                {
                                    action = new Fold();
                                }
                            }                    
                        }
                    }
                }
            }
        }
        return action;
        
    }

    @Override
    public void boardUpdated(Board board) {
        // non implementato
    }

    @Override
    public void playerUpdated(Player player) {
        // non implementato
    }

    @Override
    public void messageUpdated(String message) {
        // non implementato
    }

    @Override
    public void gameStarted(GameObservable game) {

        if (game instanceof Game)
        {
            this.settings = ((Game)game).getSettings();
        }
        else
        {
            throw new IllegalArgumentException("Classe dell'oggetto observable non corretta");
        }
    }

    @Override
    public void handStarted(Player dealer) {
        this.cards = new ArrayList<>();
    }

    @Override
    public void currentPlayerUpdated(Player currentPlayer) {
        // non implementato
    }

    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        // non implementato
    }

    @Override
    public void selfUpdated(Player player) {
        if (player.getCards().size() == 2)
        {
            this.cards = player.getCards();
            this.stake = player.getStake();
            this.ownBet = player.getCurrentBet();
        }
    }

    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        // non implementato
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
