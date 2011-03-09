package net.altercon.apihandler.apis.currency;

import net.altercon.apihandler.apis.UniqueAPI;

public class CurrencyAPI extends UniqueAPI<CurrencyInterface> implements CurrencyInterface {

	@Override
	public int balance(String playerName) {
		return this.plugin.balance(playerName);
	}

	@Override
	public void transfer(String sender, String recipient, int amount) {
		this.plugin.transfer(sender, recipient, amount);
	}

}
