/**
 * Created by Ashrynn Macke | Flutterflies on 3 January 2016.
 *
 * This class was created as a part of Waterless Redstone, a
 * plugin for SpigotMC. Waterless Redstone is licensed under
 * the MIT License. You should have received a copy of this
 * license included with this source code, if not, please find
 * it here: http://flutterflies.net/license.html
 */
package net.flutterflies.waterless;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Command executor for Waterless Redstone.
 *
 * @author Ashrynn Macke | Flutterflies
 */
class WaterCommand implements CommandExecutor {

    /**
     * Instance of the main plugin class.
     */
    private final Waterless plugin;

    /**
     * Colored chat prefix to sending messages in game to players.
     */
    private String coloredPrefix = ChatColor.DARK_AQUA + "[" + ChatColor.LIGHT_PURPLE + "Waterless Redstone" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;

    /**
     * Non colored chat message for sending messages to console.
     */
    private String plainPrefix = "[Waterless Redstone] ";

    /**
     * Construct a new instance of the waterless command executor.
     *
     * @param plugin Instance of the main plugin class.
     */
    WaterCommand(Waterless plugin) {
        this.plugin = plugin;
    }

    /**
     * Executor method called when the wlrblocks command is executed
     *
     * @param sender Who sent the command.
     * @param cmd    The command that was sent.
     * @param label  The aliases that was used foe the command.
     * @param args   The arguments given to the command.
     * @return True if the command was successfully executed.
     */
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

    /**
     * Make a single String from the given list of materials
     *
     * @param list A list of Materials to turn into a string
     * @return A string of the names of all the elements tof the provided list.
     */
    private String makeString(List<Material> list) {
        String names = "";

        for(Material material : list) {
            names = names + material.name().toLowerCase() + ", ";
        }

        return names;
    }
}