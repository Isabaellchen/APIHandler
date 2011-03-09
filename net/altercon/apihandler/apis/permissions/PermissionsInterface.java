
package net.altercon.apihandler.apis.permissions;

/**
 *
 * @author Isa
 */
public interface PermissionsInterface {

	boolean hasPermission(String world, String player, String permission);
	
	String[] getGroups(String world, String player);
	
}
