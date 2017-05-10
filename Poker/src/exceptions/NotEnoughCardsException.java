package exceptions;

/**
 * Eccezione che segnala un numero errato di carte
 */
public class NotEnoughCardsException extends RuntimeException {

    public NotEnoughCardsException(String message) {
        super(message);
    }
    
}