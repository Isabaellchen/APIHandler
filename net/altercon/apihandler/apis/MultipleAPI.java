package net.altercon.apihandler.apis;

import java.util.ArrayList;
import java.util.List;

public class MultipleAPI<E> implements GenericAPI<E> {

	protected List<E> plugins = new ArrayList<E>();
	
	@Override
	public void registerPlugin(E plugin) {
		this.plugins.add(plugin);
	}

	@Override
	public void unregisterPlugin(E plugin) {
		this.plugins.remove(plugin);
	}

	public void clear() {
		this.plugins.clear();
	}
	
	@Override
	public boolean isRegistered() {
		return !this.plugins.isEmpty();
	}

}
