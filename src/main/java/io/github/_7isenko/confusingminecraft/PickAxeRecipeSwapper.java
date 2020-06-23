package io.github._7isenko.confusingminecraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class PickAxeRecipeSwapper implements Listener {
    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent  event) {
        if (event.getRecipe() instanceof ShapedRecipe) {
            ShapedRecipe recipe = (ShapedRecipe)event.getRecipe();
            Material type = recipe.getResult().getType();
            String name = type.name();
            if (name.contains("_PICKAXE")) {
                switch (type) {
                    case WOODEN_PICKAXE:
                        setResult(event, Material.WOODEN_AXE);
                        break;
                    case STONE_PICKAXE:
                        setResult(event, Material.STONE_AXE);
                        break;
                    case IRON_PICKAXE:
                        setResult(event, Material.IRON_AXE);
                        break;
                    case GOLDEN_PICKAXE:
                        setResult(event, Material.GOLDEN_AXE);
                        break;
                    case DIAMOND_PICKAXE:
                        setResult(event, Material.DIAMOND_AXE);
                        break;
                }
            }
            if (name.contains("_AXE")) {
                switch (type) {
                    case WOODEN_AXE:
                        setResult(event, Material.WOODEN_PICKAXE);
                        break;
                    case STONE_AXE:
                        setResult(event, Material.STONE_PICKAXE);
                        break;
                    case IRON_AXE:
                        setResult(event, Material.IRON_PICKAXE);
                        break;
                    case GOLDEN_AXE:
                        setResult(event, Material.GOLDEN_PICKAXE);
                        break;
                    case DIAMOND_AXE:
                        setResult(event, Material.DIAMOND_PICKAXE);
                        break;
                }
            }
        }
    }
    private void setResult(PrepareItemCraftEvent event, Material material) {
        event.getInventory().setResult(new ItemStack(material));
    }
}
