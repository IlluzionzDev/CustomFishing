package com.illuzionzstudios.customfishing.settings;

import com.illuzionzstudios.core.config.Config;
import com.illuzionzstudios.core.config.ConfigSetting;
import com.illuzionzstudios.customfishing.CustomFishing;

/**
 * Created by Illuzionz on 12 2019
 */
public class Settings {

    static final Config config = CustomFishing.getInstance().getCoreConfig();

    public static final ConfigSetting REWARD_CHANCE = new ConfigSetting(config, "Main.Reward Chance", 30d,
            "The chance of finding a custom reward while fishing"
            ,"can be a decimal number.");

    public static final ConfigSetting EXP_REWARD = new ConfigSetting(config, "Main.Exp Reward", 6,
            "Amount of experience to give the player when they HAVEN'T found a reward");

    public static final ConfigSetting TITLE_FADEIN = new ConfigSetting(config, "Title.Fade In", 5,
            "Ticks for a title to fade in");

    public static final ConfigSetting TITLE_DISPLAY = new ConfigSetting(config, "Title.Display", 40,
            "Ticks for a title to stay on screen");

    public static final ConfigSetting TITLE_FADEOUT = new ConfigSetting(config, "Title.Fade Out", 5,
            "Ticks for a title to fade out");

    public static final ConfigSetting LANGUGE_MODE = new ConfigSetting(config, "System.Language Mode", "en_US",
            "The language file to use for the plugin",
            "More language files (if available) can be found in the plugins locale folder.");
    public static final ConfigSetting AUTOSAVE = new ConfigSetting(config, "System.Autosave Interval", 60,
            "Seconds to autosave and load the updated config");


    /**
     * Setup the configuration
     */
    public static void loadConfig() {
        config.load();
        config.setAutoremove(true).setAutosave(true).setAutosaveInterval(AUTOSAVE.getInt());

        config.saveChanges();
    }
}
