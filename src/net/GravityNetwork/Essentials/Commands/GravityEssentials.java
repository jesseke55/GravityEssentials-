package net.GravityNetwork.Essentials.Commands;

import net.GravityNetwork.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * Created by Jesse on 18-12-2015.
 */
public class GravityEssentials implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("gravityessentials")){
           if(sender.hasPermission("gravityessentials.main")){
                if(args.length == 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("ENTER_ARGUMENT")));
                    return true;
                } else if (args.length == 1){
                    if(args[0].equalsIgnoreCase("reload")){
                        if(args.length == 2) {
                            if(args[1].equalsIgnoreCase("config")) {
                                PluginUtil.reload(PluginUtil.ReloadType.CONFIG);
                                sender.sendMessage(ChatColor.GREEN + "Configuration is reloaded.");
                                return true;
                            } else if(args[1].equalsIgnoreCase("plugin")) {
                                PluginUtil.reload(PluginUtil.ReloadType.PLUGIN);
                                sender.sendMessage(ChatColor.GREEN + "Plugin is reloaded.");
                                return true;
                            } else if(args[1].equalsIgnoreCase("all")) {
                                PluginUtil.reload(PluginUtil.ReloadType.ALL);
                                sender.sendMessage(ChatColor.GREEN + "Plugin & Configuration is reloaded.");
                                return true;
                            }
                        } else {
                            PluginUtil.reload(PluginUtil.ReloadType.ALL);
                            sender.sendMessage(ChatColor.GREEN + "Plugin & Configuration is reloaded.");
                            return true;
                        }
                    } else  if(args[0].equalsIgnoreCase("nospamon")){
                        Main.getPlugin().getConfig().set("slowModeEnabled", "true");
                        Main.getPlugin().saveConfig();
                        sender.sendMessage("The anti-spam mode has been enabled!");
                        return true;
                    } else  if(args[0].equalsIgnoreCase("nospamoff")){
                        Main.getPlugin().getConfig().set("slowModeEnabled", "false");
                        Main.getPlugin().saveConfig();
                        sender.sendMessage("The anti spam-mode has been disabled!");
                        return true;
                    }
                }
            }
        }



        return true;
    }
}
