package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.config.YamlConfig;
import com.illuzionzstudios.mist.controller.PluginController;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;
import com.illuzionzstudios.mist.util.ItemStackUtil;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;

/**
 * This controller is here when we make API breaking changes to files. It
 * handles the conversion to the new version
 */
public enum ConverterController implements PluginController<CustomFishing> {
    INSTANCE;

    @Override
    public void initialize(CustomFishing customFishing) {

    }

    @Override
    public void stop(CustomFishing customFishing) {

    }

    /**
     * Looks through all files with old keys and creates a new file for each of them
     */
    public void convertAll() {
        // Reward directory
        File dir = new File(SpigotPlugin.getInstance().getDataFolder().getPath() + File.separator + "rewards");

        // If doesn't exist and has to create no point loading
        if (dir.listFiles() == null || !dir.exists()) {
            return;
        }

        // Go through files
        for (File file : dir.listFiles()) {
            YamlConfig config = new YamlConfig(file);
            config.load();

            if (config.getString("Name") != null || config.getString("Name").isEmpty()) {
                Logger.info("Converting old file " + config.getFileName());
                // Is old file, so lets convert
                YamlConfig newConfig = new YamlConfig(SpigotPlugin.getInstance(), "/rewards", config.getFileName());
                newConfig.set("name", config.getString("Name"));
                newConfig.set("commands", config.getStringList("Commands"));

                ArrayList<ItemStack> items = new ArrayList<>();
                // Load items
                for (String item : config.getStringList("Items")) {
                    // Deserialize item stack
                    ItemStack stack = ItemStackUtil.deserialize(item);
                    if (stack != null) {
                        items.add(stack);
                    }
                }

                int counter = 0;
                for (ItemStack item : items) {
                    String path = "items." + counter + ".";

                    newConfig.set(path + "chance", 100);
                    newConfig.set(path + "item-name", item.getItemMeta() != null && item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : "");
                    newConfig.set(path + "lore", item.getItemMeta() != null && item.getItemMeta().hasLore() ? item.getItemMeta().getLore() : "");
                    newConfig.set(path + "amount", item.getAmount());
                    newConfig.set(path + "material", item.getType().toString());
                    ArrayList<String> enchants = new ArrayList<>();
                    item.getEnchantments().forEach((ench, level) -> {
                        enchants.add(ench.getName() + ":" + level);
                    });
                    newConfig.set(path + "enchants", enchants);
                }

                newConfig.set("messages", config.getStringList("Messages"));
                newConfig.set("broadcasts", config.getStringList("Broadcasts"));
                newConfig.set("title", config.getString("Title"));
                newConfig.set("sub-title", config.getString("Sub Title"));
                newConfig.set("weight", config.getDouble("Chance"));
                newConfig.set("vanilla-rewards", config.getBoolean("Vanilla Rewards"));
                newConfig.set("exp-amount", config.getInt("Exp Amount"));
                newConfig.set("sound", config.getString("Sound"));
                newConfig.set("requirements.permission", config.getString("Requirements.Permission"));
                newConfig.set("requirements.worlds", config.getStringList("Requirements.Worlds"));
                newConfig.set("requirements.regions", config.getStringList("Requirements.Regions"));
                newConfig.set("requirements.blocked-regions", config.getStringList("Requirements.BlockedRegions"));

                config.getFile().delete();
                newConfig.save();
            }
        }
    }
}
