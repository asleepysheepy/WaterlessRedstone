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


public class Waterless extends JavaPlugin
{
    FileConfiguration config;
    List<Material> waterlessMats;

    @Override
    public void onEnable()
    {
        config = getConfig();
        waterlessMats.addAll(makeList(config.getStringList("redstone")));
        waterlessMats.addAll(makeList(config.getStringList("minecart")));
        Bukkit.getPluginManager().registerEvents(new WaterListener(waterlessMats), this);

    }

    @Override
    public void onDisable()
    {
        config = null;
        Bukkit.getScheduler().cancelTasks(this);
    }

    public List<Material> makeList(List<String> list)
    {
        List<Material> materialList = new ArrayList<Material>();
        for(String string : list)
        {
            materialList.add(Material.getMaterial(string));
        }
        return materialList;
    }
}
