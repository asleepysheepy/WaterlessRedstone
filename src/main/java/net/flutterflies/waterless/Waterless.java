/**
 * Created by Ashrynn Macke | Flutterflies on 11/28/2015.
 *
 * Main Plugin class
 */
package net.flutterflies.waterless;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Waterless extends JavaPlugin
{
    WaterListener waterListener = new WaterListener();

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(waterListener, this);
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
