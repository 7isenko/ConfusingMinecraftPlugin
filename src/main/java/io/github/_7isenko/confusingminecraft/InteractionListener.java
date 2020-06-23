package io.github._7isenko.confusingminecraft;

import net.minecraft.server.v1_15_R1.Explosion;
import net.minecraft.server.v1_15_R1.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftCampfire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InteractionListener implements Listener {
    private static List<Material> types = Arrays.asList(Material.FURNACE, Material.FURNACE_MINECART, Material.BLAST_FURNACE, Material.SMOKER);

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block != null && types.contains(block.getType()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            block.getWorld().createExplosion(block.getLocation(), 4);
        }

        if (block != null && block.getType() == Material.CAMPFIRE && event.getMaterial() != Material.AIR && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            Campfire campfire = (Campfire) block.getState();
            for (int i = 0; i < 4; i++) {
                if (campfire.getItem(i) != null)
                    continue;
                ItemStack is = event.getItem().clone();
                is.setAmount(1);
                campfire.setItem(i, is);
                campfire.setCookTime(i, 0);
                campfire.setCookTimeTotal(i, 600);
                campfire.update();
                event.getItem().setAmount(event.getItem().getAmount() - 1);
                break;
            }

        }
    }
}
