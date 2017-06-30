package gui;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;
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

            
    public static ImageIcon getCardImage(Card card) {
        
        String cardValue = Card.getRankName(card.getRank()) + Card.getSuitName(card.getSuit());
        String fullPath = BASE_PATH + cardValue + ".png";
        return getIcon(fullPath);
    }
    
    public static ImageIcon getDealerOn()
    {
        return getIcon(DEALER_ON);
    }
    
    public static ImageIcon getDealerOff()
    {
        return getIcon(DEALER_OFF);
    }
    
    public static ImageIcon getCardOff()
    {
        return getIcon(CARD_OFF);
    }
    
    public static ImageIcon getCardOn()
    {
        return getIcon(CARD_ON);
    }
    
    private static ImageIcon getIcon(String path) {
        URL url = GUIResourcesHandler.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            throw new RuntimeException("Resource file not found: " + path);
        }
    }
}
    