package net.GravityNetwork.Essentials.Utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

/**
 * Created by Jesse on 18-12-2015.
 */
public class ItemBuilder {



    private final ItemStack stack;
    private final ItemMeta meta;

    public ItemBuilder(final Material material) {
        stack = new ItemStack(material);
        meta = stack.getItemMeta();
    }
    public ItemBuilder amount(final Integer amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemBuilder name(final String name) {
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(final String... lore) {
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder enchant(final Enchantment enchant, final Integer level) {
        final ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(enchant, level, true);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder color(final Color color) {
        if (stack.equals(Material.LEATHER_BOOTS) || stack.equals(Material.LEATHER_LEGGINGS) || stack.equals(Material.LEATHER_CHESTPLATE) ||
                stack.equals(Material.LEATHER_HELMET)) {
            final LeatherArmorMeta LAmeta = (LeatherArmorMeta) meta;
            LAmeta.setColor(color);
            stack.setItemMeta(LAmeta);
        }
        else {
            throw new IllegalArgumentException("setColor can only be used on leather armour!");
        }

        return this;
    }

    public ItemBuilder durability(final int durability) {
        if (durability >= Short.MIN_VALUE && durability <= Short.MAX_VALUE) {
            stack.setDurability((short)durability);
        }
        else {
            throw new IllegalArgumentException("The durability must be a short!");
        }
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder data(final int data) {
        stack.setData(new MaterialData(data));
        return this;
    }

    public ItemBuilder flag(final ItemFlag flag) {
        meta.addItemFlags(flag);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder data(MaterialData data) {
        stack.setData(data);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder owner(ItemStack item, String name) {
        if(item.getType() == Material.SKULL || item.getType() == Material.SKULL_ITEM) {
            SkullMeta SMeta = (SkullMeta) meta;
            SMeta.setOwner(name);
            item.setItemMeta(SMeta);
        }

        return this;
    }

    public final ItemStack getStack() {
        return stack;
    }
}


