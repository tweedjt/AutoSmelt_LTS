package me.tweedjt.autosmelt;
import me.tweedjt.autosmelt.data.SmeltData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;


import me.tweedjt.autosmelt.bukkitlisteners.BlockListener;
import me.tweedjt.autosmelt.bukkitlisteners.PlayerListener;
import me.tweedjt.autosmelt.bukkitlisteners.ReloadCommandListener;
import me.tweedjt.autosmelt.commands.ASCommandListener;
import me.tweedjt.autosmelt.commands.SmeltCommandListener;
import me.tweedjt.autosmelt.util.AutoSmeltConfig;
import me.tweedjt.autosmelt.util.Log;


public class AutoSmelt extends JavaPlugin {

    // NOTE: Moved all the data elements to data\SmeltData

    // Instance
    // Create an instance of the main class so we can call it from other classes
    private static AutoSmelt instance;
    public static AutoSmelt getInstance() {
        return instance;
    }

    // Create a variable holding our data class, we'll set this in the onEnable
    private SmeltData smeltData;
    public SmeltData getSmeltData() { return this.smeltData; }

    // NOTE: Moved this near the top for ease of reading
    private AutoSmeltConfig config;
    public AutoSmeltConfig getAutoSmeltConfig() {
        return this.config;
    }
    public void setAutoSmeltConfig(AutoSmeltConfig config) {
        this.config = config;
    }

    // TODO: Move WorldGuard lookups to their own class
    private WorldGuardPlugin wg;
    private WorldEditPlugin we;
    private boolean hasWorldGuard = false;
    public boolean hookWorldGuard()
    {
        Plugin wgPlug = getServer().getPluginManager().getPlugin("WorldGuard");
        Plugin wePlug = getServer().getPluginManager().getPlugin("WorldEdit");

        if (wgPlug != null && wePlug != null)
        {
            wg = ((WorldGuardPlugin) wgPlug);
            we = ((WorldEditPlugin) wePlug);

            hasWorldGuard = true;
            return true;
        } else {
            return false;
        }
    }
    public boolean hasWorldGuard() {
        return hasWorldGuard;
    }


    public WorldGuardPlugin getWorldGuard() {
        return wg;
    }
    public WorldEditPlugin getWorldEdit() {
        return we;
    }

    // On startup
    @Override
    public void onEnable() {
        
        instance = this;

        // Populate our data class with a new instance, but pass in "this" which is our existing instance
        // this will help avoid static abuse
        this.smeltData = new SmeltData(this);

        saveDefaultConfig();

        config = new AutoSmeltConfig(this);

        if (hookWorldGuard()) {
            Log.logToConsole("WorldGuard functionality added");
        } else {
            Log.logToConsole("WorldGuard functionality NOT added");
        }

        Log.logToConsole("Starting AutoSmelt");

        // Add command listeners
        try {
            getCommand("as").setExecutor(new ASCommandListener()); // as
            getCommand("smelt").setExecutor(new SmeltCommandListener()); // smelt <playername>
            getCommand("asreload").setExecutor(new ReloadCommandListener());
        } catch (Exception ex) {
            // If we have an error, output it
            Log.logToConsole("Unexpected Error - " + ex.getMessage());
        }



        // Register event listeners

        // Block Event listener for smelting
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        // Player Event listener for when players join
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);



    }



    // On shutdown
    @Override
    public void onDisable() {
        Log.logToConsole("Disabling AutoSmelt");
    }

}