package io.github._7isenko.confusingminecraft;

import org.bukkit.Bukkit;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;

public class CampfireRecipeTranslator {
    public static void addAll() {
        Bukkit.getServer().recipeIterator().forEachRemaining(recipe -> {
            if (recipe instanceof FurnaceRecipe) {
                Bukkit.getServer().addRecipe(cast((FurnaceRecipe) recipe));
            }
        });
    }

    private static CampfireRecipe cast(FurnaceRecipe furnaceRecipe) {
        return new CampfireRecipe(furnaceRecipe.getKey(), furnaceRecipe.getResult(), furnaceRecipe.getInput().getType(), furnaceRecipe.getExperience(), furnaceRecipe.getCookingTime() * 3);
    }

}
