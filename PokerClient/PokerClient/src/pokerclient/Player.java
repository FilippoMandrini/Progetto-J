
package pokerclient;

import java.util.Scanner;

public class Player {
    
    String nome;
    
    public Player(String nome) {
        this.nome = nome;
    }
    
    public String act() {
        System.out.println("Digitare un messaggio");
        Scanner tastiera = new Scanner(System.in);
        return this.nome + " " + tastiera.nextLine(); 
    }
}