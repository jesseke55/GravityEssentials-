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
public class EnchantTable implements Listener, CommandExecutor {

    public Main main;

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){

    if (cmd.getName().equalsIgnoreCase("enchant")) {
        if ((sender instanceof Player))
        {
            if (sender.hasPermission("minechill.enchant"))
            {
                if (args.length >= 0)
                {
                    Player player = (Player)sender;
                    player.openEnchanting(null, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("SUCCESS") + main.getConfig().getString("open.enchanttable")));
                    return true;

                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NO_PERMISSION")));
            }
        }
        else {
            sender.sendMessage(main.getConfig().getString("YouAreConsole"));
        }
    }
        return true;
    }
}
