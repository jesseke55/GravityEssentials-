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

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){

    if (cmd.getName().equalsIgnoreCase("enchanttable")) {
        if ((sender instanceof Player))
        {
            if (sender.hasPermission("gravityessentials.enchanttable"))
            {
                if (args.length >= 0)
                {
                    Player player = (Player)sender;
                    player.openEnchanting(null, true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix") + Main.getPlugin().getConfig().getString("open.enchanttable")));
                    return true;

                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("NO_PERMISSION")));
            }
        }
        else {
            sender.sendMessage(Main.getPlugin().getConfig().getString("YouAreConsole"));
        }
    }
        return true;
    }
}
