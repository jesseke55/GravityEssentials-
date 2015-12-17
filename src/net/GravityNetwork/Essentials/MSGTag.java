package net.GravityNetwork.Essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Jesse on 17-12-2015.
 */
public class MSGTag implements Listener {

    @EventHandler
    public void callOutPlayer(AsyncPlayerChatEvent e)
    {
        for (Player players : Bukkit.getServer().getOnlinePlayers())
        {
            String playerName = players.getName();
            if ((!e.getMessage().startsWith(playerName)) &&
                    (e.getMessage().contains(playerName)))
            {
                Player p = e.getPlayer();

                Player target = Bukkit.getPlayer(playerName);
                if (target != e.getPlayer())
                {
                    p.sendMessage("You tagged " + target.getName() );


                    target.playSound(target.getLocation(), Sound.NOTE_PLING, 10.0F, 1.0F);
                    String str1 = e.getMessage().replaceAll(playerName, ChatColor.AQUA + "@" + playerName);
                }
            }
        }
    }
}
