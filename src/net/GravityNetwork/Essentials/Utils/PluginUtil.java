package net.GravityNetwork.Essentials.Utils;

import net.GravityNetwork.Essentials.Main;
import org.bukkit.Bukkit;

public class PluginUtil {

  private PluginUtil() { }
  
  public static void reload(ReloadType type) {
      switch(type) {
        case PLUGIN:
          Bukkit.getServer().getPluginManager().disablePlugin(Main.getPlugin());
          Bukkit.getServer().getPluginManager().enablePlugin(Main.getPlugin());
          break;
        case CONFIG:
          Main.getPlugin().reloadConfig();
          break;
        case ALL:
          Main.getPlugin().reloadConfig();
          Bukkit.getServer().getPluginManager().disablePlugin(Main.getPlugin());
          Bukkit.getServer().getPluginManager().enablePlugin(Main.getPlugin());
          break;
        default:
          throw new IllegalArgumentException("Invalid reload type.");
      }
  }
  
  public static enum ReloadType {
  
      PLUGIN,
      CONFIG,
      ALL,
  }
}
