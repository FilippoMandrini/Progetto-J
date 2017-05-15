
package poker;

import java.util.ArrayList;

public class Turni {
    
    
    Board b1 = new Board();
    private int smallblind;
    private int bigblind;
    private boolean check;
    private boolean raise;
    private boolean fold;
    private boolean call;

    private void primoTurno(){
        b1.preflop();
        for(Player player : b1.getRanking()){
            
        }
        
        
    }
    
    
}
