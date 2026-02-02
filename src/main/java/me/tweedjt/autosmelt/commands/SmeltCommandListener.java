package me.tweedjt.autosmelt.commands;

import me.tweedjt.autosmelt.AutoSmelt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.autosmelt.SmeltFunctions;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Message;

public class SmeltCommandListener  implements CommandExecutor {

    private AutoSmelt plugin;

    // This class handles the /as command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // Create a variable to hold our instance
        this.plugin = AutoSmelt.getInstance();
        // Create a local copy of SmeltFunctions with the instance passed to it
        // we'll use this copy (smeltFunctions) to call our functions, NOT SmeltFunctions
        SmeltFunctions smeltFunctions = new SmeltFunctions(plugin);

        int iargs = 0;
        if (args != null) {
            if (args.length > 0) {
                StringBuilder fullargs = new StringBuilder();
                for (Object o : args)
                {
                    iargs++;
                    fullargs.append(o.toString());
                    fullargs.append(" ");
                }
                Log.logToConsole("/smelt command run by " + sender.getName() + " with arguments: " + fullargs);

                if (iargs != 1) {
                    // Either we have no parameters passed, or too many
                    Message.toSender("Usage: /smelt <playername>", sender);
                    return true;
                }

                Player recipient = null; // This is the player that will receive the pickaxe

                // It is technically possible you could run this command from console with no players on
                // so check it for null, then loop through
                if (Bukkit.getOnlinePlayers() != null) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        // Check if the display name matches
                        if (ChatColor.stripColor(p.getDisplayName()).equalsIgnoreCase(args[0].toString())) {
                            // The player exists, try and give the smelting pick
                            recipient = p; // Set the recipient
                            break;
                        }
                        // Also check if the player name matches
                        if (ChatColor.stripColor(p.getName()).equalsIgnoreCase(args[0].toString())) {
                            // The player exists, try and give the smelting pick
                            recipient = p; // Set the recipient
                            break;
                        }
                    }
                    if (recipient != null) {
                        if (sender.hasPermission("autosmelt.give")) {
                            if (smeltFunctions.giveSmeltingPickToPlayer(recipient)) {
                                Message.toSender("&a" + recipient.getDisplayName() + " was given a Smelting Pickaxe", sender);
                                return true;
                            } else {
                                Message.toSender("&cUnable to give " + recipient.getDisplayName() + " a Smelting Pickaxe.  Make sure they have a free inventory slot", sender);
                                return true;
                            }
                        } else {
                            Message.toSender("&cSorry, you do not have permission to run this command", sender);
                            return true;
                        }
                    } else {
                        Message.toSender("&cInvalid player.  Player must be online.  Usage: /smelt <playername>", sender);
                        return true;
                    }
                } else {
                    Message.toSender("&cNo players online.  Unable to give smelting pickaxe", sender);
                    return true;
                }


            } else {
                // Either we have no parameters passed, or too many
                Message.toSender("Usage: /smelt <playername>", sender);
                return true;
            }
        }
        Message.toSender("Usage: /smelt <playername>", sender);
        return true;
    }

}
