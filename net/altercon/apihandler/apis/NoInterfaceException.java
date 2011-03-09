package net.altercon.apihandler.apis;

public class NoInterfaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8464439487633022638L;

	protected NoInterfaceException(String name) {
		super("No plugin found for: '" + name + "'");
	}
	
}
