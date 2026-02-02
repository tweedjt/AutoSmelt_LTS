![Header](https://github.com/user-attachments/assets/f10d389d-f3df-4095-8d29-be06d1fc77ca)

![About](https://github.com/user-attachments/assets/0b5d5a80-9416-4f12-a1b4-cbc639cff73b)

AutoSmelt is a plugin where you break an ore and receive an ingot!
Perfect for Prison Servers!

AutoSmelt LTS is the Free long term support version.
A premium more feature rich version will release soon!

---

![Features](https://github.com/user-attachments/assets/4c2bf146-fc54-46f7-b51c-949448874f37)

- Grab any pickaxe and start mining!
- Fortune pickaxe will drop extra ingots!
- SilkTouch pickaxe will pick up blocks instead of ingots
- Great for Prison and Factions servers!
- Lightweight / Doesn't cause server lag
- Worldguard Support
- Auto Pickup after blocks are mined
- Naturally drop blocks when players inventory is full
- AutoSmelt Pickaxe that can be spawned in and used without command
- Config to customize the plugin!
- Experience Orb Drops

---

![Commands](https://github.com/user-attachments/assets/7653a3c5-2ab1-482d-8e5d-830c67a1625d)

| Command | Description |
|---------|-------------|
| `/as` | Toggles AutoSmelt on/off |
| `/smelt <player>` | Spawns in AutoSmelt Pickaxe |
| `/asreload` | Reloads Config |

---

![Permissions](https://github.com/user-attachments/assets/064c7765-1e82-4453-85d8-927aa47b5fa2)

| Permission | Description |
|------------|-------------|
| `autosmelt.mine` | Required for /as |
| `autosmelt.give` | Required for /smelt <player> |
| `autosmelt.reload` | Required for /asreload |

---

![Planned Features](https://github.com/user-attachments/assets/c14f7f1f-4ff8-4509-9817-61e627c91c25)

- Adding Economy Support on block break (configurable)
- Adding blocks to configuration file to add custom "smelts"
- Open to suggestions! :)

---

![Configuration](https://github.com/user-attachments/assets/32ab0ca3-5237-4cb0-b865-e8a1f225c503)

```yaml
#Is AutoSmelt toggled on by default
auto_smelt: false

#Are Drops to Inventory on by default
auto_pickup: true

#Is the /smelt Pickaxe unbreakable?
pick_damage: false

#Drop exp orbs?
exp_drops: true

#Enable Fortune Drops for Fortune Pick?
fortune_drops: true

#Are random drops enabled?
random_drop_amount: false

#Set value of drop amount if "random_drop_amount = true"
min_drop_amount: 1
max_drop_amount: 4

#Set the experience orb value for Iron
iron_exp: 1

#Set the experience orb value for Gold
gold_exp: 1

#Set the experience orb value for Netherite Scrap
nether_scrap_exp: 2

#On message for /as command
autosmelt_on_message: "AutoSmelt has been turned &aON"

#Off message for /as command
autosmelt_off_message: "AutoSmelt has been turned &fOFF"

#Message prefix for plugin
message_prefix: "&d[&fAutoSmelt&d] &r"
