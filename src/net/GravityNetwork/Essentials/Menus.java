package net.GravityNetwork.Essentials;

import net.GravityNetwork.Essentials.Utils.ItemBuilder;
import net.GravityNetwork.Essentials.Utils.MenuBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by Jesse on 18-12-2015.
 */
public class Menus  {

    public Inventory server(){

        MenuBuilder menu = new MenuBuilder(9, ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.Name")));

        menu.addItem(0, new ItemBuilder(Material.COMPASS)
                .name(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.Hub.Name")))
                .lore(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.servers.lore"))).getStack());



        menu.addItem(1, new ItemBuilder(Material.DIAMOND_PICKAXE)
                .name(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPFactions.Name")))
                .lore(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.servers.lore"))).getStack());


        menu.addItem(2, new ItemBuilder(Material.DIAMOND_SWORD)
                .name(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPPrison.Name")))
                .lore(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.servers.lore"))).getStack());


        menu.addItem(3, new ItemBuilder(Material.GRASS)
                .name(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.Creative.Name")))
                .lore("Menus.ServerSelector.servers.lore").getStack());




        

        return menu.getInventory();
    }
}
