# AutoSmelt_LTS Changelog

## v1.0.0 - Complete Rewrite (2026)

### 🚀 Fully Recoded for Minecraft 1.21+

AutoSmelt has been completely rewritten from the ground up for modern Minecraft servers. This LTS (Long-Term Support) release focuses on stability, performance, and reliability.

---

### ✨ What's New

#### Core Improvements
- **Complete codebase rewrite** - Cleaner, more maintainable architecture
- **Java 21 support** - Built for modern JVM performance
- **Minecraft 1.21+ compatibility** - Fully updated for the latest Spigot API
- **Optimized event handling** - Reduced server overhead during mining operations

#### Smeltable Ores (Hardcoded for Stability)
- Iron Ore / Deepslate Iron Ore → Iron Ingot
- Gold Ore / Deepslate Gold Ore → Gold Ingot
- Copper Ore / Deepslate Copper Ore → Copper Ingot
- Ancient Debris → Netherite Scrap

#### Features
- **Toggle Command** (`/as`) - Players can enable/disable auto-smelting
- **Smelting Pickaxe** (`/smelt <player>`) - Special Netherite pickaxe that auto-smelts
- **Fortune Support** - Fortune enchantment increases ingot yield
- **Silk Touch Support** - Respects Silk Touch (drops original ore block)
- **Experience Drops** - Configurable XP orbs per ore type
- **Auto-Pickup** - Smelted items go directly to inventory
- **WorldGuard Integration** - Respects protected regions

#### Configuration Options
- `auto_smelt` - Global default toggle
- `auto_pickup` - Direct to inventory or drop naturally
- `pick_damage` - Smelting pickaxe durability loss
- `exp_drops` - Enable/disable XP orb spawning
- `fortune_drops` - Fortune enchantment multiplier
- `random_drop_amount` - Variable drop quantities
- Customizable messages with color code support

---

### 🔧 Technical Changes

- Removed legacy block configuration system for improved stability
- Fixed PlayerListener singleton bug causing toggle desync
- Modernized YAML configuration parsing
- Improved null-safety throughout codebase
- Streamlined permission system

---

### 📋 Permissions

| Permission | Description |
|------------|-------------|
| `autosmelt.mine` | Use /as toggle command |
| `autosmelt.give` | Use /smelt to give pickaxes |
| `autosmelt.reload` | Reload configuration |

---

### 🎯 Commands

| Command | Description |
|---------|-------------|
| `/as` | Toggle auto-smelt on/off |
| `/smelt <player>` | Give a smelting pickaxe |
| `/asreload` | Reload config from disk |

---

### 📦 Requirements

- **Minecraft:** 1.21+
- **Server:** Spigot/Paper
- **Java:** 21+
- **Optional:** WorldGuard 7.0.9+ (for region protection)

---

### 🔄 Migrating from AutoSmelt 1.x

This is a fresh LTS release. Simply drop the new JAR into your plugins folder - a new `config.yml` will be generated automatically. Your old configuration is not compatible with this version.

---

*AutoSmelt_LTS - Simple. Stable. Supported.*
