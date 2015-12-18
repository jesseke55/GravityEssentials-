package net.GravityNetwork.Essentials.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.GravityNetwork.Essentials.Main;
import net.GravityNetwork.Essentials.Menus;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
                sender.sendMessage("You are not a player!");
                return true;
            }
            if(args.length ==0) {
                Player p = (Player) sender;
                p.openInventory(new Menus().server());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix") + Main.getPlugin().getConfig().getString("open.servergui")));
            }
            else if(args.length==1){
              if(args[0].equalsIgnoreCase("hub")){
                Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("hub");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
               else if(args[0].equalsIgnoreCase("factions")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("opfactions");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
               else if(args[0].equalsIgnoreCase("prison")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("prison");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
              else if(args[0].equalsIgnoreCase("creative")){
                  Player p =(Player) sender;
                  p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                  ByteArrayDataOutput out = ByteStreams.newDataOutput();
                  out.writeUTF("Connect");
                  out.writeUTF("opprison");
                  p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
              }
            }
        }


        return true;

    }
}
