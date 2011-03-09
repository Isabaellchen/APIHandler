package net.altercon.apihandler.apis.permissions;

import net.altercon.apihandler.apis.UniqueAPI;

/**
 * Wraps the permissions interface and stores a reference to the registered interface.
 * 
 * @author Fabian Neundorf
 */
public class PermissionsAPI extends UniqueAPI<PermissionsInterface> implements PermissionsInterface{

	@Override
	public boolean hasPermission(String world, String player, String permission) {
		return this.plugin.hasPermission(world, player, permission);
	}

	@Override
	public String[] getGroups(String world, String player) {
		return this.plugin.getGroups(world, player);
	}

}
