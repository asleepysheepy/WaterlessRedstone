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
    List<Material> waterlessMats = new ArrayList<Material>();

    @Override
    public void onEnable()
    {
        config = this.getConfig();
        List<String> stringList = config.getStringList("redstone");
        if(stringList != null)
        {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling Redstone blocks");
        }
        else
        {
            getLogger().info("No Redstone blocks to enable");
        }
        stringList = config.getStringList("minecart");
        if(stringList != null)
        {
            waterlessMats.addAll(makeList(stringList));
            getLogger().info("Enabling Minecart rails.");
        }
        else
        {
            getLogger().info("No Minecart rails to enable.");
        }
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
            if(string != null)
            {
                materialList.add(Material.getMaterial(string));
            }
        }
        return materialList;
    }
}