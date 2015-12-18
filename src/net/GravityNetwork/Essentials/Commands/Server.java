package net.GravityNetwork.Essentials.Commands;

import net.GravityNetwork.Essentials.Main;
import net.GravityNetwork.Essentials.Menus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Jesse on 18-12-2015.
 */
public class Server implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("server")){
            if(sender instanceof Player){
                sender.sendMessage("");
                return true;
            }
            Player p = (Player)sender;
            p.openInventory(new Menus().server());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix") + Main.getPlugin().getConfig().getString("open.servergui")));
        }


        return true;

    }
}
