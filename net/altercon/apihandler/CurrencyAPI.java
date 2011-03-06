
package net.altercon.apihandler;

/**
 * The API for all currency operations
 * @author Isa
 */
public class CurrencyAPI extends GenericAPI<CurrencyInterface> implements CurrencyInterface{

    public int balance(String playerName) {
        int result = 0;
        //TODO: Solve the problem of having multiple currency plugins
        for (CurrencyInterface p: clients) {
            result = p.balance(playerName);
        }
        return result;
    }

    public void transfer(String sender, String recipient, int amount) {
        for (CurrencyInterface p: clients) {
            p.transfer(sender, recipient, amount);
        }
    }

}
