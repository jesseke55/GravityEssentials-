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
public class Workbench implements Listener, CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){


        if (cmd.getName().equalsIgnoreCase("craft"))
        {
            if ((sender instanceof Player))
            {
                if (sender.hasPermission("gravityessentials.craft"))
                {
                    if (args.length >= 0)
                    {
                        Player player = (Player)sender;
                        player.openWorkbench(null, true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix") + Main.getPlugin().getConfig().getString("open.workbench")));
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("NO_PERMISSION")));
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("YouAreConsole")));
            }
        }

        return true;
    }
}
