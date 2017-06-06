package players;

/**
 * Classe che rappresenta la copia del giocatore senza informazioni sensibili
 */
public class ShadowPlayer extends Player{
    
    private boolean hasCards;
    
    /**
     * Costruttore della classe
     * @param name il nome del giocatore
     * @param stake lo stake del giocatore
     */
    public ShadowPlayer(String name, int stake) {
        super(name, stake, null);   
        
    }

    /**
     * Ritorna se il giocatore ha le carte o meno
     * @return  true se non ha foldato, false altrimenti
     */
    public boolean hasCards() {
        return hasCards;
    }

    /**
     * Imposta se un giocatore ha le carte o meno
     * @param hasCards true se le ha, false altrimenti
     */
    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
    } 
    
}