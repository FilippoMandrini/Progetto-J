package players;

import actions.Action;
import actions.GenericAction;
import java.io.BufferedReader;
import java.util.Scanner;

public class TestPlayer extends Player {
    
    public TestPlayer(String name) {
        super(name, new HumanTestStrategy());
    } 
}
