/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import handtypes.Hand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public class PotHandler {
    
    private final List<Pot> pots;
    
    public PotHandler() {
        this.pots = new ArrayList<>();
    }
    
    /**
     * Ritorna il quantitativo totale delle puntate
     * @return quantitativo totale delle puntate
     */
    public int getTotalPot()
    {
        int total = 0;
        for (Pot pot : pots)
        {
            total+=pot.getValue();
        }
        return total;
    }
    
    /**
     * Aggiunge la puntata del giocatore al pot complessivo 
     * Crea un nuovo oggetto pot se si verifica un raise o se un giocatore va in all-in
     * senza comprire totalmente la puntata attuale 
     * @param amount valore versato nel pot complessivo
     * @param currentPlayer giocatore che deve pagare il pot
     */
    public void addToPot(int amount, Player currentPlayer)
    {
        for (Pot pot : pots)
        {
            if (!pot.hasMember(currentPlayer))
            {
                int currentPotBet = pot.getBet();
                if (amount >= currentPotBet)
                {
                    pot.addMember(currentPlayer);
                    amount -= currentPotBet;
                }
                else
                {
                    pots.add(pot.getSidePot(amount, currentPlayer));
                    amount = 0;
                }
            }
            if (amount == 0)
            {
                break;
            }
        }
        if (amount > 0)
        {
            Pot pot = new Pot(amount);
            pot.addMember(currentPlayer);
            pots.add(pot);
        }
    }
    
    /**
     * Distribuisce le vincite ai giocatori
     * @param ranking la classifica dei giocatori in base alle mani
     * @param players i giocatori tra cui distribuire le vincite
     * @param dealerPosition la posizione del dealer a partire dalla quale vengono ripartite le chip dispari
     */
    public void distributePots(Map<Hand, List<Player>> ranking, List<Player> players, int dealerPosition)
    {
        int totalPot = getTotalPot();
        //System.out.println("[TEST] Distribuzione vincite...");
        Map<Player, Integer> winners =  new HashMap<>();
        for (Hand hand : ranking.keySet())
        {
            List<Player> handWinners = ranking.get(hand);
            for (Pot pot : pots) 
            {
                int potWinners = 0;
                for (Player handWinner : handWinners) 
                {
                    if (pot.hasMember(handWinner)) 
                    {
                        potWinners++;
                    }
                }
                if (potWinners > 0)
                {
                    int potShare =  pot.getValue() / potWinners;
                    for (Player handWinner : handWinners)
                    {
                        if (pot.hasMember(handWinner))
                        {
                            Integer previousShare = winners.get(handWinner);
                            if (previousShare == null)
                            {
                                previousShare = 0;
                            }
                            winners.put(handWinner, previousShare + potShare);                            
                        }

                    }
                    int chipsLeft = pot.getValue() % potWinners;
                    if (chipsLeft > 0)
                    {
                        int position = dealerPosition;
                        while(chipsLeft > 0)
                        {
                            position = (position + 1) % players.size();
                            Player currentOddWinner = players.get(position);
                            if (winners.containsKey(currentOddWinner))
                            {
                                winners.put(currentOddWinner, winners.get(currentOddWinner) + 1);
                                chipsLeft--;
                            }
                        }
                    }
                    pot.reset();
                }
            }
        }
        int totalWin = 0;
        for (Player winner : winners.keySet())
        {
            totalWin += winners.get(winner);
        }
        if (totalWin > totalPot)
        {
            throw new IllegalStateException("Si sono generati soldi dal nulla!");
        }
        for (Player winner : winners.keySet())
        {
            //System.out.println("[TEST] " + winner.toString() + " vince " + winners.get(winner));
            winner.win(winners.get(winner));      
        }
    }

    public List<Pot> getPots() {
        return pots;
    }
    
    public void clearPots()
    {
        pots.clear();
    }

    @Override
    public String toString() {
        String report = new String();
        for (Pot pot : pots)
        {
            report += "Pot " + pot.getValue() + "\n";
            for (Player member : pot.getMembers())
            {
                report += "\tGiocatore "+  member.toString() + " con scommessa " + pot.getBet() + "\n";
            }
        }
        return report;
    }

    
}