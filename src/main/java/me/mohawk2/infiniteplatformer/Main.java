package me.mohawk2.infiniteplatformer;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //Sets up config file
        /*this.saveDefaultConfig();
        FileConfiguration fileOptions = this.getConfig();
        fileOptions.addDefault("maxXYOffset", 4); */

        //Initializes the click listener
        ClickListener clickListener = new ClickListener(this);
        getServer().getPluginManager().registerEvents(clickListener, this);

        //Initializes the team command listener
        this.getCommand("join").setExecutor(new TeamCommands());

        //Initializes the respawn listener
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);

        //Initializes the BlockPlaceListener
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(clickListener), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
