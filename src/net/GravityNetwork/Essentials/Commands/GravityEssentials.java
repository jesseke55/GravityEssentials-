package net.GravityNetwork.Essentials.Commands;

import net.GravityNetwork.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Jesse on 18-12-2015.
 */
public class GravityEssentials implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){

        if(sender.hasPermission("gravityessentials.main")) {
            if (cmd.getName().equalsIgnoreCase("gravityessentials")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("ENTER_ARGUMENT")));
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "plugman reload GravityEssentials");
                        sender.sendMessage("The plugin has been reloaded!");
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("stop")){
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.performCommand("server hub");
                        }
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                               Bukkit.shutdown();
                            }
                        }, 60L, 60L);
                    }
                }
            }
            }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("NO_PERMISSION")));
            }






        return true;
    }
}
