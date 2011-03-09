
package net.altercon.apihandler.apis.currency;

import net.altercon.apihandler.apis.NoInterfaceException;

/**
 * Thrown when a plugin wants to make use of Currency while there is no
 * currency plugin installed.
 * @author Isabaellchen
 */
public class NoCurrencyPluginException extends NoInterfaceException {

    public NoCurrencyPluginException() {
        super("currency");
    }

}
