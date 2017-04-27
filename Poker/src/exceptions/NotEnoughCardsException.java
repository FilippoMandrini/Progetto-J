/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Nickelsilver
 */
public class NotEnoughCardsException extends RuntimeException {

    public NotEnoughCardsException(String message) {
        super(message);
    }
    
    
}
