package exceptions;

/**
 * Eccezione che segnala che il nome scelto dall'utente è già in uso
 */
public class IllegalActionException extends RuntimeException {

    public IllegalActionException(String message) {
        super(message);
    }
    
}