package me.tweedjt.autosmelt.data;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Misc;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SmeltData {


    private AutoSmelt plugin; // This is to hold our existing instance

    /**
     * Constructor for the class, takes in the instance of the plugin
     * @param plugin Existing instance of AutoSmelt
     */
    public SmeltData(AutoSmelt plugin) {
        this.plugin = plugin; // Set the instance in this class = existing instance we've passed to it
    }

    // Debug - set to true for debugging
    private final boolean debug = false;
    public boolean getDebug() {
        return debug;
    }

    // Create a HashSet of player unique id values, if the player is in this list, auto-smelt is on
    // NOTE: On the left side of declaring something, use the superclass (so Set, not HashSet.  Map, not HashMap
    // It is the Liskov Substitution Principle if you wanted to google it.  Also, you don't need to use
    // UUID on the right side, it will be inferred.
    //private HashSet<UUID> smelters = new HashSet<UUID>();
    private Set<UUID> smelters = new HashSet<>();



    // This is the name of the smelting pick
    private final String smeltingPickName = "Smelting Pick";
    public String getSmeltingPickaxeName() {
        return smeltingPickName;
    }

    // This is the lore for the smelting pick - note that it uses SmeltFunctions.stringToLore,
    // this allows using | as a line break.  Also, note the use of &d - this is a color code
    // that we will convert to a color.  This is done through SmeltFunctions.colorToString,
    // but we don't need to call that here as SmeltFunctions.stringToLore calls it.
    private final List<String> smeltingLore = Misc.stringToLore("&dAutomatically smelts ores!|Fortune will increase yield") ;
    public List<String> getSmeltingPickAxeLore() {
        return smeltingLore;
    }
    // This is the smelting pick material
    private final Material smeltingPickAxeMaterial = Material.NETHERITE_PICKAXE;
    public Material getSmeltingPickAxeMaterial() {
        return smeltingPickAxeMaterial;
    }
    // This is the amount of damage to cause to the pick each use
    private final int smeltingDamage = 1;
    public int getSmeltingDamage() {
        return smeltingDamage;
    }

    // Create a check to see iff they have auto-smelt, and getters and setters
    public boolean hasSmelt(UUID uuid) {
        if (smelters == null) {
            // If for some reason smelters is null, set it as a blank HashSet
            smelters = new HashSet<UUID>();
        }
        return smelters.contains(uuid);
    }
    public void putSmelt(UUID uuid) {
        if (smelters == null) {
            // If for some reason smelters is null, set it as a blank HashSet
            smelters = new HashSet<UUID>();
        }
        smelters.add(uuid);
    }
    public void removeSmelt(UUID uuid) {
        if (smelters == null) {
            // If for some reason smelters is null, set it as a blank HashSet
            smelters = new HashSet<UUID>();
        }
        smelters.remove(uuid);
    }
}
