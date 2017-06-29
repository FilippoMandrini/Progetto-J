package gui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * Classe ConfigListener
 */
public class ConfigListener implements DocumentListener {

    private final JButton connectButton;
    private final ArrayList<Document> documents = new ArrayList<>();

    public ConfigListener(JButton connectButton) {
        this.connectButton = connectButton;
    }

    public void addDocument(Document doc) {
        doc.addDocumentListener(this);
        documents.add(doc);
        documentChanged();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        documentChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        documentChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        
        documentChanged();
    }

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
