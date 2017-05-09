
package exceptions;

public class NotEnoughCardsException extends RuntimeException {

    public NotEnoughCardsException(String message) {
        super(message);
    } 
}