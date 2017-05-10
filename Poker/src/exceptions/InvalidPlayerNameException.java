package exceptions;

/**
 * Eccezione che segnala che il nome scelto dall'utente è già in uso
 */
public class InvalidPlayerNameException extends RuntimeException {

    public InvalidPlayerNameException(String message) {
        super(message);
    }
    
}