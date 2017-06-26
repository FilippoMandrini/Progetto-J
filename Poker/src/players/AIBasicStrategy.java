package players;

import actions.*;
import gametypes.GameType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import poker.Board;
import poker.Card;
import utilities.ChenEvaluator;

/**
 *Classe che implementa la strategia del bot
 */
public class AIBasicStrategy extends AIStrategy {

    private final int aggressiveness;
    private final int discipline;    
    private List<Card> cards;
    private int stake;  
    private int ownBet;
    private GameType settings;

    /**
     * Costruttore della classe
     * @param aggressiveness l'aggressivita' (la tendenza a raisare/callare)
     * @param discipline  la disciplina ( la tendenza a checkare/foldare)
     */
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
    
    /** {@inheritDoc} */    
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
                    System.out.println("[BOTTEST] Carte ottime");
                    switch (aggressiveness) {
                        case 0:
                            if (allowedActions.contains(ActionSet.CALL)) 
                            {
                                action = new Call();
                            }
                            else 
                            {
                                action = new Check();
                            }   
                            break;
                        case 100:
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
                            break;
                        default:
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
                                    if (amount > stake)
                                    {
                                        amount = stake;
                                    }
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
                                    action = new Call();
                                }
                                if (allowedActions.contains(ActionSet.CHECK))
                                {
                                    action = new Check();
                                }
                            }
                            break;
                    }
                }
                else
                {   
                    System.out.println("[BOTTEST] Carte decenti");
                    if (allowedActions.contains(ActionSet.CHECK)) 
                    {
                        action = new Check();
                    }
                    else 
                    {
                        if (allowedActions.contains(ActionSet.CALL))
                        {                         
                            action = new Call();  
                        }

                    }
                }
            }
        }
        return action;
    }
    
    /** {@inheritDoc} */
    @Override
    public void boardUpdated(Board board) {
        // non implementato
    }

    /** {@inheritDoc} */    
    @Override
    public void playerUpdated(Player player) {
        // non implementato
    }

    /** {@inheritDoc} */    
    @Override
    public void messageUpdated(String message) {
        // non implementato
    }

    /** {@inheritDoc} */    
    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        this.settings = settings;
    }

    /** {@inheritDoc} */    
    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        this.cards = new ArrayList<>();
    }

    /** {@inheritDoc} */    
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        // non implementato
    }

    /** {@inheritDoc} */    
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        // non implementato
    }

    /** {@inheritDoc} */    
    @Override
    public void selfUpdated(Player player) {
        if (player.getCards().size() == 2)
        {
            this.cards = player.getCards();
            this.stake = player.getStake();
            this.ownBet = player.getCurrentBet();
        }
    }

    /** {@inheritDoc} */    
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        // non implementato
    }



    
}