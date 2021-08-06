package com.illuzionzstudios.customfishing.settings;

import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.config.locale.PluginLocale;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;

public class FishingLocale extends PluginLocale {

    public FishingLocale(SpigotPlugin plugin) {
        super(plugin);
    }

    /**
     * Indicate saving objects
     */
    public static MistString GENERAL_PLUGIN_SAVE = GENERAL_GROUP.create("general.save", "&7Saved all rewards");

    /**
     * Name for adding values in the edit list inventory
     */
    public static MistString INTERFACE_VIEW_REWARDS_REWARD_NAME = INTERFACE_GROUP.create("interface.view-rewards.reward.name", "&d&l{rewardName}");

    /**
     * Lore for adding values in the edit list inventory
     */
    public static MistString INTERFACE_VIEW_REWARDS_REWARD_LORE = INTERFACE_GROUP.create("interface.view-rewards.reward.lore", "&7Chance: &d{chance}\n&r\n&7&o(Left click to edit reward)\n&c&o(Right click to delete reward)");

    @Override
    public void loadLocale() {
    }
}
