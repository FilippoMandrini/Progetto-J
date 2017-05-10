/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Ludovico
 */
public class InvalidPlayerNameException extends RuntimeException {

    public InvalidPlayerNameException(String msg) {
        super(msg);
    }
}
