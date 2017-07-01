package gui;

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
 * GUI del gioco
 */
public class MainGUI extends JFrame implements GameObserver {
        
    private GridBagConstraints gc;
    private CentralPanel centralPanel;
    private MessagePanel messagePanel;
    private Map<Player, PlayerPanel> panelMap;
    private List<PlayerPanel> panels; 
    private final Game game;
    
    /**
     * Costruttore di MainGUI
     * @param game la partita
     */
    public MainGUI(Game game) {   
        super("Poker Texas Hold'em");
        this.game = game;
        initComponents();
    }
    
    /**
     * Inizializza la GUI
     */
    private void initComponents()
    {
        gc = new GridBagConstraints();
        centralPanel = new CentralPanel(game);
        panelMap = new HashMap<>();
        messagePanel = new MessagePanel(game);
        setLayout(new GridBagLayout());
        panels = new ArrayList<>();
        for (int i= 0; i< GUIConstants.MAX_PLAYERS; i++)
        {
            PlayerPanel panel = new PlayerPanel(game);
            panel.waiting("Attesa giocatori");
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
        getContentPane().setBackground(GUIConstants.TABLE_COLOR);
        addComponent(centralPanel, 4, 1, 1, 1);
        addComponent(messagePanel, 4, 0, 1, 1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Aggiunge un Component
     * @param component l'elemento
     * @param x la coordinata x
     * @param y la coordinata y
     * @param width la largezza
     * @param height l'altezza
     */
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

    /** @inheritDoc */
    @Override
    public void boardUpdated(Board board) {
        centralPanel.updateBoardCards(board.getCommunityCards());
    }

    /** @inheritDoc */
    @Override
    public void playerUpdated(Player player, boolean toShow) {
        PlayerPanel playerPanel = panelMap.get(player);
        if (playerPanel != null)
        {
            playerPanel.update(player);
        }
    }

    /** @inheritDoc */
    @Override
    public void messageUpdated(String message) {
        centralPanel.setHeader(message);
    }

    /** @inheritDoc */
    @Override
    public void gameStarted(List<Player> players, GameType settings) {
        
        for (int i = 0; i<players.size(); i++) 
        {
            PlayerPanel current = panels.get(i);
            panelMap.put(players.get(i), panels.get(i));
            current.update(players.get(i));
            current.activate();
        }   
        repaint();
        pack();
        messagePanel.setHeader("Benvenuto al tavolo! Variante: " + settings.getName());
    }

    /** @inheritDoc */
    @Override
    public void handStarted(Player dealer, int dealerPosition) {
        setDealer(dealer);
        messagePanel.setNotify("Mano n° " + game.getNoOfHands());
    }

    /** @inheritDoc */
    @Override
    public void currentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        setCurrent(currentPlayer);
    }

    /** @inheritDoc */
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        centralPanel.updateBoardBetting(bet, totalPot);
    }

    /** @inheritDoc */
    @Override
    public void selfUpdated(Player player) {
        playerUpdated(player, true);
    }

    /** @inheritDoc */
    @Override
    public void currentPlayerActed(Player player) 
    {
        PlayerPanel playerPanel = panelMap.get(player);
        if (playerPanel != null) 
        {
            playerPanel.updateAction(player);
            playerPanel.updateStake(player);
            playerPanel.updateName(player);
            Action action = player.getLastAction();
            if (action != null) 
            {
                centralPanel.setMessage(player.getName() + " " + action.getDescription());
                repaint();
            }
        } 
        else 
        {
            throw new IllegalStateException("Pannello inesistente");
        }
    }

    /** @inheritDoc */
    @Override
    public void actionRequest(int bet, int minBet, Set<ActionSet> allowedActions) {
        centralPanel.setMessage("Proprio turno: compiere un azione");
        centralPanel.actionRequest(allowedActions);
    }

    /** @inheritDoc */
    @Override
    public void disconnect() {
        centralPanel.disconnect();
        centralPanel.setMessage("Sei stato disconnesso dal server!");
        for (PlayerPanel panel : panelMap.values())
        {
            panel.waiting("Disconnesso");
        }
    }
    
    /**
     * Imposta il giocatore corrente nella GUI
     * @param currentPlayer il giocatore corrente
     */
    private void setCurrent(Player currentPlayer) 
    {
        if (game.getCurrentPlayer() != null && game.getCurrentPlayer().equals(currentPlayer)) 
        {
            for(Player player : panelMap.keySet())
            {
                panelMap.get(player).setCurrent(player.equals(currentPlayer));
            }
        }
    }

    /**
     * Imposta il giocatore dealer nella GUI
     * @param dealer il giocatore dealer
     */
    private void setDealer(Player dealer) 
    {
        if (game.getDealer() != null && game.getDealer().equals(dealer)) 
        {
            for(Player player : panelMap.keySet())
            {
                panelMap.get(player).setDealer(player.equals(dealer));
            }
        }
    }

}