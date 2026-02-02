// ######################################################################
// # Class:  YmlParser
// # Author: torpkev
// # Description:
// # This class is a wrapper around reading from .yml files
// ######################################################################
package me.tweedjt.autosmelt.util;

import me.tweedjt.autosmelt.AutoSmelt;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class YmlParser {

    //region Objects
    AutoSmelt plugin;
    FileConfiguration config;
    //endregion
    //region Constructors
    public YmlParser(AutoSmelt plugin, File configFile) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }
    //endregion
    //region Methods

    /**
     * Gets an Integer config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getIntValue(String configItem, Integer defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isInt(configItem)) {
                    return new ConfigReturn(true, this.config.getInt(configItem));
                } else {
                    Log.error(
                            1,
                            "YmlParser",
                            "getValue(String configItem, Integer defaultValue, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, defaultValue);
                }
            } else {
                if (required) {

                    Log.error(
                            2,
                            "YmlParser",
                            "getValue(String configItem, Integer defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getValue(String configItem, Integer defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a double config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getDoubleValue(String configItem, double defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isDouble(configItem) || this.config.isInt(configItem)) {
                    return new ConfigReturn(true, this.config.getDouble(configItem));
                } else {

                    Log.error(
                            1,
                            "YmlParser",
                            "getDoubleValue(String configItem, double defaultValue, boolean required)",
                            "Config item is not an int or double",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, defaultValue);
                }
            } else {
                if (required) {
                    Log.error(
                            2,
                            "YmlParser",
                            "getDoubleValue(String configItem, double defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getValue(String configItem, double defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a long config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getLong(String configItem, long defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isLong(configItem) || this.config.isInt(configItem)) {
                    return new ConfigReturn(true, this.config.getLong(configItem));
                } else {
                    Log.error(
                            1,
                            "YmlParser",
                            "getLongValue(String configItem, long defaultValue, boolean required)",
                            "Config item is not an int or double",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, defaultValue);
                }
            } else {
                if (required) {
                    Log.error(
                            2,
                            "YmlParser",
                            "getLongValue(String configItem, long defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getLongValue(String configItem, long defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a String config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getStringValue(String configItem, String defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    return new ConfigReturn(true, this.config.getString(configItem));
                } else {
                    Log.error(
                            1,
                            "YmlParser",
                            "getValue(String configItem, String defaultValue, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, defaultValue);
                }
            } else {
                if (required) {

                    Log.error(
                            2,
                            "YmlParser",
                            "getValue(String configItem, String defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {

            Log.error(
                    3,
                    "YmlParser",
                    "getValue(String configItem, String defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a boolean config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getBooleanValue(String configItem, boolean defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isBoolean(configItem)) {
                    return new ConfigReturn(true, this.config.getBoolean(configItem));
                } else {
                    Log.error(
                            1,
                            "YmlParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is not boolean",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, defaultValue);
                }
            } else {
                if (required) {
                    Log.error(
                            2,
                            "YmlParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getValue(String configItem, boolean defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a string list config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getStringList(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isList(configItem)) {
                    return new ConfigReturn(true, this.config.getStringList(configItem));
                } else {
                    Log.error(
                            1,
                            "YmlParser",
                            "getValue(String configItem, List<String> defaultValue, boolean required)",
                            "Config item is not a list",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                    return new ConfigReturn(false, new ArrayList<String>());
                }
            } else {
                if (required) {
                    Log.error(
                            2,
                            "YmlParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, new ArrayList<String>());
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getValue(String configItem, boolean defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, new ArrayList<String>());
        }
    }

    /**
     * Gets a color from a config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getColor(String configItem, Color defaultValue, boolean required) {
        try {
            Color returnValue = defaultValue;
            if (this.config.contains(configItem)) {
                if (config.contains(configItem + ".name")) {
                    returnValue = Misc.colorFromName(config.getString(configItem + ".name"));
                } else if (
                        config.contains(configItem + ".red") &&
                                config.contains(configItem + ".green") &&
                                config.contains(configItem + ".blue")
                ) {
                    if (
                            config.isInt(configItem + ".red") &&
                                    config.isInt(configItem + ".green") &&
                                    config.isInt(configItem + ".blue")
                    ) {
                        returnValue = Color.fromRGB(
                                config.getInt(configItem + ".red"),
                                config.getInt(configItem + ".green"),
                                config.getInt(configItem + ".blue")
                        );
                    }

                }

                return new ConfigReturn(true, returnValue);

            } else {
                return new ConfigReturn(false, defaultValue);
            }
        } catch (Exception ex) {
            Log.error(
                    1,
                    "YmlParser",
                    "getValue(String configItem, Color defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, defaultValue);
        }
    }

    /**
     * Gets a Configuration Section from a config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return ConfigReturn object
     */
    public ConfigReturn getConfigSection(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isConfigurationSection(configItem)) {

                    ConfigurationSection configSection = this.config.getConfigurationSection(configItem);

                    return new ConfigReturn(true, configSection);
                } else {
                    if (required) {
                        Log.error(
                                1,
                                "YmlParser",
                                "getConfigSection(String configItem, boolean required)",
                                "Config item is not config section",
                                "Config Item: " + configItem,
                                Log.Severity.WARN,
                                null
                        );
                        return new ConfigReturn(false, null);
                    } else {
                        return new ConfigReturn(true, null);
                    }
                }
            } else {
                if (required) {
                    Log.error(
                            2,
                            "YmlParser",
                            "getConfigSection(String configItem, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            Log.Severity.WARN,
                            null
                    );
                }
                return new ConfigReturn(false, null);
            }
        } catch (Exception ex) {
            Log.error(
                    3,
                    "YmlParser",
                    "getConfigSection(String configItem, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    Log.Severity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigReturn(false, null);
        }
    }

    //endregion
    //region Sub-Classes
    public static class ConfigReturn {
        //region Objects
        private boolean success;
        private Object returnObject;
        //endregion
        //region Constructors
        public ConfigReturn(boolean success, Object returnObject) {
            this.success = success;
            this.returnObject = returnObject;
        }
        //endregion
        //region Getters

        /**
         * Checks if the config lookup was successful
         * @return If true, was successful
         */
        public boolean success() {
            return success;
        }

        /**
         * Gets the return object
         * @return Object being returned
         */
        public Object getReturnObject() {
            return returnObject;
        }

        /**
         * Gets the return object as a String
         * @return String of the returned object
         */
        public String getString() {
            if (returnObject instanceof String) {
                return (String) returnObject;
            } else {
                return "";
            }
        }

        /**
         * Gets the return object as a UUID
         * @return UUID of the returned object, null if not a UUID
         */
        public UUID getUUID() {
            if (returnObject instanceof String) {
                UUID uuid = Misc.uuidFromString((String) returnObject);
                return uuid;
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as a boolean
         * @return boolean of the returned object
         */
        public Boolean getBoolean() {
            if (returnObject instanceof Boolean) {
                return (Boolean) returnObject;
            }
            if (returnObject instanceof String) {
                String returnString = (String) returnObject;
                if ("true".equalsIgnoreCase(returnString)) {
                    return true;
                }
                if ("false".equalsIgnoreCase(returnString)) {
                    return false;
                }
            }
            return false;
        }

        /**
         * Gets the return object as an Integer
         * @return Integer of the returned object
         */
        public Integer getInt() {
            if (returnObject instanceof Integer) {
                return (Integer) returnObject;
            } else {
                return 0;
            }
        }

        /**
         * Gets the return object as a double
         * @return Double of the returned object
         */
        public Double getDouble() {
            if (returnObject instanceof Double) {
                return (Double) returnObject;
            } else {
                return (double) 0;
            }
        }

        /**
         * Gets the return object as a long
         * @return Long of the returned object
         */
        public long getLong() {
            if (returnObject instanceof Long) {
                return (long) returnObject;
            } else {
                return (long) 0;
            }
        }

        /**
         * Gets the return object as a long
         * @return Long of the returned object
         */
        public Timestamp getTimestamp() {
            if (returnObject instanceof Long) {
                long longTime = (long) returnObject;
                return new Timestamp(longTime);
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as a String List
         * @return String List of the returned object
         */
        public List<String> getStringList() {
            if (returnObject instanceof List) {
                return (List<String>) returnObject;
            } else {
                return new ArrayList<>();
            }
        }

        /**
         * Gets the return object as a Color
         * @return Color of the returned object
         */
        public Color getColor() {
            if (returnObject instanceof Color) {
                return (Color) returnObject;
            } else {
                return Color.WHITE;
            }
        }

        /**
         * Gets the return object as a Configuration Section
         * @return Configuration Section of the returned object
         */
        public ConfigurationSection getConfigSection() {
            if (returnObject instanceof ConfigurationSection) {
                return (ConfigurationSection) returnObject;
            } else {
                return null;
            }
        }
        //endregion
    }
    //endregion

}
