package net.altercon.apihandler.apis;

public interface GenericAPI<E> {

	void registerPlugin(E plugin);
	
	void unregisterPlugin(E plugin);
	
	boolean isRegistered();
	
}
