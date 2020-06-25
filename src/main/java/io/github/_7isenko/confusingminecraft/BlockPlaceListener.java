package io.github._7isenko.confusingminecraft;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.util.CraftMagicNumbers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.lang.reflect.Field;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType().equals(Material.CAMPFIRE))
            return;
        Bukkit.getScheduler().runTaskLater(ConfusingMinecraft.plugin, () -> {
            try {
                if (event.getBlockPlaced().getType() == Material.AIR)
                    return;
                Block block = CraftMagicNumbers.getBlock(event.getBlockPlaced().getType());
                SoundEffectType set = block.getBlockData().r();
                Field f = set.getClass().getDeclaredField("z");
                f.setAccessible(true);
                SoundEffect se = (SoundEffect) f.get(set);
                Location location = event.getBlockPlaced().getLocation();
                BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
                ((CraftWorld) event.getPlayer().getWorld()).getHandle().playSound(null, bp, se, SoundCategory.BLOCKS, 1f, 1f);

            } catch (Exception e) {
                // ok fuck it
            }
            event.getBlockPlaced().breakNaturally();
        }, 200);
    }
}
