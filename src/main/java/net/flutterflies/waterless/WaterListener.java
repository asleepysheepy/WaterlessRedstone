/**
 * Created by Ashrynn Macke | Flutterflies on 11/29/2015.
 */
package net.flutterflies.waterless;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EnumSet;

public class WaterListener implements Listener
{
    private EnumSet<Material> waterlessBlocks;
    public WaterListener()
    {
        waterlessBlocks = EnumSet.of
        (
            Material.REDSTONE_COMPARATOR,
            Material.REDSTONE_COMPARATOR_OFF,
            Material.REDSTONE_COMPARATOR_ON,
            Material.REDSTONE_TORCH_OFF,
            Material.REDSTONE_TORCH_ON,
            Material.REDSTONE_WIRE,
            Material.DIODE,
            Material.DIODE_BLOCK_OFF,
            Material.DIODE_BLOCK_ON,
            Material.LEVER,
            Material.STONE_BUTTON,
            Material.WOOD_BUTTON,
            Material.TRIPWIRE_HOOK,
            Material.TRIPWIRE,
            Material.STRING,
            Material.RAILS,
            Material.ACTIVATOR_RAIL,
            Material.DETECTOR_RAIL,
            Material.POWERED_RAIL
        );
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFromTo(BlockFromToEvent event)
    {
        if(waterlessBlocks.contains(event.getToBlock().getType()))
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getPlayer().getItemInHand().getType() == Material.WATER || event.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET)
            {
                if(waterlessBlocks.contains(event.getClickedBlock().getType()))
                {
                    event.setCancelled(true);
                }
            }
        }
    }
}
