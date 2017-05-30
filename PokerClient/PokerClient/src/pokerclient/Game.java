
package pokerclient;

import java.util.ArrayList;
import java.util.List;

public class Game {
    
    List<ServerClient> lista = new ArrayList<>();
    
    public void addServerClient (ServerClient a)
    {
        this.lista.add(a);
    }
    
    public void playGame()
    {
        while (true)
        {
            for (ServerClient s : lista) {
                s.out.println("inizio");
                System.out.println(s.act());
            }
        }
    }    
}