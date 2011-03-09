package net.altercon.apihandler;

import java.util.HashMap;
import java.util.logging.Logger;

import net.altercon.apihandler.apis.APIs;
import net.altercon.apihandler.apis.currency.CurrencyAPI;
import net.altercon.apihandler.apis.currency.CurrencyInterface;
import net.altercon.apihandler.apis.currency.NoCurrencyPluginException;
import net.altercon.apihandler.apis.permissions.NoPermissionPluginException;
import net.altercon.apihandler.apis.permissions.PermissionsAPI;
import net.altercon.apihandler.apis.permissions.PermissionsInterface;

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
    private boolean isCurrencyLoaded = false;
    /**
     * The respective PermissionAPI
     */
    private PermissionsAPI permissionsAPI = new PermissionsAPI();
    private boolean isPermissionsLoaded = false;

    public void onEnable() {
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

    @Override
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
        if (!isPermissionsLoaded) {
            registerPlugins(APIs.Permissions);
            isPermissionsLoaded = true;
        }
        if (!permissionsAPI.isRegistered()) {
            throw new NoPermissionPluginException();
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
        if (!isCurrencyLoaded) {
            registerPlugins(APIs.Currency);
            isCurrencyLoaded = true;
        }
        if (!currencyAPI.isRegistered()) {
            throw new NoCurrencyPluginException();
        }
        return currencyAPI;
    }

    /**
     * Registers plugins to their respective Category API
     * @param p The plugin to be added
     * @param api The desired Category
     */
    public void registerPlugins(APIs api) {
        switch (api) {
            case Currency:
                for (Plugin p : getServer().getPluginManager().getPlugins()) {
                    if (CurrencyInterface.class.isInstance(p)) {
                        currencyAPI.registerPlugin((CurrencyInterface) p);
                    }
                }
                break;
            case Permissions:
                for (Plugin p : getServer().getPluginManager().getPlugins()) {
                    if (PermissionsInterface.class.isInstance(p)) {
                        permissionsAPI.registerPlugin((PermissionsInterface) p);
                    }
                }
                break;
        }
    }
}
