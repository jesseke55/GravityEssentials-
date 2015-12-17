package net.GravityNetwork.Essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Afk implements CommandExecutor, Listener {



    private HashSet<String> afkPlayers = new HashSet();

    public boolean isAFK(Player p)
    {
        if (this.afkPlayers.contains(p.getName())) {
            return true;
        }
        return false;
    }

    public void setAFK(Player p)
    {
        this.afkPlayers.add(p.getName());
    }

    public void removeAFK(Player p)
    {
        if (this.afkPlayers.contains(p.getName())) {
            this.afkPlayers.remove(p.getName());
        }
    }

    public HashSet<String> getAFKPlayerNames()
    {
        return this.afkPlayers;
    }

    @EventHandler
    public void onAFKChat(AsyncPlayerChatEvent event)
    {
        Player chatPlayer = event.getPlayer();
        String msg = event.getMessage();
        for (String pName : getAFKPlayerNames()) {
            if ((msg.contains(pName)) && (!pName.equalsIgnoreCase(chatPlayer.getName())))
            {
                chatPlayer.sendMessage(ChatColor.YELLOW+ "" + ChatColor.BOLD + "WARNING" + ChatColor.GRAY + ": " + pName + " is currently AFK and might not respond to your message!");
                chatPlayer.playSound(chatPlayer.getLocation(), Sound.ZOMBIE_METAL, 10.0F, 7.0F);
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("afk"))
        {
            if ((sender instanceof Player))
            {
                Player p = (Player)sender;
                if (args.length == 0)
                {
                    if (isAFK(p))
                    {
                        removeAFK(p);
                        Bukkit.broadcastMessage(ChatColor.GOLD + ""+ ChatColor.BOLD + "AFK" + ChatColor.GRAY + ": " + p.getName() + " is no longer AFK!");
                        return true;
                    }
                    setAFK(p);
                    Bukkit.broadcastMessage(ChatColor.GOLD+ "" + ChatColor.BOLD + "AFK" + ChatColor.GRAY + ": " + p.getName() + " is now AFK!");
                    return true;
                }
                if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null)
                    {
                        if (isAFK(target)) {
                            p.sendMessage(ChatColor.YELLOW + ""+ ChatColor.BOLD + "WARNING" + ChatColor.GRAY + ": " + target.getName() + " is currently AFK!");
                        } else {
                            p.sendMessage(ChatColor.YELLOW + ""+ ChatColor.BOLD + "WARNING" + ChatColor.GRAY + ": " + target.getName() + " is not AFK!");
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lFAULT&r&7: " + args[0] + " isn't online!"));
                    }
                }
                else if ((args.length != 0) || (args.length != 1))
                {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lFAULT&7: &fPlease use the correct format."));
                }
            }
        }
        else
        {
            sender.sendMessage("You can not use this cmd from console!");
            return true;
        }
        return false;
    }
}

