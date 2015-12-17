package net.GravityNetwork.Essentials;

import net.GravityNetwork.Essentials.Commands.Afk;
import net.GravityNetwork.Essentials.Commands.CI;
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
        pm.registerEvents(this,this);
    }
    public void onDisable(){
        plugin = null;
    }

    private void cmds(){
        getCommand("afk").setExecutor(new Afk() );
        getCommand("ci").setExecutor(new CI() );
    }

    private void CONFIGURATION(){
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
