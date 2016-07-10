/**
 * Created by Ashrynn Macke | Flutterflies on 11/29/2015.
 *
 * Listener for events
 */
package net.flutterflies.waterless;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

class WaterListener implements Listener {
    private final Waterless plugin;

    WaterListener(Waterless plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFromTo(BlockFromToEvent event) {
        if(plugin.getWaterlessMats().contains(event.getToBlock().getType())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {

        String version = plugin.getServer().getClass().getPackage().getName();
        version = version.substring(version.lastIndexOf('.') + 1);

        Material materialInHand, materialInOffHand = null;

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
