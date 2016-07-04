/**
 * Created by Ashrynn Macke | Flutterflies on 11/28/2015.
 *
 * Main Plugin class
 */
package net.flutterflies.waterless;

import org.bukkit.Bukkit;
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
        config = this.getConfig();
        saveDefaultConfig();
        List<String> stringList = config.getStringList("redstone");

        if(stringList != null) {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling Redstone blocks");
        }
        else {
            getLogger().info("No Redstone blocks to enable");
        }

        stringList = config.getStringList("minecart");
        if(stringList != null) {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling Minecart rails.");
        }
        else {
            getLogger().info("No Minecart rails to enable.");
        }

        stringList = config.getStringList("foliage");
        if(stringList != null) {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling Foliage blocks");
        }
        else {
            getLogger().info("No Foliage blocks to enable");
        }

        stringList = config.getStringList("other");
        if(stringList != null) {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling all other blocks");
        }
        else {
            getLogger().info("No other blocks to enable");
        }

        Bukkit.getPluginManager().registerEvents(new WaterListener(waterlessMats), this);
        getCommand("wlrblocks").setExecutor(new WaterCommand(waterlessMats));
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        config = null;
        Bukkit.getScheduler().cancelTasks(this);
    }

    private List<Material> makeList(List<String> list) {
        List<Material> materialList = new ArrayList<>();

        list.stream()
            .filter(string -> string != null)
            .forEach(string -> materialList.add(Material.getMaterial(string)));
        return materialList;
    }
}