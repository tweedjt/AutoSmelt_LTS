package me.tweedjt.autosmelt.util;

import me.tweedjt.autosmelt.AutoSmelt;
import org.bukkit.Material;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AutoSmeltConfig {

    private final AutoSmelt plugin;
    private final YmlParser ymlParser;

    // Configuration values
    private boolean autoPickup = false;
    private boolean autoSmelt = false;
    private boolean expDrop = false;
    private boolean fortuneDrop = false;
    private boolean randomDropAmount = false;
    private boolean pickDamage = false;

    private int ironExp = 1;
    private int goldExp = 1;
    private int netherScrapExp = 2;
    private int maxDropAmount = 2;
    private int minDropAmount = 1;

    private String messagePrefix = "&d[&fAutoSmelt&d] &r";
    private String autoSmeltOffMessage = "AutoSmelt has been turned &cOFF";
    private String autoSmeltOnMessage = "AutoSmelt has been turned &aON";

    // Hardcoded smeltable ore mappings (ore -> smelted result)
    private final Map<Material, Material> smeltableOres = new HashMap<>();

    public AutoSmeltConfig(AutoSmelt plugin) {
        this.plugin = plugin;
        File configFile = Misc.getConfigYml(this.plugin);
        this.ymlParser = new YmlParser(plugin, configFile);
        initializeSmeltableOres();
        load();
    }

    /**
     * Initialize the hardcoded smeltable ore mappings.
     * Only includes ores that can actually be smelted in vanilla Minecraft.
     */
    private void initializeSmeltableOres() {
        // Iron ores
        smeltableOres.put(Material.IRON_ORE, Material.IRON_INGOT);
        smeltableOres.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT);

        // Gold ores
        smeltableOres.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        smeltableOres.put(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_INGOT);

        // Copper ores
        smeltableOres.put(Material.COPPER_ORE, Material.COPPER_INGOT);
        smeltableOres.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT);

        // Ancient Debris
        smeltableOres.put(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP);
    }

    public void load() {
        // Load boolean settings
        loadBooleanSetting("auto_smelt", false, val -> this.autoSmelt = val);
        loadBooleanSetting("pick_damage", false, val -> this.pickDamage = val);
        loadBooleanSetting("auto_pickup", false, val -> this.autoPickup = val);
        loadBooleanSetting("exp_drops", false, val -> this.expDrop = val);
        loadBooleanSetting("fortune_drops", false, val -> this.fortuneDrop = val);
        loadBooleanSetting("random_drop_amount", false, val -> this.randomDropAmount = val);

        // Load integer settings
        loadIntSetting("iron_exp", 1, val -> this.ironExp = val);
        loadIntSetting("gold_exp", 1, val -> this.goldExp = val);
        loadIntSetting("nether_scrap_exp", 2, val -> this.netherScrapExp = val);
        loadIntSetting("max_drop_amount", 2, val -> this.maxDropAmount = val);
        loadIntSetting("min_drop_amount", 1, val -> this.minDropAmount = val);

        // Load string settings
        loadStringSetting("message_prefix", "&d[&fAutoSmelt&d] &r", val -> this.messagePrefix = val);
        loadStringSetting("autosmelt_on_message", "AutoSmelt has been turned &aON", val -> this.autoSmeltOnMessage = val);
        loadStringSetting("autosmelt_off_message", "AutoSmelt has been turned &cOFF", val -> this.autoSmeltOffMessage = val);
    }

    private void loadBooleanSetting(String key, boolean defaultValue, java.util.function.Consumer<Boolean> setter) {
        YmlParser.ConfigReturn result = ymlParser.getBooleanValue(key, defaultValue, false);
        if (result.success()) {
            setter.accept(result.getBoolean());
        } else {
            // Use default value if parsing fails
            setter.accept(defaultValue);
        }
    }

    private void loadIntSetting(String key, int defaultValue, java.util.function.Consumer<Integer> setter) {
        YmlParser.ConfigReturn result = ymlParser.getIntValue(key, defaultValue, false);
        if (result.success()) {
            setter.accept(result.getInt());
        } else {
            setter.accept(defaultValue);
        }
    }

    private void loadStringSetting(String key, String defaultValue, java.util.function.Consumer<String> setter) {
        YmlParser.ConfigReturn result = ymlParser.getStringValue(key, defaultValue, false);
        if (result.success()) {
            setter.accept(result.getString());
        } else {
            setter.accept(defaultValue);
        }
    }

    // Getters
    public boolean getAutoPickup() { return this.autoPickup; }
    public boolean getAutoSmelt() { return this.autoSmelt; }
    public boolean getExpDrops() { return this.expDrop; }
    public boolean getFortuneDrops() { return this.fortuneDrop; }
    public boolean getPickDamage() { return this.pickDamage; }
    public boolean getDropAmount() { return this.randomDropAmount; }

    public int getIronExp() { return this.ironExp; }
    public int getGoldExp() { return this.goldExp; }
    public int getNetherScrapExp() { return this.netherScrapExp; }
    public int getMaxDropAmount() { return this.maxDropAmount; }
    public int getMinDropAmount() { return this.minDropAmount; }

    public String getMessagePrefix() { return this.messagePrefix; }
    public String getAutoSmeltOnMessage() { return this.autoSmeltOnMessage; }
    public String getAutoSmeltOffMessage() { return this.autoSmeltOffMessage; }

    // Smeltable ore methods
    public Map<Material, Material> getSmeltableOres() { return this.smeltableOres; }

    /**
     * Get the smelted result for a given ore block.
     * @param ore The ore block material
     * @return The smelted material, or null if not a smeltable ore
     */
    public Material getSmeltedResult(Material ore) {
        return this.smeltableOres.get(ore);
    }

    /**
     * Check if a block is a smeltable ore.
     * @param block The block material to check
     * @return true if the block is a smeltable ore
     */
    public boolean isSmeltableOre(Material block) {
        return this.smeltableOres.containsKey(block);
    }
}
