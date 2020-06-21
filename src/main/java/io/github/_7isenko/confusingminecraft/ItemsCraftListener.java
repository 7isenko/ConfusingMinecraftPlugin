package io.github._7isenko.confusingminecraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class ItemsCraftListener implements Listener {
    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        Recipe recipe = e.getRecipe();
        String name = recipe != null ? recipe.getResult().getType().name() : "null";
        if (name.contains("HOE") || name.contains("FURNACE"))
            e.getInventory().setResult(new ItemStack(Material.AIR));
    }
}
