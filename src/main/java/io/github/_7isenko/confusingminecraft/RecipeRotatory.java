package io.github._7isenko.confusingminecraft;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;

public class RecipeRotatory {
    public static void rotateAll() {
        Bukkit.getServer().recipeIterator().forEachRemaining(recipe -> {
            if (recipe instanceof ShapedRecipe) {
                ShapedRecipe r = (ShapedRecipe) recipe;
                Bukkit.removeRecipe(r.getKey());
                r.shape(rotate(r.getShape()));
                Bukkit.addRecipe(r);
            }
        });
    }
    private static String[] rotate(String[] rows) {
        String[][] transpose = new String[rows[0].length()][rows.length];
        int maxI = rows.length-1;
        int maxJ = rows[0].length()-1;
        for (int i = 0; i <= maxI; i++) {
            for (int j = 0; j <= maxJ; j++) {
                transpose[j][maxI - i] = String.valueOf(rows[i].charAt(j));
            }
        }
        String[] fin = new String[rows[0].length()];
        for (int i = 0; i < rows[0].length(); i++) {
            fin[i] = String.join("", transpose[i]);
        }
        return fin;
    }
}
