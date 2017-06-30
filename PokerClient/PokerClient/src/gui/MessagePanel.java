package gui;

import model.Game;

/**
 * Pannello dei messaggi
 */
public class MessagePanel extends GamePanel {

    /**
     * Crea un nuovo MessagePanel
     * @param game la partita
     */
    public MessagePanel(Game game) {
        super(game);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notifyLabel = new javax.swing.JLabel();
        headerLabel = new javax.swing.JLabel();

        notifyLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        notifyLabel.setForeground(new java.awt.Color(255, 205, 51));
        notifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notifyLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        headerLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 205, 51));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(notifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Imposta l'intestazione
     * @param header il messaggio per l'intestazione
     */
    public void setHeader(String header)
    {
        headerLabel.setText(header);
    }
    
    /**
     * Imposta il messaggio di notifica
     * @param notify il messaggio di notifica
     */
    public void setNotify(String notify)
    {
        notifyLabel.setText(notify);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel notifyLabel;
    // End of variables declaration//GEN-END:variables
}
