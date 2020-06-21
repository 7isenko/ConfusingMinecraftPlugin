package io.github._7isenko.confusingminecraft;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfusingMinecraft extends JavaPlugin {
    static Plugin plugin;
    // How to build: Maven/ConfusingMinecraft/Lifecycle/package
    @Override
    public void onEnable() {
        plugin = this;
        // Let creepers be invulnerable
        this.getServer().getPluginManager().registerEvents(new CreeperSpawnListener(), this);

        // No hoes and furnaces
        this.getServer().getPluginManager().registerEvents(new ItemsCraftListener(), this);

        // Destroy blocks after 10 seconds
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        // Let shooting monsters run away
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new MobEscapingRunnable(),0, 20);

        // Bucket swap
        this.getServer().getPluginManager().registerEvents(new BucketUseListener(), this);

        // Rotating recipes
        RecipeRotatory.rotateAll();

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}