package exceptions;

/**
 * Eccezione che segnala un numero errato di carte
 */
public class WrongCardNumberException extends RuntimeException {

    public WrongCardNumberException(String message) {
        super(message);
    }
    
}