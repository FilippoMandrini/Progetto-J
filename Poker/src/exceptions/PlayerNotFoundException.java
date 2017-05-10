package exceptions;

/**
 * Eccezione che segnala l'assenza del giocatore
 */
public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message) {
        super(message);
    }
    
}