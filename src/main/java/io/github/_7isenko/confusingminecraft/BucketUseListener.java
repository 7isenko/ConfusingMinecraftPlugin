package io.github._7isenko.confusingminecraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class BucketUseListener implements Listener {
    @EventHandler
    public void onBucket(PlayerBucketEmptyEvent event) {
        Block block = event.getBlock();
        if (event.getBucket() == Material.WATER_BUCKET) {
            ConfusingMinecraft.plugin.getServer().getScheduler().runTaskLater(ConfusingMinecraft.plugin, () -> block.setType(Material.LAVA, true), 0);
        }
        if (event.getBucket() == Material.LAVA_BUCKET) {
            ConfusingMinecraft.plugin.getServer().getScheduler().runTaskLater(ConfusingMinecraft.plugin, () -> block.setType(Material.WATER, true), 0);
        }
    }
}
