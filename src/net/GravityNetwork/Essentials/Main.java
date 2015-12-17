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
        cmds();
        CONFIGURATION();
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        pm.registerEvents(new Afk(), this);

        pm.registerEvents(new Broadcast(), this);

        pm.registerEvents(new InventoryInspector(), this);

        pm.registerEvents(new Poke(), this);

        pm.registerEvents(this, this);

        pm.registerEvents(new MSGTag(), this);



    }
    public void onDisable(){
        plugin = null;
    }

    private void cmds(){
        getCommand("afk").setExecutor(new Afk() );
        getCommand("broadcast").setExecutor(new Broadcast() );
        getCommand("poke").setExecutor(new Poke() );
    }

    private void CONFIGURATION(){
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
