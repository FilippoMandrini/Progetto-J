package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * Classe che implementa DocumentListener
 */
public class ConfigListener implements DocumentListener {

    private final JButton connectButton;
    private final ArrayList<Document> documents = new ArrayList<>();

    /**
     * Costruttore di ConfigListener
     * @param connectButton il pulsante
     */
    public ConfigListener(JButton connectButton) {
        this.connectButton = connectButton;
    }

    /**
     * Aggiunge un Document
     * @param doc il Document da aggiungere
     */
    public void addDocument(Document doc) {
        doc.addDocumentListener(this);
        documents.add(doc);
        documentChanged();
    }

    /**
     * Invocato all'inserimento
     * @param e il DocumentEvent
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        documentChanged();
    }
    
    /**
     * Invocato alla rimozione
     * @param e il DocumentEvent
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        documentChanged();
    }

    /**
     * Invocato alla modifica
     * @param e il DocumentEvent
     */
    @Override
    public void changedUpdate(DocumentEvent e) {  
        documentChanged();
    }

    /**
     * Invocato per gestire il pulsante
     */
    public void documentChanged() {
        boolean buttonEnabled = false;
        for (Document document : documents) {
            if (document.getLength() == 0 ) {
                buttonEnabled = false;
                break;
            }
            buttonEnabled = true;
        }
        connectButton.setEnabled(buttonEnabled);
    }

}