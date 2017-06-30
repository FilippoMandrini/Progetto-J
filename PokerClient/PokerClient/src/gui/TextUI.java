package gui;

import actions.*;
import client.Sender;
import gametypes.GameType;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.Board;
import model.Card;
import model.Game;
import model.GameObserver;
import model.Player;

/**
 * Classe per il testo della GUI
 */
public class TextUI implements GameObserver {

    private Game game;
    
    public TextUI(Game game) {
        this.game = game;
    }

    
    @Override
    public void boardUpdated(Board board) {
        if (!board.getCommunityCards().isEmpty())
        {
            System.out.println("Carte comuni:");
            System.out.println(board);
        }
    }

    @Override
    public void playerUpdated(Player player, boolean toShow) {
        
        if(toShow)
        {
            System.out.println(player + " | carte: ");
            for (Card hc : player.getCards())
            {
                System.out.println(hc);
            }
        }
        else
        {
            System.out.println(player);
        }
    }

    @Override
    public void messageUpdated(String message) {
        System.out.println("--INIZIO MESSAGGIO DAL SERVER--\n" + message + "\n--FINE MESSAGGIO DAL SERVER--");
    }

    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        System.out.println("[PARTITA INIZIATA]");
        System.out.println(settings.getDescription());
        System.out.println(settings);
        for (Player player : players)
        {
            System.out.println(player);
        }
    }

    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        System.out.println("[NUOVA MANO]");
        System.out.println("Il giocatore " + dealer.getName() +" in posizione " + dealerPosition + " è il dealer");
    }

    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        System.out.println("E' il turno del giocatore " + currentPlayer.getName() +" in posizione " + currentPlayerPosition);
    }

    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        System.out.println("Scommessa attuale: " + bet + " | Scommessa minima: " + minBet + " | POT TOTALE: " + totalPot);
    }

    @Override
    public void selfUpdated(Player player) {
        System.out.println("La tua nuova situazione è la seguente: ");
        System.out.println(player);
        System.out.println("Le tue carte sono:");
        for (Card hc : player.getCards()) 
        {
            System.out.println(hc);
        }
    }

    @Override
    public void currentPlayerActed(Player player) {
        System.out.println("Il giocatore corrente " + player.getLastAction());
    }

    @Override
    public void actionRequest(int bet, int minBet, Set<ActionSet> allowedActions) {
        System.out.println("[AZIONE RICHIESTA]");
        System.out.println("Scommessa attuale: " + bet + " | scommessa minima: " + minBet);
        Action action = null;
        Scanner in  = new Scanner(System.in);
        if(in.hasNextLine())
        {
            do {
                String result = in.nextLine();
                String[] tokens = result.split(" ");
                if (tokens[0].equalsIgnoreCase("H")) {
                    action = new Check();
                }
                if (tokens[0].equalsIgnoreCase("R")) {
                    action = new Raise(Integer.parseInt(tokens[1]));
                }
                if (tokens[0].equalsIgnoreCase("C")) {
                    action =  new Call();
                }
                if (tokens[0].equalsIgnoreCase("T")) {
                    action = new Bet(Integer.parseInt(tokens[1]));
                }
                if (tokens[0].equalsIgnoreCase("B")) {
                    action = new BigBlind(0);
                }
                if (tokens[0].equalsIgnoreCase("S")) {
                    action = new SmallBlind(0);
                }
                if (tokens[0].equalsIgnoreCase("F")) {
                    action = new Fold();
                }
            } while (!allowedActions.contains(action.getActionType()) && action.getAmount() < game.getCurrentPlayer().getStake());
        }
        Sender.getInstance().sendAction(action);
    }

    @Override
    public void disconnect() {
        System.out.println("--[CLIENT DISCONNESSO DAL SERVER]--");
    }
    
}
