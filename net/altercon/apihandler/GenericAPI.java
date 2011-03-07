
package net.altercon.apihandler;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generic Observer-Pattern for APIs
 * @author Isabaellchen
 * @param <E> an API Interface
 */
public class GenericAPI<E> {

    ArrayList<E> clients = new ArrayList<E>();

    public void registerPlugin(E plugin){
        clients.add(plugin);
    }

    public void unregisterPlugin(E plugin){
        clients.remove(plugin);
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }
    
}
