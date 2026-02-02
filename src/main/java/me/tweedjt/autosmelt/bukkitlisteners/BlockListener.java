package me.tweedjt.autosmelt.bukkitlisteners;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.SmeltFunctions;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Misc;

@SuppressWarnings("ALL")
public class BlockListener implements Listener {

    private AutoSmelt plugin;

    @EventHandler
    public void onInteract(BlockBreakEvent event) {

        // Create a variable to hold our instance
        this.plugin = AutoSmelt.getInstance();
        // Create a local copy of SmeltFunctions with the instance passed to it
        SmeltFunctions smeltFunctions = new SmeltFunctions(plugin);

        Player player = event.getPlayer();

        if (event.isCancelled()) {
            // Check if some other plugin cancelled the event, if so, do nothing
            return;
        }

        Block block = event.getBlock();
        Material brokenBlock = block.getType();

        // Check if this block is a smeltable ore (hardcoded list)
        if (!plugin.getAutoSmeltConfig().isSmeltableOre(brokenBlock)) {
            return;
        }

        // Get the smelted result for this ore
        Material drop = plugin.getAutoSmeltConfig().getSmeltedResult(brokenBlock);
        if (drop == null) {
            return;
        }

        int dropAmount = 1;
        ItemStack hand = player.getInventory().getItemInMainHand();
        boolean allowAutoSmelt = false;
        Random rand = new Random();

        // Random drop amount settings from config
        boolean randDrops = plugin.getAutoSmeltConfig().getDropAmount();
        int dropMaxAmount = plugin.getAutoSmeltConfig().getMaxDropAmount();
        int dropMinAmount = plugin.getAutoSmeltConfig().getMinDropAmount();

        if (randDrops) {
            dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount + 1) + dropMinAmount;
        }

        if (Misc.worldGuardPreventBreakAtLocation(block, player)) {
            // If WorldGuard is blocking the breaking here, exit out
            return;
        }

        // Check if the player is holding a pickaxe
        boolean hasPickaxe = false;
        switch (hand.getType()) {
            case DIAMOND_PICKAXE:
            case GOLDEN_PICKAXE:
            case IRON_PICKAXE:
            case STONE_PICKAXE:
            case WOODEN_PICKAXE:
            case NETHERITE_PICKAXE:
                hasPickaxe = true;
                break;
            default:
                break;
        }

        if (smeltFunctions.isSmeltingPick(hand)) {
            // We have a smelting pick, we don't need permissions
            allowAutoSmelt = true;
        } else {
            // Check if we have permission and a pickaxe in hand
            if (player.hasPermission("autosmelt.mine") && hasPickaxe) {
                if (plugin.getSmeltData().hasSmelt(player.getUniqueId())) {
                    // We have pick and permission, and autosmelting is turned on for this player
                    allowAutoSmelt = true;
                }
            }
        }

        boolean autoSmeltDefault = plugin.getAutoSmeltConfig().getAutoSmelt();
        // Check the players gamemode, if they aren't in survival, we will deny auto-smelting
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            // Player is not in Survival mode
            Log.debugToConsole("Player is not in survival mode");
            allowAutoSmelt = false;
            block.setType(Material.AIR);
        }

        if (autoSmeltDefault) {
            if (player.hasPermission("autosmelt.mine")) {
                allowAutoSmelt = true;
            }
        }

        if (allowAutoSmelt) {

            // Check if the pickaxe has fortune
            if (plugin.getAutoSmeltConfig().getFortuneDrops()) {
                if (hand.containsEnchantment(Enchantment.FORTUNE)) {
                    // Ancient Debris should not benefit from fortune (vanilla behavior)
                    if (brokenBlock == Material.ANCIENT_DEBRIS) {
                        dropAmount = 1;
                    } else {
                        dropAmount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.FORTUNE) + 1) + 1;
                    }
                }
            }

            if (hand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                // Silk touch: drop the original block instead of the smelted item
                drop = brokenBlock;
                dropAmount = 1;
            }

            // Clear the block
            block.setType(Material.AIR);

            // Damage the pickaxe manually as we're cancelling the events
            ItemMeta handMeta = hand.getItemMeta();
            if (handMeta != null && plugin.getAutoSmeltConfig().getPickDamage()) {
                if (handMeta instanceof Damageable) {
                    Damageable d = (Damageable) handMeta;
                    d.setDamage(d.getDamage() + plugin.getSmeltData().getSmeltingDamage());
                    hand.setItemMeta(handMeta);
                }
            }

            boolean dropItem = false;
            Location loc = block.getLocation();
            World w = block.getWorld();

            // Check if auto-pickup is on
            if (plugin.getAutoSmeltConfig().getAutoPickup()) {
                if (player.getInventory().firstEmpty() == -1) {
                    // No empty slots - drop the item
                    dropItem = true;
                } else {
                    // Empty slot found, place item in inventory
                    player.getInventory().addItem(new ItemStack(drop, dropAmount));
                    Misc.xpDrops(drop, w, loc);
                }
            } else {
                // Auto-pickup is off, so drop the item
                dropItem = true;
            }

            if (dropItem) {
                // Drop the item
                block.getWorld().dropItemNaturally(block.getLocation().add(0.2D, 0.2D, 0.2D), new ItemStack(drop, dropAmount));
                Misc.xpDrops(drop, w, loc);
            }

            event.setCancelled(true);
        }
    }
}
