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

import java.util.ArrayList;
import java.util.List;

public class WaterListener implements Listener {
	private static List<Material> materials = new ArrayList<Material>();

	public WaterListener(List<Material> list) {
		materials = list;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockFromTo(BlockFromToEvent event) {
		if(materials.contains(event.getToBlock().getType())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getItemInHand().getType() == Material.WATER || event.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET) {
				if(materials.contains(event.getClickedBlock().getType())) {
					event.setCancelled(true);
				}
			}
		}
	}
}
