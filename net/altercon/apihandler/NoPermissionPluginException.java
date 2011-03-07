
package net.altercon.apihandler;

/**
 * Thrown when a plugin wants to make use of Permissions while there is no
 * permissions plugin installed.
 * @author Isabaellchen
 */
public class NoPermissionPluginException extends Exception{

    public NoPermissionPluginException() {
        super("No permissions plugin found!");
    }

}
