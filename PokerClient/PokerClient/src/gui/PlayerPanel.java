package gui;

import actions.Action;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import model.Card;
import model.Game;
import model.Player;

/**
 * Pannello del giocatore
 */
public class PlayerPanel extends GamePanel {

    List<JLabel> cardLabels;
    
    /**
     * Crea un nuovo PlayerPanel
     * @param game la partita
     */
    public PlayerPanel(Game game) {
        super(game);
        initComponents();
        setBorder(GUIConstants.PANEL_BORDER);
        cardLabels = new ArrayList<>();
        cardLabels.add(card1Label);
        cardLabels.add(card2Label);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerLabel = new javax.swing.JLabel();
        stakeLabel = new javax.swing.JLabel();
        actionLabel = new javax.swing.JLabel();
        betLabel = new javax.swing.JLabel();
        card1Label = new javax.swing.JLabel();
        card2Label = new javax.swing.JLabel();
        dealerLabel = new javax.swing.JLabel();

        playerLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        playerLabel.setForeground(new java.awt.Color(255, 205, 51));
        playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakeLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        stakeLabel.setForeground(new java.awt.Color(255, 205, 51));
        stakeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        actionLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        actionLabel.setForeground(new java.awt.Color(255, 205, 51));
        actionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        betLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        betLabel.setForeground(new java.awt.Color(255, 205, 51));
        betLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        betLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        card1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        card2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dealerLabel.setForeground(new java.awt.Color(255, 205, 51));
        dealerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dealerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stakeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(card2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(card1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(betLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(dealerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dealerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stakeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(betLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Modifica pannello per l'attesa
     * @param parameter stringa da visualizzare
     */
    public void waiting(String parameter)
    {
        this.setBackground(Color.GRAY);
        playerLabel.setText(parameter);
        repaint();
    }
    
    /**
     * Modifica pannella per lo stato attivo
     */
    public void activate()
    {
        this.setBackground(GUIConstants.TABLE_COLOR);
        repaint();
    }
    
    /**
     * Imposta il messaggio dell'azione
     * @param player il giocatore
     */
    public void updateAction(Player player)
    {
        Action action = player.getLastAction();
        if (action != null) 
        {
            actionLabel.setText(action.getName());
        } 
        else 
        {
            actionLabel.setText("");
        }    
    }
    
    /**
     * Imposta la visualizzazione dello stake
     * @param player il giocatore
     */
    public void updateStake(Player player)
    {
        stakeLabel.setText("€ " + player.getStake());
    }
    
    /**
     * Imposta il nome da visualizzare del giocatore
     * @param player il giocatore
     */
    public void updateName(Player player)
    {
        String oldName = playerLabel.getText();
        playerLabel.setText(player.getName());
        if (player.getName().equalsIgnoreCase("Disconnesso"))
        {
            waiting(player.getName());
        }
    }
    
    /**
     * Aggiorna la visualizzazione del pannello
     * @param player il giocatore
     */
    public void update(Player player) 
    {
        updateName(player);
        updateStake(player);
        int bet = player.getCurrentBet();
        if (bet == 0) 
        {
            betLabel.setText("");
        } 
        else 
        {
            betLabel.setText("€ " + bet);
        }
        updateAction(player);
        if (player.hasCards()) 
        {
            List<Card> cards = player.getCards();
            if (cards.size() == GUIConstants.MAX_HOLE_CARDS) 
            {
                for (int i= 0; i < GUIConstants.MAX_HOLE_CARDS; i++)
                {
                    cardLabels.get(i).setIcon(GUIResourcesHandler.getCardImage(cards.get(i)));
                }
            } 
            else 
            {
                for (int i= 0; i < GUIConstants.MAX_HOLE_CARDS; i++)
                {
                    cardLabels.get(i).setIcon(GUIResourcesHandler.getCardOn());
                }
            }            
        } 
        else 
        {
            for (int i= 0; i < GUIConstants.MAX_HOLE_CARDS; i++)
            {
                cardLabels.get(i).setIcon(GUIResourcesHandler.getCardOff());
            }
        }
        repaint();
    }
    
    /**
     * Imposta la visualizzazione del gettone del dealer
     * @param isDealer true se è il pannello del dealer, false altrimenti
     */
    public void setDealer(boolean isDealer)
    {
        if(isDealer)
        {
            dealerLabel.setIcon(GUIResourcesHandler.getDealerOn());
        }
        else
        {
            dealerLabel.setIcon(GUIResourcesHandler.getDealerOff());
        }
        repaint();
    }
    
    /**
     * Imposta la visualizzazione del giocatore attuale
     * @param isCurrent true se ilgiocatore sta agendo, false altrimenti
     */
    public void setCurrent(boolean isCurrent)
    {
        if(isCurrent)
        {
            playerLabel.setForeground(GUIConstants.CURRENT_COLOR);
        }
        else
        {
            playerLabel.setForeground(GUIConstants.STANDARD_COLOR);
        }
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionLabel;
    private javax.swing.JLabel betLabel;
    private javax.swing.JLabel card1Label;
    private javax.swing.JLabel card2Label;
    private javax.swing.JLabel dealerLabel;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JLabel stakeLabel;
    // End of variables declaration//GEN-END:variables
}