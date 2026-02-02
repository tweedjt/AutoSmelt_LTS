package me.tweedjt.autosmelt.commands;

import me.tweedjt.autosmelt.data.SmeltData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Message;

public class ASCommandListener implements CommandExecutor {

    // This class handles the /as command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        SmeltData smeltData = AutoSmelt.getInstance().getSmeltData();

        //Log.logToConsole("/as command run"); // Comment this out for release

        // Put all the code in a try/catch block to avoid console errors
        try {
            if (sender != null) {
                // Check if a player or console ran the command
                if (sender instanceof Player) {
                    //Log.logToConsole("Sender is a player"); // Comment this out for release
                    // A player called it
                    Player player = (Player) sender;

                    // Check if the player has permission
                    if (!player.hasPermission("autosmelt.mine")) {
                        Message.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }
                    // Check if the player has auto-smelt turned on
                    if (smeltData.hasSmelt(player.getUniqueId())) {
                        //Log.logToConsole("Player has smelt on - turn it off"); // Comment this out for release
                        // Player already has smelt on - turn it off
                        smeltData.removeSmelt(player.getUniqueId());
                        Message.toPlayer(AutoSmelt.getInstance().getAutoSmeltConfig().getAutoSmeltOffMessage(), player);
                    } else {
                        //Log.logToConsole("Player has smelt off - turn it on"); // Comment this out for release
                        // Player has smelt off - turn it on
                        smeltData.putSmelt(player.getUniqueId());
                        Message.toPlayer(AutoSmelt.getInstance().getAutoSmeltConfig().getAutoSmeltOnMessage(), player);
                    }
                } else {
                    // A non-player (console) called the command
                    //Log.logToConsole("Command was run by console"); // Comment this out for release
                    Message.toSender("This command cannot be called by console", sender);
                }
                // Return true
                return true;
            } else {
                Log.logToConsole("Sender is null");
                return false;
            }
        } catch (Exception ex) {
            // Output the error and return false
            Log.logToConsole(ex.getMessage());
            return false;
        }
    }
}