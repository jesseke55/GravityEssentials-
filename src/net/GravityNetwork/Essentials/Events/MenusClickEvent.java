package net.GravityNetwork.Essentials.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.GravityNetwork.Essentials.Main;
import net.GravityNetwork.Essentials.Menus;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Jesse on 18-12-2015.
 */
public class MenusClickEvent implements Listener {

    public void onClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();




        if (e.getInventory().getName().equals(new Menus().server().getName())) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPFactions.Name")))) send(p, Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPFactions.Server"));
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPPrison.Name")))) send(p, Main.getPlugin().getConfig().getString("Menus.ServerSelector.OPPrison.Server"));
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Menus.ServerSelector.Creative.Name")))) send(p, Main.getPlugin().getConfig().getString("Menus.ServerSelector.Creative.Server"));
        }
    }

    private void send(Player p, String server) {
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
    }
}
