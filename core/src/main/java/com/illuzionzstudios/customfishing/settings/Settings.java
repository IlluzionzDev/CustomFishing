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
    public static final ConfigSettings MAIN_GROUP = new ConfigSettings("main");

    /**
     * Title settings
     */
    public static final ConfigSettings TITLE_GROUP = new ConfigSettings("title");

    /**
     * The chance of finding a custom reward
     */
    public static ConfigSetting REWARD_CHANCE = MAIN_GROUP.create("Main.Reward Chance", 30d,
            "The chance of finding a custom reward while fishing"
            , "can be a decimal number.");

    /**
     * Amount of EXP rewarded from fishing when no custom reward
     */
    public static ConfigSetting EXP_REWARD = MAIN_GROUP.create("Main.Exp Reward", 6,
            "Amount of experience to give the player when they HAVEN'T found a custom reward");

    /**
     * Ticks for the title to fade in
     */
    public static ConfigSetting TITLE_FADEIN = TITLE_GROUP.create("Title.Fade In", 5,
            "Ticks for a title to fade in");

    /**
     * Ticks for the title to actually display
     */
    public static ConfigSetting TITLE_DISPLAY = TITLE_GROUP.create("Title.Display", 40,
            "Ticks for a title to stay on screen");

    /**
     * Ticks for the title to fade out
     */
    public static ConfigSetting TITLE_FADEOUT = TITLE_GROUP.create("Title.Fade Out", 5,
            "Ticks for a title to fade out");

    public Settings(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void loadSettings() {
        MAIN_GROUP.load();
        TITLE_GROUP.load();

        // Only want our settings
        SETTINGS_FILE.setAutoRemove(true);
    }
}
