
package players;

import actions.Action;
import actions.ActionSet;
import java.util.Set;
import poker.GameObserver;

public interface Client extends GameObserver{
    
    public Action act(Set<ActionSet> allowedActions);
    
}
