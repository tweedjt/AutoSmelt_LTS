package me.tweedjt.autosmelt.bukkitlisteners;

import me.tweedjt.autosmelt.data.SmeltData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.util.Log;


public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        SmeltData smeltData = AutoSmelt.getInstance().getSmeltData();
        // This event is called when a player joins the server

        // Check if the player has auto-smelt auto-on
        if (AutoSmelt.getInstance().getAutoSmeltConfig().getAutoSmelt()) {
            // AutoSmelt is on, check if the player is already in the list, if not, add them
            if (!smeltData.hasSmelt(event.getPlayer().getUniqueId())) {
                smeltData.putSmelt(event.getPlayer().getUniqueId());
            }
        } else {
            // Check if the player has auto-smelt on
            if (smeltData.hasSmelt(event.getPlayer().getUniqueId())) {
                // Player already has auto-smelt (connect/disconnect?) - turn off
                smeltData.removeSmelt(event.getPlayer().getUniqueId());
            }
        }
    }
}