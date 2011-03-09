
package net.altercon.apihandler.apis.permissions;

import net.altercon.apihandler.apis.NoInterfaceException;

/**
 * Thrown when a plugin wants to make use of Permissions while there is no
 * permissions plugin installed.
 * @author Isabaellchen
 */
public class NoPermissionPluginException extends NoInterfaceException {

    public NoPermissionPluginException() {
        super("permissions");
    }

}
