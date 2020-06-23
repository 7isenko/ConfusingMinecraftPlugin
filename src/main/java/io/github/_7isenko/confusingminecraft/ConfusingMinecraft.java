package io.github._7isenko.confusingminecraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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

        // You can't use any furnace
        this.getServer().getPluginManager().registerEvents(new InteractionListener(), this);

        // Destroy blocks after 10 seconds
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        // Let shooting monsters run away
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new MobEscapingRunnable(),0, 20);

        // Bucket swap
        this.getServer().getPluginManager().registerEvents(new BucketUseListener(), this);

        // Hello
        this.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onChat(AsyncPlayerChatEvent event) {
                if (event.getMessage().equalsIgnoreCase("удиви меня"))
                    Bukkit.getServer().getScheduler().runTaskLater(ConfusingMinecraft.plugin, ()-> event.getPlayer().sendRawMessage("<Майнкрафт> Oк :)"), 20);
            }
        }, this);

        // Rotating recipes
        RecipeRedactor.rotateAll();


        // Add campfire recipes
        CampfireRecipeTranslator.addAll();

        // Swap pick and axe
        this.getServer().getPluginManager().registerEvents(new PickAxeRecipeSwapper(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}