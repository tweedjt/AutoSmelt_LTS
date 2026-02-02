package me.tweedjt.autosmelt.bukkitlisteners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.util.AutoSmeltConfig;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Message;

public class ReloadCommandListener implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            if (sender != null) {
                // Check if a player or console ran the command
                if (sender instanceof Player) {
                    // Log.logToConsole("Sender is a player"); // Comment this out for release
                    // A player called it
                    Player player = (Player) sender;
                    // Check if the player has permission
                    if (!player.hasPermission("autosmelt.reload")) {
                        Message.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }else{
                        AutoSmelt plugin = AutoSmelt.getInstance();
                        plugin.reloadConfig();
                        plugin.setAutoSmeltConfig(new AutoSmeltConfig(plugin));
                        Message.toPlayer("AutoSmelt reloaded.", player);
                        Log.logToConsole("AutoSmelt Reloaded");
                    }
                    return true;

                }else {
                    Log.logToConsole("Sender is null");
                    return false;

                }
            }
            return true;
        }
        catch (Exception e) {
            Log.logToConsole(e.getMessage());
            //Log.logToConsole("Catch Exception");
            return false;
        }

    }
}
