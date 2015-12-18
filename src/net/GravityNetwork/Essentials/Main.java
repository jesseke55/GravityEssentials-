package net.GravityNetwork.Essentials;

import net.GravityNetwork.Essentials.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Main extends JavaPlugin implements Listener {

    private PluginManager pm = Bukkit.getPluginManager();
    public static Plugin getPlugin() {
        return plugin;
    }
    private static Plugin plugin;
    public void onEnable(){
        plugin = this;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        pm.registerEvents(new Afk(), this);

        pm.registerEvents(new Broadcast(), this);

        pm.registerEvents(new Echest(), this);

        pm.registerEvents(new InventoryInspector(), this);

        pm.registerEvents(new Poke(), this);

        pm.registerEvents(this, this);

        getCommand("afk").setExecutor(new Afk() );

        getCommand("broadcast").setExecutor(new Broadcast() );

        getCommand("enderchest").setExecutor(new Echest() );

        getCommand("enchanttable").setExecutor(new EnchantTable() );

        getCommand("gravityessentials").setExecutor(new GravityEssentials() );

        getCommand("viewinv").setExecutor(new InventoryInspector() );

        getCommand("poke").setExecutor(new Poke() );

        getCommand("shout").setExecutor(new Shout() );

        getCommand("craft").setExecutor(new Workbench() );


        getConfig().options().copyDefaults(true);
        saveDefaultConfig();


    }
    public void onDisable(){
        plugin = null;
    }

}
