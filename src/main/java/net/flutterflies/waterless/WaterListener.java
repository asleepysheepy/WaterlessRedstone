/**
 * Created by Ashrynn Macke | Flutterflies on 29 November 2015
 *
 * This class was created as a part of Waterless Redstone, a
 * plugin for SpigotMC. Waterless Redstone is licensed under
 * the MIT License. You should have received a copy of this
 * license included with this source code, if not, please find
 * it here: http://flutterflies.net/license.html
 */
package net.flutterflies.waterless;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Listener for Waterless Redstone events.
 *
 * @author Ashrynn MAcke | Flutterflies
 */
class WaterListener implements Listener {

    /**
     * Instance of the main plugin class.
     */
    private final Waterless plugin;

    /**
     * Construct a new instance of the Waterless Event Listener.
     *
     * @param plugin Instance of the main plugin class.
     */
    WaterListener(Waterless plugin) {
        this.plugin = plugin;
    }

    /**
     * Event called when a flowing liquid washes away a block.
     *
     * @param event The BlockFromToBlock
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFromTo(BlockFromToEvent event) {
        if(plugin.getWaterlessMats().contains(event.getToBlock().getType())) {
            event.setCancelled(true);
        }
    }

    /**
     * Player Interact Event used to prevent players from directly
     * placing water buckets on blocks that are are affected by this
     * plugin.
     *
     * @param event The PlayerInteractEvent
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {

        //get the version number
        String version = plugin.getServer().getClass().getPackage().getName();
        version = version.substring(version.lastIndexOf('.') + 1);

        Material materialInHand, materialInOffHand = null;

        //The the material type of the item that was used to interact
        if(version.contains("1_8")) {
            materialInHand = event.getPlayer().getItemInHand().getType();
        }
        else {
            materialInHand = event.getPlayer().getInventory().getItemInMainHand().getType();
            materialInOffHand = event.getPlayer().getInventory().getItemInOffHand().getType();
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(plugin.getWaterlessMats().contains(event.getClickedBlock().getType())) {
                if(materialInHand.equals(Material.WATER) || materialInHand.equals(Material.WATER_BUCKET)) {
                    event.setCancelled(true);
                }
                else if(materialInOffHand != null) {
                    if(materialInOffHand.equals(Material.WATER) || materialInOffHand.equals(Material.WATER_BUCKET)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
