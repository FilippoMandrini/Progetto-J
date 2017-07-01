package gui;

import java.net.URL;
import javax.swing.ImageIcon;
import model.Card;

/**
 * Gestore delle immagini per la GUI
 */
public abstract class GUIResourcesHandler {
    
    private static final String BASE_PATH = "/images/";
    private static final String DEALER_ON = "/images/dealer_on.png";
    private static final String DEALER_OFF = "/images/dealer_off.png";
    private static final String CARD_OFF = "/images/card_off.png";
    private static final String CARD_ON = "/images/Retro.png";

    /**
     * Ritorna l'immagine della carta da mostrare
     * @param card la carta da mostrare
     * @return l'ImageIcon associata all'immagine
     */
    public static ImageIcon getCardImage(Card card) {
        
        String cardValue = Card.getRankName(card.getRank()) + Card.getSuitName(card.getSuit());
        String fullPath = BASE_PATH + cardValue + ".png";
        return getIcon(fullPath);
    }
    
    /**
     * Ritorna l'immagine del gettone del dealer
     * @return l'ImageIcon associata all'immagine
     */
    public static ImageIcon getDealerOn()
    {
        return getIcon(DEALER_ON);
    }
    
    /**
     * Ritorna l'immagine dello spazio del gettone del dealer
     * @return l'ImageIcon associata all'immagine
     */
    public static ImageIcon getDealerOff()
    {
        return getIcon(DEALER_OFF);
    }
    
    /**
     * Ritorna l'immagine dello spazio per la carta
     * @return l'ImageIcon associata all'immagine
     */
    public static ImageIcon getCardOff()
    {
        return getIcon(CARD_OFF);
    }
    
    /**
     * Ritorna l'immagine del retro della carta
     * @return l'ImageIcon associata all'immagine
     */
    public static ImageIcon getCardOn()
    {
        return getIcon(CARD_ON);
    }
    
    /**
     * Ritorna l'immagine dato il percorso
     * @param path il percorso per l'immagine
     * @throws RuntimeException quando l'immagine non viene trovata
     * @return l'ImageIcon associata all'immagine
     */
    private static ImageIcon getIcon(String path) {
        URL url = GUIResourcesHandler.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            throw new RuntimeException("Resource file not found: " + path);
        }
    }
}