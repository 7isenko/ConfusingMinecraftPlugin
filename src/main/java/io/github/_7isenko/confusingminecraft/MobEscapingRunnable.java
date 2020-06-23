package io.github._7isenko.confusingminecraft;

import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class MobEscapingRunnable implements Runnable {
    private static List<EntityType> types;

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
                return;
            player.getNearbyEntities(8, 8, 8).forEach((entity -> {
                if (types.contains(entity.getType()) && entity.isValid() && !entity.isDead() && player.getWorld().equals(entity.getWorld())) {
                    EntityCreature insentient = (EntityCreature) ((CraftEntity) entity).getHandle();
                    insentient.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(insentient, EntityPlayer.class, 6F, 1.6D, 2.2D));
                }
            }));
        }
    }

    static {
        types = Arrays.asList(EntityType.BLAZE, EntityType.PILLAGER, EntityType.SKELETON, EntityType.STRAY, EntityType.GUARDIAN);
    }
}
