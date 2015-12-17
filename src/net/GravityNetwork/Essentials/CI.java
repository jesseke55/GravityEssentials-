package net.GravityNetwork.Essentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Jesse on 17-12-2015.
 */
public class CI implements Listener, CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ci")) {
            if ((sender instanceof Player)) {
                if (sender.hasPermission("minechill.ci.self")) {
                    Player player = (Player) sender;
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "SUCCESS" + ChatColor.GRAY + ":" + ChatColor.WHITE + " Your inventory has been cleared!");
                        player.getInventory().clear();
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ":" + ChatColor.WHITE + " You do not have permission to use this command!");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You're console, you don't have an inventory!");
            }
        }
        return true;
    }
}
