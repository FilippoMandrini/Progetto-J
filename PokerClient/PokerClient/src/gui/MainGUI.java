/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Nickelsilver
 */
import actions.Action;
import actions.ActionSet;
import gametypes.GameType;
import model.Board;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import model.Game;
import model.GameObserver;
import model.Player;

/**
 * The game's main frame.
 * 
 * This is the core class of the Swing UI client application.
 * 
 * @author Oscar Stigter
 */
public class MainGUI extends JFrame implements GameObserver {
        
    
    private GridBagConstraints gc;
        
    private CenterPanel centerPanel;
    
    private MessagePanel messagePanel;
    
    private Map<Player, PlayerPanel> panelMap;
    
    private List<PlayerPanel> panels;
        
    private Game game;

    public MainGUI(Game game) {
        
        super("Poker Texas Hold'em");
        this.game = game;
        initComponents();
    }
    
    private void initComponents()
    {
        gc = new GridBagConstraints();
        centerPanel = new CenterPanel(game);
        panelMap = new HashMap<>();
        messagePanel = new MessagePanel(game);
        setLayout(new GridBagLayout());
        panels = new ArrayList<>();
        for (int i= 0; i< GUIConstants.MAX_PLAYERS; i++)
        {
            PlayerPanel panel = new PlayerPanel(game);
            panel.waiting();
            panels.add(panel);
            switch (i) {
                
                case 0:
                    // sud
                    addComponent(panel, 4, 2, 1, 1);
                    break;
                case 1:
                    // ovest
                    addComponent(panel, 1, 1, 1, 1);
                    break;
                case 2:
                    // nord ovest
                    addComponent(panel, 3, 0, 1, 1);
                    break;
                case 3:
                    // nord est
                    addComponent(panel, 6, 0, 1, 1);
                    break;
                case 4:
                    // est
                    addComponent(panel, 9, 1, 1, 1);
                    break;
                default:
                }
        }
        addComponent(centerPanel, 4, 1, 1, 1);
        addComponent(messagePanel, 4, 0, 1, 1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addComponent(Component component, int x, int y, int width, int height) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        getContentPane().add(component, gc);
    }

    @Override
    public void boardUpdated(Board board) {
        centerPanel.updateBoardCards(board.getCommunityCards());
    }

    @Override
    public void playerUpdated(Player player, boolean toShow) {
        PlayerPanel playerPanel = panelMap.get(player);
        if (playerPanel != null)
        {
            playerPanel.update(player);
        }
    }

    @Override
    public void messageUpdated(String message) {
        centerPanel.setHeader(message);
    }

    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        
        for (int i = 0; i<players.size(); i++) 
        {
            PlayerPanel current = panels.get(i);
            panelMap.put(players.get(i), panels.get(i));
            current.update(players.get(i));
        }   
        repaint();
        pack();
        messagePanel.setHeader("Benvenuto al tavolo! Variante: " + settings.getName());
    }

    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        setDealer(dealer);
        messagePanel.setNotify("Mano n° " + game.getNoOfHands());
    }

    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        setCurrent(currentPlayer);
        currentPlayerActed(currentPlayer);
    }

    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        centerPanel.updateBoardBetting(bet, totalPot);
    }

    @Override
    public void selfUpdated(Player player) {
        playerUpdated(player, true);
    }

    @Override
    public void currentPlayerActed(Player player) 
    {
        PlayerPanel playerPanel = panelMap.get(player);
        if (playerPanel != null) 
        {
            playerPanel.update(player);
            Action action = player.getLastAction();
            if (action != null) 
            {
                centerPanel.setMessage(player.getName() + " " + action.getDescription());
                repaint();
            }
        } 
        else 
        {
            throw new IllegalStateException("Pannello inesistente");
        }
    }

    @Override
    public void actionRequest(int bet, int minBet, Set<ActionSet> allowedActions) {
        centerPanel.setMessage("Proprio turno: compiere un azione");
        centerPanel.actionRequest(allowedActions);
    }

    @Override
    public void disconnect() {
        centerPanel.disconnect();
        centerPanel.setMessage("Sei stato disconnesso dal server!");
        for (PlayerPanel panel : panelMap.values())
        {
            panel.waiting();
        }
    }
    
    private void setCurrent(Player currentPlayer) 
    {
        if (game.getCurrentPlayer() != null && game.getCurrentPlayer().equals(currentPlayer)) 
        {
            for(Player player : panelMap.keySet())
            {
                panelMap.get(currentPlayer).setCurrent(player.equals(currentPlayer));
            }
        }
    }

    private void setDealer(Player dealer) 
    {
        if (game.getCurrentPlayer() != null && game.getCurrentPlayer().equals(dealer)) 
        {
            for(Player player : panelMap.keySet())
            {
                panelMap.get(dealer).setCurrent(player.equals(dealer));
            }
        }
    }

}
