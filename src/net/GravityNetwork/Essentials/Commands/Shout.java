package net.GravityNetwork.Essentials.Commands;


import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Shout
        implements Listener,
        CommandExecutor {
    public HashMap<String, Long> shoutCool = new HashMap();

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("gravityessentials.shout")) {
            sender.sendMessage((Object)ChatColor.DARK_RED + ""+(Object)ChatColor.BOLD + "FAULT" + (Object)ChatColor.GRAY + ": You do not have permission to use this command!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("shout")) {
            if (args.length == 0) {
                sender.sendMessage((Object)ChatColor.DARK_RED + ""+(Object)ChatColor.BOLD + "FAULT" + (Object)ChatColor.GRAY + ": Please specify a message you wish to shout to the server!");
                return true;
            }
            if (this.shoutCool.containsKey(sender.getName())) {
                long kitTime = this.shoutCool.get(sender.getName());
                if (this.shoutCool.get(sender.getName()) > System.currentTimeMillis()) {
                    sender.sendMessage((Object)ChatColor.DARK_RED +""+ (Object)ChatColor.BOLD + "FAULT" + (Object)ChatColor.GRAY + ": You can not shout a message this often. Please wait another " + (kitTime - System.currentTimeMillis()) / 1000 + " seconds");
                    return true;
                }
                Player p22 = (Player)sender;
                String message2 = "";
                String[] arrstring = args;
                int n = arrstring.length;
                int n2 = 0;
                while (n2 < n) {
                    String word = arrstring[n2];
                    message2 = String.valueOf(message2) + " " + word;
                    ++n2;
                }
                message2 = message2.substring(1);
                for (Player server : Bukkit.getOnlinePlayers()) {
                    server.sendMessage((Object)ChatColor.RED + ""+ (Object)ChatColor.BOLD + "[" + (Object)ChatColor.GOLD + (Object)ChatColor.BOLD + "SHOUT" + (Object)ChatColor.RED + (Object)ChatColor.BOLD + "]" + (Object)ChatColor.RESET + " " + (Object)ChatColor.DARK_PURPLE + sender.getName() + (Object)ChatColor.GRAY + ": " + (Object)ChatColor.LIGHT_PURPLE + message2);
                    server.playSound(server.getLocation(), Sound.NOTE_PLING, 10.0f, 1.0f);
                    this.addCooldown(p22, 15);
                }
                return true;
            }
            String message = "";
            String[] server = args;
            int message2 = server.length;
            int p22 = 0;
            while (p22 < message2) {
                String word = server[p22];
                message = String.valueOf(message) + " " + word;
                ++p22;
            }
            message = message.substring(1);
            Iterator p222 = Bukkit.getOnlinePlayers().iterator();
            if (p222.hasNext()) {
                Player server2 = (Player)p222.next();
                server2.sendMessage((Object)ChatColor.RED + ""+ (Object)ChatColor.BOLD + "[" + (Object)ChatColor.GOLD + (Object)ChatColor.BOLD + "SHOUT" + (Object)ChatColor.RED + (Object)ChatColor.BOLD + "]" + (Object)ChatColor.RESET + " " + (Object)ChatColor.DARK_PURPLE + sender.getName() + (Object)ChatColor.GRAY + ": " + (Object)ChatColor.LIGHT_PURPLE + message);
                server2.playSound(server2.getLocation(), Sound.NOTE_PLING, 10.0f, 1.0f);
                Player p = (Player)sender;
                this.addCooldown(p, 15);
                return true;
            }
        }
        return false;
    }

    public void addCooldown(Player p, long secs) {
        long currentMillis1 = System.currentTimeMillis();
        long coolTime1 = currentMillis1 + (secs + 1) * 1000;
        this.shoutCool.put(p.getName(), coolTime1);
    }
}