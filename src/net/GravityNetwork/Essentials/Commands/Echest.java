package net.GravityNetwork.Essentials.Commands;

import net.GravityNetwork.Essentials.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Echest implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){


        if(cmd.getName().equalsIgnoreCase("enderchest")){
            if (sender.hasPermission("gravityessentials.enderchest")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.openInventory(p.getEnderChest());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix")+Main.getPlugin().getConfig().getString("open.echest")));
                } else {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("NO_PERMISSION")));
                }
            }
        }


        return true;
    }
}
