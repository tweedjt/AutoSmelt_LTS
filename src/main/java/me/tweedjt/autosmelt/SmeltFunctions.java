package me.tweedjt.autosmelt;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Message;
import me.tweedjt.autosmelt.util.Misc;


public class SmeltFunctions {

    private AutoSmelt plugin; // This is to hold our existing instance

    /**
     * Constructor for the class, takes in the instance of the plugin
     * @param plugin Existing instance of AutoSmelt
     */
    public SmeltFunctions(AutoSmelt plugin) {
        this.plugin = plugin; // Set the instance in this class = existing instance we've passed to it
    }

    //NOTE: Removed "static" - we'll be calling this from an instantiated copy
    public boolean isSmeltingPick(ItemStack itemStack) {
        // Get the real pick
        ItemStack realPick = getSmeltingPick();

        // Cloning the provided pick as I'll be playing with the meta
        // and we don't want it accidentally carrying back over
        ItemStack clonedThisPick = itemStack.clone();

        // Get the item meta from the item we're checking
        ItemMeta itemMeta = clonedThisPick.getItemMeta();
        if (itemMeta == null) {
            return false;
        }

        // Create a new item based on what we're checking
        // we're doing it this way so we won't take into
        // account any damage or enchantments when comparing
        // instead we're just checking name and lore

        ItemStack thisPick = new ItemStack(clonedThisPick.getType(), 1);
        ItemMeta thisPickMeta = thisPick.getItemMeta();
        if (thisPickMeta != null) {
            thisPickMeta.setDisplayName(itemMeta.getDisplayName());
            thisPickMeta.setLore(itemMeta.getLore());
            thisPick.setItemMeta(thisPickMeta);
        }

        // Get the string blob for the real smelting pick
        String realSmeltingPickBlob = Misc.itemToStringBlob(realPick);

        // Get the string blob for the item we're checking
        String itemBlob = Misc.itemToStringBlob(thisPick);



        Log.debugToConsole("Real Pick: " + realSmeltingPickBlob);
        Log.debugToConsole("This Pick: " + itemBlob);
        // Compare them
        if (realSmeltingPickBlob.equals(itemBlob)) {
            return true;
        } else {
            return false;
        }
    }

    //NOTE: Removed "static" so we can use "plugin"
    public ItemStack getSmeltingPick() {
        // Create a new ItemStack for the smelting pickaxe
        ItemStack itemStack = new ItemStack(plugin.getSmeltData().getSmeltingPickAxeMaterial(), 1);
        // Get the item meta
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            // Set the display name
            itemMeta.setDisplayName(plugin.getSmeltData().getSmeltingPickaxeName());
            // Set the lore
            itemMeta.setLore(plugin.getSmeltData().getSmeltingPickAxeLore());
            // Set the updated ItemMeta back to the ItemStack
            itemStack.setItemMeta(itemMeta);
        }

        // return the item
        return itemStack;
    }

    //NOTE: Removed "static" so we can use "plugin"
    public boolean giveSmeltingPickToPlayer(Player player) {
        if (player != null) {
            int itemSlot = Misc.firstEmptySlot(player.getUniqueId());
            if (itemSlot >= 0) {
                Message.toPlayer("&aEnjoy your new smelting pick!", player);
                player.getInventory().setItem(itemSlot, getSmeltingPick());
                return true;
            } else {
                Log.logToConsole("No free inventory slot found");
                return false;
            }
        } else {
            Log.logToConsole("Player is null, unable to give Smelting pickaxe");
            return false;
        }
    }
}
