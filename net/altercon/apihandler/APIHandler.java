package net.altercon.apihandler;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * This Plugin does API handling.
 * It works intermediary between plugins implementing the interfaces
 * introduced by this plugin and plugins that want to make use of the methods
 * defined by those interfaces.
 *
 * @author Isabaellchen
 */
public class APIHandler extends JavaPlugin {

    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public static final Logger log = Logger.getLogger("Minecraft");
    private String pluginName;
    /**
     * The CurrencyAPI that receives all actions that were made by a plugin
     * implementing the CurrencyInterface
     */
    private CurrencyAPI currencyAPI = new CurrencyAPI();
    /**
     * The respective PermissionAPI
     */
    private PermissionsAPI permissionsAPI = new PermissionsAPI();

    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();

        //For each plugin loaded, check if an Interface is being used and
        //register it to the respective API as a listener
        for (Plugin p : pm.getPlugins()) {
            if (CurrencyInterface.class.isInstance(p)) {
                registerPlugin(p, APIs.Currency);
            }
            if (PermissionsInterface.class.isInstance(p)) {
                registerPlugin(p, APIs.Permissions);
            }
        }

        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");

        pluginName = pdfFile.getName();
    }

    public void onDisable() {
        System.out.println(pluginName + ": Goodbye world!");
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        return true;
    }

    /**
     * Getter for the PermissionAPI.
     * @return The PermissionAPI holding all the client plugins as listeners.
     * @throws NoPermissionPluginException When the PermissionAPI is requested
     * while there is no Permission plugin installed.
     */
    public PermissionsAPI getPermissionsAPI() throws NoPermissionPluginException {
        if (permissionsAPI.isEmpty()) {
            throw new NoPermissionPluginException("No Permission plugin installed!");
        }
        return permissionsAPI;
    }

    /**
     * Getter for the CurrencyAPI.
     * @return The CurrencyAPI holding all the client plugins as listeners.
     * @throws NoCurrencyPluginException When the CurrencyAPI is requested
     * while there is no Currency plugin installed.
     */
    public CurrencyAPI getCurrencyAPI() throws NoCurrencyPluginException {
        if (permissionsAPI.isEmpty()) {
            throw new NoCurrencyPluginException("No Currency plugin installed!");
        }
        return currencyAPI;
    }

    /**
     * Registers plugins to their respective Category API
     * @param p The plugin to be added
     * @param api The desired Category
     */
    public void registerPlugin(Plugin p, APIs api) {
        switch (api) {
            case Currency:
                currencyAPI.registerPlugin((CurrencyInterface) p);
                break;
            case Permissions:
                permissionsAPI.registerPlugin((PermissionsInterface) p);
                break;
        }
    }
}
