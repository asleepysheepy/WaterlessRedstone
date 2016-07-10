/**
 * Created by Ashrynn Macke | Flutterflies on 28 November 2016.
 *
 * This class was created as a part of Waterless Redstone, a
 * plugin for SpigotMC. Waterless Redstone is licensed under
 * the MIT License. You should have received a copy of this
 * license included with this source code, if not, please find
 * it here: http://flutterflies.net/license.html
 */
package net.flutterflies.waterless;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Plugin class
 *
 * @version 1.4
 * @author Ashrynn Macke
 */
public class Waterless extends JavaPlugin {

    /**
     * The plugin's configuration file
     */
    private FileConfiguration config;

    /**
     * List of all materials to be affected by the plugin
     */
    private List<Material> waterlessMats = new ArrayList<>();

    /**
     * Method called to set up and enable the plugins features.
     */
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.config = this.getConfig();

        init();

        getServer().getPluginManager().registerEvents(new WaterListener(this), this);
        getCommand("wlrblocks").setExecutor(new WaterCommand(this));
    }

    /**
     * Initialize all of the materials to be affected by the plugin.
     */
    private void init() {

        //Redstone Blocks
        waterlessMats.addAll(makeList(config.getStringList("redstone")));

        //Minecart tracks
        waterlessMats.addAll(makeList(config.getStringList("minecart")));

        //Foliage
        waterlessMats.addAll(makeList(config.getStringList("foliage")));

        //Other
        waterlessMats.addAll(makeList(config.getStringList("other")));
    }

    /**
     * Converts a list of strings into their corresponding material type.
     *
     * @param list The list of materials to convert
     * @return The List of Materials
     *
     * @see org.bukkit.Material
     */
    private List<Material> makeList(List<String> list) {
        List<Material> materialList = new ArrayList<>();

        list.stream()
                .filter(string -> string != null)
                .forEach(string -> materialList.add(Material.getMaterial(string)));
        return materialList;
    }

    /**
     * Get the list of materials to be affected by the plugin.
     *
     * @return The list of affected materials
     */
    List<Material> getWaterlessMats() {
        return this.waterlessMats;
    }
}