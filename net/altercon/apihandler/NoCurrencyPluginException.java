
package net.altercon.apihandler;

/**
 * Thrown when a plugin wants to make use of Currency while there is no
 * currency plugin installed.
 * @author Isabaellchen
 */
public class NoCurrencyPluginException extends Exception {

    public NoCurrencyPluginException() {
        super("No currency plugin found!");
    }

}
