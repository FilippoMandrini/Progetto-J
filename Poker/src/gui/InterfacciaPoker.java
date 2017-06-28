package gui;

/**
 * GUI per la partita di Poker
 */
public class InterfacciaPoker extends javax.swing.JFrame {

    public InterfacciaPoker() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sfondoPanel = new javax.swing.JPanel();
        boardPanel = new javax.swing.JPanel();
        cartaBoard1Label = new javax.swing.JLabel();
        cartaBoard4Label = new javax.swing.JLabel();
        cartaBoard2Label = new javax.swing.JLabel();
        cartaBoard5Label = new javax.swing.JLabel();
        cartaBoard3Label = new javax.swing.JLabel();
        potLabel = new javax.swing.JLabel();
        turnoLabel = new javax.swing.JLabel();
        playerPanel = new javax.swing.JPanel();
        pulsantiPanel = new javax.swing.JPanel();
        callButton = new javax.swing.JButton();
        raiseButton = new javax.swing.JButton();
        foldButton = new javax.swing.JButton();
        carta2PlayerLabel = new javax.swing.JLabel();
        nomePlayerLabel = new javax.swing.JLabel();
        stakePlayerLabel = new javax.swing.JLabel();
        azionePlayerLabel = new javax.swing.JLabel();
        carta1PlayerLabel = new javax.swing.JLabel();
        dealerPlayerLabel = new javax.swing.JLabel();
        raisePanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        raiseSpinner = new javax.swing.JSpinner();
        player1Panel = new javax.swing.JPanel();
        carta2Player1Label = new javax.swing.JLabel();
        nomePlayer1Label = new javax.swing.JLabel();
        stakePlayer1Label = new javax.swing.JLabel();
        azionePlayer1Label = new javax.swing.JLabel();
        carta1Player1Label = new javax.swing.JLabel();
        dealerPlayer1Label = new javax.swing.JLabel();
        player2Panel = new javax.swing.JPanel();
        carta2Player2Label = new javax.swing.JLabel();
        nomePlayer2Label = new javax.swing.JLabel();
        stakePlayer2Label = new javax.swing.JLabel();
        azionePlayer2Label = new javax.swing.JLabel();
        carta1Player2Label = new javax.swing.JLabel();
        dealerPlayer2Label = new javax.swing.JLabel();
        player3Panel = new javax.swing.JPanel();
        carta2Player3Label = new javax.swing.JLabel();
        nomePlayer3Label = new javax.swing.JLabel();
        stakePlayer3Label = new javax.swing.JLabel();
        azionePlayer3Label = new javax.swing.JLabel();
        carta1Player3Label = new javax.swing.JLabel();
        dealerPlayer3Label = new javax.swing.JLabel();
        player4Panel = new javax.swing.JPanel();
        carta2Player4Label = new javax.swing.JLabel();
        nomePlayer4Label = new javax.swing.JLabel();
        stakePlayer4Label = new javax.swing.JLabel();
        azionePlayer4Label = new javax.swing.JLabel();
        carta1Player4Label = new javax.swing.JLabel();
        dealerPlayer4Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Poker");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);

