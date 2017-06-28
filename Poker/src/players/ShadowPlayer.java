package players;

/**
 * Classe che rappresenta la copia del giocatore senza informazioni sensibili
 */
public class ShadowPlayer extends Player{
        
    /**
     * Costruttore della classe
     * @param name il nome del giocatore
     * @param stake lo stake del giocatore
     * @param id l'id del giocatore
     */
    public ShadowPlayer(String name, int stake, int id) {
        super(name, stake, null);   
        super.setId(id);
        
    }

    /**
     * Ritorna se il giocatore ha le carte o meno
     * @return  true se non ha foldato, false altrimenti
     */
    @Override
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