package net.GravityNetwork.Essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * Created by Jesse on 17-12-2015.
 */
public class Poke implements Listener, CommandExecutor {

    public HashMap<String, Long> pokeCool = new HashMap();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!sender.hasPermission("minechill.poke"))
        {
            sender.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": You do not have permission to use this command!");
        }
        else
        {
            Player player = (Player)sender;
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("poke"))
            {
                if (args.length == 0)
                {
                    player.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": Please use the correct format! /poke <player>");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null)
                {
                    sender.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": That player could not be found!");
                    return true;
                }
                if (this.pokeCool.containsKey(sender.getName()))
                {
                    long kitTime = ((Long)this.pokeCool.get(sender.getName())).longValue();
                    if (((Long)this.pokeCool.get(sender.getName())).longValue() > System.currentTimeMillis())
                    {
                        sender.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": You can not poke someone this frequently, Please wait another " + (kitTime - System.currentTimeMillis()) / 1000L + " seconds");
                        System.currentTimeMillis();
                    }
                    else
                    {
                        player.sendMessage(ChatColor.GOLD +""+  ChatColor.BOLD + "POKE" + ChatColor.GRAY + ": " + target.getName() + " has been poked!");
                        target.sendMessage(ChatColor.GOLD + ""+ChatColor.BOLD + "POKE" + ChatColor.GRAY + ": " + sender.getName() + " has poked you!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 6.0F);
                        target.playSound(player.getLocation(), Sound.HURT_FLESH, 10.0F, 0.0F);
                        addCooldown(p, 15L);
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.GOLD + ""+ ChatColor.BOLD + "POKE" + ChatColor.GRAY + ": " + target.getName() + " has been poked!");
                    target.sendMessage(ChatColor.GOLD + ""+ChatColor.BOLD + "POKE" + ChatColor.GRAY + ": " + sender.getName() + " has poked you!");
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 6.0F);
                    player.playSound(player.getLocation(), Sound.HURT_FLESH, 10.0F, 0.0F);
                    addCooldown(p, 15L);
                    return true;
                }
            }
        }
        return false;
    }

    public void addCooldown(Player p, long secs)
    {
        long currentMillis1 = System.currentTimeMillis();
        long coolTime1 = currentMillis1 + (secs + 1L) * 1000L;

        this.pokeCool.put(p.getName(), Long.valueOf(coolTime1));
    }
}


