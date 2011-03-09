package net.altercon.apihandler.apis;

public class UniqueAPI<E> implements GenericAPI<E> {

	protected E plugin;
	
	@Override
	public void registerPlugin(E plugin) {
		this.plugin = plugin;
	}

	@Override
	public void unregisterPlugin(E plugin) {
		if (this.plugin == plugin || plugin == null) {
			this.plugin = null;
		}
	}

	@Override
	public boolean isRegistered() {
		return this.plugin != null;
	}

}
