package io.github._7isenko.confusingminecraft;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreeperSpawnListener implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType().equals(EntityType.CREEPER)) {
            event.getEntity().setInvulnerable(true);
        }
    }
}
