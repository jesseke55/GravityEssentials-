package net.GravityNetwork.Essentials;

import net.GravityNetwork.Essentials.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Main extends JavaPlugin implements Listener {

    private static Plugin plugin;
    
    public void onEnable(){
        Main.plugin = this;
        
        initCommands();
        initConfig();
        initListeners();
        initChannels();
        initSave();
    }
    public void onDisable(){
        Main.plugin = null;
    }
    
    public static Plugin getPlugin() {
        return plugin;
    }

    private void initChannels() {
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void initListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        
        pm.registerEvents(new Afk(), this);
        pm.registerEvents(new Broadcast(), this);
        pm.registerEvents(new Echest(), this);
        pm.registerEvents(new InventoryInspector(), this);
        pm.registerEvents(new Poke(), this);
    }

    private void initCommands(){
        getCommand("afk").setExecutor(new Afk());
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("enderchest").setExecutor(new Echest());
        getCommand("enchanttable").setExecutor(new EnchantTable());
        getCommand("viewinv").setExecutor(new InventoryInspector());
        getCommand("poke").setExecutor(new Poke());
        getCommand("shout").setExecutor(new Shout());
        getCommand("craft").setExecutor(new Workbench());
    }

    private void initConfig(){
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void initSave(){

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Prefixes.Prefix") + Main.getPlugin().getConfig().getString("AutoSave.complete")));
                for (World w : Bukkit.getWorlds()) {
                    w.save();
                    w.setAutoSave(true);
                    w.setKeepSpawnInMemory(false);
                    System.gc();

                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Prefixes.Prefix") + Main.getPlugin().getConfig().getString("AutoSave.complete")));

            }
        }, 0L, 1200 * Main.getPlugin().getConfig().getInt("AutoSaveInterval"));


    }
}
