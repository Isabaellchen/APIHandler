
package net.altercon.apihandler;

/**
 * Defines the standard methods that a Currency plugin should have.
 * @author Isabaellchen
 */
public interface CurrencyInterface {

    /**
     * Checks the current balance of a player.
     * @param playerName
     * @return
     */
    public int balance(String playerName);

    /**
     * Standard method to transfer money
     * @param sender
     * @param recipient
     * @param amount
     */
    public void transfer(String sender, String recipient, int amount);
}
