package net.GravityNetwork.Essentials.Commands;

import net.GravityNetwork.Essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Broadcast implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("broadcast"))
        {
            if (((sender instanceof Player)) || (!(sender instanceof Player))) {
                if (sender.hasPermission("gravityessentials.bc"))
                {
                    if (args.length == 0)
                    {
                        sender.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": Please specify a message you wish to broadcast to the server!");
                        return true;
                    }
                    String msg = "";
                    String[] arrayOfString;
                    int j = (arrayOfString = args).length;
                    for (int i = 0; i < j; i++)
                    {
                        String word = arrayOfString[i];
                        msg = msg + " " + word;
                    }
                    msg = msg.substring(1);
                    for (Player server : Bukkit.getOnlinePlayers())
                    {
                        server.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("BROADCAST_PREFIX")) + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', msg));
                        server.playSound(server.getLocation(), Sound.NOTE_PLING, 10.0F, 1.0F);
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.DARK_RED + ""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": You do not have permission to use this command!");
                }
            }
            return true;
        }
        return false;
    }
}
