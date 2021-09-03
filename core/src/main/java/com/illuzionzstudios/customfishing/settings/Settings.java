package com.illuzionzstudios.customfishing.settings;

import com.illuzionzstudios.mist.config.ConfigSetting;
import com.illuzionzstudios.mist.config.ConfigSettings;
import com.illuzionzstudios.mist.config.PluginSettings;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;

/**
 * This is the main settings class relating to config.yml
 */
public class Settings extends PluginSettings {

    /**
     * Main plugin settings
     */
    public static final ConfigSettings FISHING_GROUP = new ConfigSettings("fishing");

    /**
     * Title settings
     */
    public static final ConfigSettings TITLE_GROUP = new ConfigSettings("title");

    /**
     * The chance of finding a custom reward
     */
    public static ConfigSetting REWARD_CHANCE = FISHING_GROUP.create("fishing.reward-chance", 100d,
            "The chance of finding a custom reward while fishing"
            , "can be a decimal number.");

    /**
     * Amount of EXP rewarded from fishing when no custom reward
     */
    public static ConfigSetting EXP_REWARD = FISHING_GROUP.create("fishing.exp-reward", "1--6",
            "Amount of experience to give the player when they HAVEN'T found a custom reward."
            , "\"--\" indicates a range between two numbers."
            ,"-1 indicates to not reward exp.");

    public static ConfigSetting MIN_WAIT_TIME = FISHING_GROUP.create("fishing.min-wait-time", 100,
            "The minimum amount of ticks to wait for a bite. This is before processing lore and other things"
            , "REQUIRES 1.16+");

    public static ConfigSetting MAX_WAIT_TIME = FISHING_GROUP.create("fishing.max-wait-time", 600,
            "The maximum amount of ticks to wait for a bite. This is before processing lore and other things"
            , "REQUIRES 1.16+");

    /**
     * Ticks for the title to fade in
     */
    public static ConfigSetting TITLE_FADEIN = TITLE_GROUP.create("title.fadein", 5,
            "Ticks for a title to fade in");

    /**
     * Ticks for the title to actually display
     */
    public static ConfigSetting TITLE_DISPLAY = TITLE_GROUP.create("title.display", 40,
            "Ticks for a title to stay on screen");

    /**
     * Ticks for the title to fade out
     */
    public static ConfigSetting TITLE_FADEOUT = TITLE_GROUP.create("title.fadeout", 5,
            "Ticks for a title to fade out");

    public Settings(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void loadSettings() {
        FISHING_GROUP.load();
        TITLE_GROUP.load();

        // Only want our settings
        SETTINGS_FILE.setAutoRemove(true);
    }
}
