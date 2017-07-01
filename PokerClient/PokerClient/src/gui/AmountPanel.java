package gui;

import actions.Action;
import actions.ActionSet;
import client.Sender;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import model.Game;

/**
 * Pannello per le puntate
 */
public class AmountPanel extends GamePanel {

    /**
     * Crea un nuovo AmountPanel
     * @param game la partita su cui si applica
     */
    public AmountPanel(Game game) {
        super(game);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amountSpinner = new javax.swing.JSpinner();
        okButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(okButton)
                        .addComponent(resetButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Conferma puntata
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        int amount = (int)amountSpinner.getValue();
        selectedAction.setAmount(amount);
        if(selectedAction!=null)
        {
            //System.out.println(JSONEncoder.getInstance().encodeAct(selectedAction));
            Sender.getInstance().sendAction(selectedAction);
        }
        selectedAction = null;
        firePropertyChange("ACTION_SENT", null, selectedAction);
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Resetta la puntata
     */
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        SpinnerNumberModel model = (SpinnerNumberModel)amountSpinner.getModel();
        amountSpinner.setValue(model.getMinimum());
    }//GEN-LAST:event_resetButtonActionPerformed

    /**
     * Aggiorna il pannello per l'azione
     * @param defaultAction l'azione
     */
    public void act(Action defaultAction)
    {
        int max = 0;
        int min = game.getMinBet();
        int shift = game.getSettings().getBigBlind();
        System.out.println("[TEST]");
        System.out.println("min " + min);
        System.out.println("max " + max);
        System.out.println("shift " + shift);
        System.out.println(game.getCurrentPlayer().getStake());
        System.out.println(game.getBet());
        System.out.println(game.getMinBet());
        this.selectedAction = defaultAction;
        okButton.setText(defaultAction.getName());
        if (selectedAction.getActionType() == ActionSet.BET)
        {
            max = game.getCurrentPlayer().getStake();
        }
        if (selectedAction.getActionType() == ActionSet.RAISE)
        {
            min = game.getSettings().getBigBlind();
            max = game.getCurrentPlayer().getStake() + game.getCurrentPlayer().getCurrentBet() - game.getBet();
        }
        if (max <= min)
        {
            min = max;
            shift = min;
            System.out.println("min " + min);
            System.out.println("max " + max);
            System.out.println("shift " + shift);
        }
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(min, min, max, shift);
        amountSpinner.setModel(spinnerModel);
        amountSpinner.setValue(min);
//        try {
//            amountSpinner.commitEdit();
//        } catch (ParseException ex) {
//            spinnerModel.setValue(min);
//        }
        repaint();
        validate();
    }
    
    /**
     * Ricava la quantità della scommessa
     * @return la quantità della scommessa
     */
    public int getAmount() {
        return (int)amountSpinner.getValue();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner amountSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables
    private Action defaultAction;
    private Action selectedAction;
    private final Object lock = new Object();
}
