/**
 * Created by Ashrynn Macke | Flutterflies on 1/3/2016.
 *
 * Command executor for Waterless Redstone
 */
package net.flutterflies.waterless;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

class WaterCommand implements CommandExecutor {
    private String coloredPrefix = ChatColor.DARK_AQUA + "[" + ChatColor.LIGHT_PURPLE + "Waterless Redstone" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    private String plainPrefix = "[Waterless Redstone] ";
    private final Waterless plugin;

    WaterCommand(Waterless plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean cmdResult = false;
        if(cmd.getName().equalsIgnoreCase("wlrblocks")) {
            if(sender.hasPermission("wlr.blocks")) {
                if(sender instanceof Player) {
                    sender.sendMessage(coloredPrefix + makeString(plugin.getWaterlessMats()));
                    cmdResult = true;
                }
                else {
                    sender.sendMessage(plainPrefix + makeString(plugin.getWaterlessMats()));
                    cmdResult = true;
                }
            }
        }
        return cmdResult;
    }

    private String makeString(List<Material> list) {
        String names = "";

        for(Material material : list) {
            names = names + material.name().toLowerCase() + ", ";
        }

        return names;
    }
}