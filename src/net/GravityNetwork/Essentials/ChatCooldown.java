package net.GravityNetwork.Essentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by Jesse on 18-12-2015.
 */
public class ChatCooldown
        implements Listener {
    DecimalFormat form = new DecimalFormat("#.##");
    public static HashMap<String, Long> chatCool = new HashMap();

    public boolean isInChatCool(Player p) {
        if (Main.getPlugin().getConfig().getBoolean("slowModeEnabled")) {
            if (chatCool.containsKey(p.getName())) {
                double chatTime = chatCool.get(p.getName()).longValue();
                if (chatTime > (double)(System.currentTimeMillis() / 100)) {
                    chatTime = (chatTime - (double)(System.currentTimeMillis() / 100)) * 10.0 / 100.0;
                    p.sendMessage((Object) ChatColor.DARK_RED + "" +(Object)ChatColor.BOLD + "FAULT" + (Object)ChatColor.GRAY + ": You can not talk yet. You will be able to talk in " + this.form.format(chatTime) + " seconds!");
                } else {
                    this.addCooldown(p, 2);
                }
                return true;
            }
            this.addCooldown(p, 2);
            return false;
        }
        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (Main.getPlugin().getConfig().getBoolean("slowModeEnabled")) {
            if (chatCool.containsKey(p.getName())) {
                double chatTime = chatCool.get(p.getName()).longValue();
                if (chatTime > (double)(System.currentTimeMillis() / 100)) {
                    event.setCancelled(true);
                    DecimalFormat form = new DecimalFormat("#.##");
                    chatTime = (chatTime - (double)(System.currentTimeMillis() / 100)) * 10.0 / 100.0;
                    p.sendMessage((Object)ChatColor.DARK_RED + "" + (Object)ChatColor.BOLD + "FAULT" + (Object)ChatColor.GRAY + ": You can not talk yet. You will be able to talk in " + form.format(chatTime) + " seconds!");
                } else {
                    this.addCooldown(p, 2);
                }
            } else {
                this.addCooldown(p, 2);
            }
        }
    }

    public void addCooldown(Player p, long secs) {
        chatCool.put(p.getName(), secs * 10 + System.currentTimeMillis() / 100);
    }
}

