package players;


import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe astratta che implementa il client
 */
public abstract class HumanStrategy implements Client {

    
    protected Socket socket;
    protected Scanner in = null;
    protected PrintStream out = null;
    
    /**
     * Costruttore della classe
     * @param socket socket del client
     */
    public HumanStrategy(Socket socket) {
        this.socket = socket;
       
    }
    
    

}