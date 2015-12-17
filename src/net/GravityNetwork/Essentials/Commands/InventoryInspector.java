package net.GravityNetwork.Essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Jesse on 17-12-2015.
 */
public class InventoryInspector implements Listener, CommandExecutor {

    Inventory targetInv;
    Player target;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("viewinv"))
        {
            if ((sender instanceof Player)) {
                if (sender.hasPermission("gravityessentials.invinspect"))
                {
                    if (args.length == 0)
                    {
                        sender.sendMessage(ChatColor.DARK_RED +"" + ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": Please use the correct format! /viewinv <nickname>");
                    }
                    else if (args.length == 1)
                    {
                        this.target = Bukkit.getPlayer(args[0]);
                        if (this.target != null)
                        {
                            Player p = (Player)sender;
                            this.targetInv = Bukkit.createInventory(this.target, this.target.getInventory().getSize());
                            p.openInventory(this.target.getInventory());
                            return true;
                        }
                        sender.sendMessage(ChatColor.DARK_RED+"" + ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": " + ChatColor.WHITE + "That player can not be found.");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.DARK_RED +""+ ChatColor.BOLD + "FAULT" + ChatColor.GRAY + ": " + ChatColor.WHITE + "You do not have permission to use this command!");
                }
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e)
    {
        if ((e.getInventory().getName() == this.targetInv.getName()) &&
                (!e.getWhoClicked().hasPermission("viewinv.edit"))) {
            e.setCancelled(true);
        }
    }
}
