package gui;

import actions.ActionSet;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Set;
import model.Card;
import model.Game;

/**
 * Pannello centrale
 */
public class CentralPanel extends GamePanel {

    private final ControlPanel controlPanel;
    private final BoardPanel boardPanel;
    private final GridBagConstraints gc;
    
    /**
     * Crea un nuovo CenterPanel
     * @param game la partita
     */
    public CentralPanel(Game game) {
        super(game);
        controlPanel = new ControlPanel(game);
        boardPanel = new BoardPanel(game);
        initComponents();
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        addComponent(controlPanel, 0, 1, 1, 1);
        addComponent(boardPanel, 0, 0, 1, 1);
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
    
    public void actionRequest(Set<ActionSet> allowedActions)
    {
        controlPanel.act(allowedActions);
    }
    
    public void setMessage(String message)
    {
        boardPanel.setMessage(message);
    }
    
    public void setHeader(String header)
    {
        boardPanel.setHeader(header);
    }
    
    public void updateBoardCards(List<Card> cards)
    {
        boardPanel.updateCards(cards);
    }
    
    public void updateBoardBetting(int bet, int totalPot)
    {
        boardPanel.updateBetting(bet, totalPot);
    }
    
    public void disconnect()
    {
        boardPanel.setBackground(Color.GRAY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addComponent(Component component, int x, int y, int width, int height) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        add(component, gc);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
