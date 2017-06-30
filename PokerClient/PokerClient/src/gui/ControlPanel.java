package gui;

import actions.Action;
import actions.ActionFactory;
import actions.ActionSet;
import client.Sender;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import json.JSONEncoder;
import model.Game;

/**
 * Classe per il pannello dei controlli
 */
public class ControlPanel extends GamePanel{
    
    private final JButton checkButton;    
    private final JButton callButton; 
    private final JButton betButton; 
    private final JButton raiseButton;  
    private final JButton foldButton;
    private final AmountPanel amountPanel;    
    private Action selectedAction;
            
    public ControlPanel(Game game) {
        super(game);
        Dimension dim = new Dimension(520, 50);
        this.setSize(dim);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        checkButton = createActionButton(ActionSet.CHECK);
        callButton = createActionButton(ActionSet.CALL);
        betButton = createActionButton(ActionSet.BET);
        raiseButton = createActionButton(ActionSet.RAISE);
        foldButton = createActionButton(ActionSet.FOLD);
        amountPanel = new AmountPanel(game);
        amountPanel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("ACTION_SENT")) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            removeAll();
                            repaint();
                            validate();
                        }
                    });
                    game.setInTurn(false);
                }
            }
      });
    }
    
    private void resetPanel()
    {
        removeAll();
        repaint();
        validate();
    }

    private JButton createActionButton(ActionSet action) {
        JButton button = new JButton(action.getName());
        button.setSize(100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ActionFactory factory = new ActionFactory();
                try 
                {
                    selectedAction = factory.createAction(action, 0);
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.err.println("Errore!");
                }
                if (selectedAction.getActionType() == ActionSet.BET || selectedAction.getActionType() == ActionSet.RAISE) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            removeAll();
                            add(amountPanel);
                            repaint();
                            validate();
                        }
                    });
                    amountPanel.act(selectedAction);
                }
                else
                {
                    if (game.isInTurn()) 
                    {
                        //System.out.println(JSONEncoder.getInstance().encodeAct(selectedAction));
                        Sender.getInstance().sendAction(selectedAction);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                resetPanel();
                            }
                        });
                        game.setInTurn(false);                    
                    }
                } 
            }
        });
        return button;
    }
    
    public void act(Set<ActionSet> allowedActions) {

        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                removeAll();
                if (allowedActions.contains(ActionSet.CHECK)) 
                {
                    add(checkButton);
                }
                if (allowedActions.contains(ActionSet.CALL)) 
                {
                    add(callButton);
                }
                if (allowedActions.contains(ActionSet.BET)) 
                {
                    add(betButton);
                }
                if (allowedActions.contains(ActionSet.RAISE)) 
                {
                    add(raiseButton);
                }
                if (allowedActions.contains(ActionSet.FOLD)) 
                {
                    add(foldButton);
                }
                repaint();
                validate();
            }
        });
    }

}
