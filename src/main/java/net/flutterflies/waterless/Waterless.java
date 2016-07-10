/**
 * Created by Ashrynn Macke | Flutterflies on 11/28/2015.
 *
 * Main Plugin class
 */
package net.flutterflies.waterless;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Waterless extends JavaPlugin {
    private FileConfiguration config;
    private List<Material> waterlessMats = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.config = this.getConfig();


        init();

        getServer().getPluginManager().registerEvents(new WaterListener(this), this);
        getCommand("wlrblocks").setExecutor(new WaterCommand(this));
    }

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

    private List<Material> makeList(List<String> list) {
        List<Material> materialList = new ArrayList<>();

        list.stream()
                .filter(string -> string != null)
                .forEach(string -> materialList.add(Material.getMaterial(string)));
        return materialList;
    }

    List<Material> getWaterlessMats() {
        return this.waterlessMats;
    }
}