        sfondoPanel.setBackground(new java.awt.Color(0, 102, 51));
        sfondoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 53, 34), 3));
        sfondoPanel.setPreferredSize(new java.awt.Dimension(1200, 700));

        boardPanel.setBackground(new java.awt.Color(0, 102, 51));
        boardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 5));

        cartaBoard1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaBoard1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        cartaBoard1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        cartaBoard4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaBoard4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        cartaBoard4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        cartaBoard2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaBoard2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        cartaBoard2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        cartaBoard5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaBoard5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        cartaBoard5Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        cartaBoard3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaBoard3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        cartaBoard3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        potLabel.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        potLabel.setForeground(new java.awt.Color(255, 205, 51));
        potLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        potLabel.setText("Pot");
        potLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        turnoLabel.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        turnoLabel.setForeground(new java.awt.Color(255, 205, 51));
        turnoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnoLabel.setText("Turno");
        turnoLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        turnoLabel.setMaximumSize(new java.awt.Dimension(26, 20));
        turnoLabel.setMinimumSize(new java.awt.Dimension(26, 20));
        turnoLabel.setPreferredSize(new java.awt.Dimension(26, 20));

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(boardPanelLayout.createSequentialGroup()
                        .addComponent(cartaBoard1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cartaBoard2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cartaBoard3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cartaBoard4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cartaBoard5Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(boardPanelLayout.createSequentialGroup()
                        .addComponent(turnoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(potLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(turnoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(potLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cartaBoard3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaBoard5Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaBoard4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaBoard2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaBoard1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        playerPanel.setBackground(new java.awt.Color(0, 102, 51));
        playerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 3));

        pulsantiPanel.setBackground(new java.awt.Color(0, 102, 51));

        callButton.setBackground(new java.awt.Color(51, 51, 255));
        callButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        callButton.setText("Call");
        callButton.setPreferredSize(new java.awt.Dimension(70, 20));
        callButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                callButtonActionPerformed(evt);
            }
        });

        raiseButton.setBackground(new java.awt.Color(255, 51, 0));
        raiseButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        raiseButton.setText("Raise");
        raiseButton.setPreferredSize(new java.awt.Dimension(70, 20));

        foldButton.setBackground(new java.awt.Color(255, 255, 51));
        foldButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        foldButton.setText("Fold");
        foldButton.setPreferredSize(new java.awt.Dimension(70, 20));
        foldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foldButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pulsantiPanelLayout = new javax.swing.GroupLayout(pulsantiPanel);
        pulsantiPanel.setLayout(pulsantiPanelLayout);
        pulsantiPanelLayout.setHorizontalGroup(
            pulsantiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pulsantiPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(callButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(raiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(foldButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pulsantiPanelLayout.setVerticalGroup(
            pulsantiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pulsantiPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pulsantiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(raiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(callButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(foldButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        carta2PlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta2PlayerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta2PlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nomePlayerLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nomePlayerLabel.setForeground(new java.awt.Color(255, 205, 51));
        nomePlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomePlayerLabel.setText("NomeGiocatore");
        nomePlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakePlayerLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        stakePlayerLabel.setForeground(new java.awt.Color(255, 205, 51));
        stakePlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakePlayerLabel.setText("Stake");
        stakePlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        azionePlayerLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        azionePlayerLabel.setForeground(new java.awt.Color(255, 205, 51));
        azionePlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        azionePlayerLabel.setText("Azione");
        azionePlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        carta1PlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta1PlayerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta1PlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerPlayerLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dealerPlayerLabel.setForeground(new java.awt.Color(102, 0, 0));
        dealerPlayerLabel.setText("D");
        dealerPlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        raisePanel.setBackground(new java.awt.Color(0, 102, 51));

        okButton.setBackground(new java.awt.Color(255, 51, 0));
        okButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        okButton.setText("OK");
        okButton.setMaximumSize(new java.awt.Dimension(57, 23));
        okButton.setMinimumSize(new java.awt.Dimension(57, 23));
        okButton.setPreferredSize(new java.awt.Dimension(70, 20));

        raiseSpinner.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        raiseSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        raiseSpinner.setMinimumSize(new java.awt.Dimension(73, 22));

        javax.swing.GroupLayout raisePanelLayout = new javax.swing.GroupLayout(raisePanel);
        raisePanel.setLayout(raisePanelLayout);
        raisePanelLayout.setHorizontalGroup(
            raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(raisePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(raiseSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        raisePanelLayout.setVerticalGroup(
            raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(raisePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(raiseSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(azionePlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nomePlayerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(playerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(carta1PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(carta2PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealerPlayerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pulsantiPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stakePlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(raisePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nomePlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stakePlayerLabel)
                .addGap(4, 4, 4)
                .addComponent(azionePlayerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerPlayerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pulsantiPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(raisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        player1Panel.setBackground(new java.awt.Color(0, 102, 51));
        player1Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 3));

        carta2Player1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta2Player1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta2Player1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nomePlayer1Label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nomePlayer1Label.setForeground(new java.awt.Color(255, 205, 51));
        nomePlayer1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomePlayer1Label.setText("NomeGiocatore1");
        nomePlayer1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakePlayer1Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        stakePlayer1Label.setForeground(new java.awt.Color(255, 205, 51));
        stakePlayer1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakePlayer1Label.setText("Stake");
        stakePlayer1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        azionePlayer1Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        azionePlayer1Label.setForeground(new java.awt.Color(255, 205, 51));
        azionePlayer1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        azionePlayer1Label.setText("Azione");
        azionePlayer1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        carta1Player1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta1Player1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta1Player1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerPlayer1Label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dealerPlayer1Label.setForeground(new java.awt.Color(102, 0, 0));
        dealerPlayer1Label.setText("D");
        dealerPlayer1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        javax.swing.GroupLayout player1PanelLayout = new javax.swing.GroupLayout(player1Panel);
        player1Panel.setLayout(player1PanelLayout);
        player1PanelLayout.setHorizontalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(azionePlayer1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nomePlayer1Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(carta1Player1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(carta2Player1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealerPlayer1Label)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(stakePlayer1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        player1PanelLayout.setVerticalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nomePlayer1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stakePlayer1Label)
                .addGap(4, 4, 4)
                .addComponent(azionePlayer1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1Player1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2Player1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerPlayer1Label))
                .addContainerGap())
        );

        player2Panel.setBackground(new java.awt.Color(0, 102, 51));
        player2Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 3));

        carta2Player2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta2Player2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta2Player2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nomePlayer2Label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nomePlayer2Label.setForeground(new java.awt.Color(255, 205, 51));
        nomePlayer2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomePlayer2Label.setText("NomeGiocatore2");
        nomePlayer2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakePlayer2Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        stakePlayer2Label.setForeground(new java.awt.Color(255, 205, 51));
        stakePlayer2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakePlayer2Label.setText("Stake");
        stakePlayer2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        azionePlayer2Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        azionePlayer2Label.setForeground(new java.awt.Color(255, 205, 51));
        azionePlayer2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        azionePlayer2Label.setText("Azione");
        azionePlayer2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        carta1Player2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta1Player2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta1Player2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerPlayer2Label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dealerPlayer2Label.setForeground(new java.awt.Color(102, 0, 0));
        dealerPlayer2Label.setText("D");
        dealerPlayer2Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        javax.swing.GroupLayout player2PanelLayout = new javax.swing.GroupLayout(player2Panel);
        player2Panel.setLayout(player2PanelLayout);
        player2PanelLayout.setHorizontalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(azionePlayer2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nomePlayer2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(carta1Player2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(carta2Player2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealerPlayer2Label)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(stakePlayer2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        player2PanelLayout.setVerticalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nomePlayer2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stakePlayer2Label)
                .addGap(4, 4, 4)
                .addComponent(azionePlayer2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1Player2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2Player2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerPlayer2Label))
                .addContainerGap())
        );

        player3Panel.setBackground(new java.awt.Color(0, 102, 51));
        player3Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 3));

        carta2Player3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta2Player3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta2Player3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nomePlayer3Label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nomePlayer3Label.setForeground(new java.awt.Color(255, 205, 51));
        nomePlayer3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomePlayer3Label.setText("NomeGiocatore3");
        nomePlayer3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakePlayer3Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        stakePlayer3Label.setForeground(new java.awt.Color(255, 205, 51));
        stakePlayer3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakePlayer3Label.setText("Stake");
        stakePlayer3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        azionePlayer3Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        azionePlayer3Label.setForeground(new java.awt.Color(255, 205, 51));
        azionePlayer3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        azionePlayer3Label.setText("Azione");
        azionePlayer3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        carta1Player3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta1Player3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta1Player3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerPlayer3Label.setBackground(new java.awt.Color(153, 0, 153));
        dealerPlayer3Label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dealerPlayer3Label.setForeground(new java.awt.Color(102, 0, 0));
        dealerPlayer3Label.setText("D");
        dealerPlayer3Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        javax.swing.GroupLayout player3PanelLayout = new javax.swing.GroupLayout(player3Panel);
        player3Panel.setLayout(player3PanelLayout);
        player3PanelLayout.setHorizontalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(azionePlayer3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nomePlayer3Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(carta1Player3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(carta2Player3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealerPlayer3Label)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(stakePlayer3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        player3PanelLayout.setVerticalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nomePlayer3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stakePlayer3Label)
                .addGap(4, 4, 4)
                .addComponent(azionePlayer3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1Player3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2Player3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerPlayer3Label))
                .addContainerGap())
        );

        player4Panel.setBackground(new java.awt.Color(0, 102, 51));
        player4Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 63, 43), 3));

        carta2Player4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta2Player4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/images/Retro.png")));
        carta2Player4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nomePlayer4Label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nomePlayer4Label.setForeground(new java.awt.Color(255, 201, 51));
        nomePlayer4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomePlayer4Label.setText("NomeGiocatore4");
        nomePlayer4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        stakePlayer4Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        stakePlayer4Label.setForeground(new java.awt.Color(255, 205, 51));
        stakePlayer4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stakePlayer4Label.setText("Stake");
        stakePlayer4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        azionePlayer4Label.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        azionePlayer4Label.setForeground(new java.awt.Color(255, 205, 51));
        azionePlayer4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        azionePlayer4Label.setText("Azione");
        azionePlayer4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));

        carta1Player4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        carta1Player4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Carte/Retro.png"))); // NOI18N
        carta1Player4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        dealerPlayer4Label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dealerPlayer4Label.setForeground(new java.awt.Color(102, 0, 0));
        dealerPlayer4Label.setText("D");
        dealerPlayer4Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        javax.swing.GroupLayout player4PanelLayout = new javax.swing.GroupLayout(player4Panel);
        player4Panel.setLayout(player4PanelLayout);
        player4PanelLayout.setHorizontalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(azionePlayer4Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nomePlayer4Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(carta1Player4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(carta2Player4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealerPlayer4Label)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(stakePlayer4Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        player4PanelLayout.setVerticalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nomePlayer4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stakePlayer4Label)
                .addGap(4, 4, 4)
                .addComponent(azionePlayer4Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1Player4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2Player4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerPlayer4Label))
                .addContainerGap())
        );

        javax.swing.GroupLayout sfondoPanelLayout = new javax.swing.GroupLayout(sfondoPanel);
        sfondoPanel.setLayout(sfondoPanelLayout);
        sfondoPanelLayout.setHorizontalGroup(
            sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfondoPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(player4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sfondoPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sfondoPanelLayout.createSequentialGroup()
                                .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sfondoPanelLayout.createSequentialGroup()
                                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))))
                    .addGroup(sfondoPanelLayout.createSequentialGroup()
                        .addComponent(player3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(player2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        sfondoPanelLayout.setVerticalGroup(
            sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfondoPanelLayout.createSequentialGroup()
                .addGroup(sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sfondoPanelLayout.createSequentialGroup()
                        .addGroup(sfondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sfondoPanelLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sfondoPanelLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(player4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sfondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(sfondoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void callButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_callButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_callButtonActionPerformed

    private void foldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foldButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foldButtonActionPerformed

    public static void main(String args[]) {
        InterfacciaPoker prova = new InterfacciaPoker();
        prova.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel azionePlayer1Label;
    private javax.swing.JLabel azionePlayer2Label;
    private javax.swing.JLabel azionePlayer3Label;
    private javax.swing.JLabel azionePlayer4Label;
    private javax.swing.JLabel azionePlayerLabel;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton callButton;
    private javax.swing.JLabel carta1Player1Label;
    private javax.swing.JLabel carta1Player2Label;
    private javax.swing.JLabel carta1Player3Label;
    private javax.swing.JLabel carta1Player4Label;
    private javax.swing.JLabel carta1PlayerLabel;
    private javax.swing.JLabel carta2Player1Label;
    private javax.swing.JLabel carta2Player2Label;
    private javax.swing.JLabel carta2Player3Label;
    private javax.swing.JLabel carta2Player4Label;
    private javax.swing.JLabel carta2PlayerLabel;
    private javax.swing.JLabel cartaBoard1Label;
    private javax.swing.JLabel cartaBoard2Label;
    private javax.swing.JLabel cartaBoard3Label;
    private javax.swing.JLabel cartaBoard4Label;
    private javax.swing.JLabel cartaBoard5Label;
    private javax.swing.JLabel dealerPlayer1Label;
    private javax.swing.JLabel dealerPlayer2Label;
    private javax.swing.JLabel dealerPlayer3Label;
    private javax.swing.JLabel dealerPlayer4Label;
    private javax.swing.JLabel dealerPlayerLabel;
    private javax.swing.JButton foldButton;
    private javax.swing.JLabel nomePlayer1Label;
    private javax.swing.JLabel nomePlayer2Label;
    private javax.swing.JLabel nomePlayer3Label;
    private javax.swing.JLabel nomePlayer4Label;
    private javax.swing.JLabel nomePlayerLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel player1Panel;
    private javax.swing.JPanel player2Panel;
    private javax.swing.JPanel player3Panel;
    private javax.swing.JPanel player4Panel;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JLabel potLabel;
    private javax.swing.JPanel pulsantiPanel;
    private javax.swing.JButton raiseButton;
    private javax.swing.JPanel raisePanel;
    private javax.swing.JSpinner raiseSpinner;
    private javax.swing.JPanel sfondoPanel;
    private javax.swing.JLabel stakePlayer1Label;
    private javax.swing.JLabel stakePlayer2Label;
    private javax.swing.JLabel stakePlayer3Label;
    private javax.swing.JLabel stakePlayer4Label;
    private javax.swing.JLabel stakePlayerLabel;
    private javax.swing.JLabel turnoLabel;
    // End of variables declaration//GEN-END:variables
}
