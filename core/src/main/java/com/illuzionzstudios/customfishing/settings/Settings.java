/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.settings;

import com.illuzionzstudios.mist.config.ConfigSetting;
import com.illuzionzstudios.mist.config.PluginSettings;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;

/**
 * This is the main settings class relating to config.yml
 */
public class Settings extends PluginSettings {

    /**
     * Settings dealing with rewards and chances
     */
    public static class Reward {

        /**
         * The chance of finding a custom reward
         */
        public static ConfigSetting REWARD_CHANCE;

        /**
         * Amount of EXP rewarded from fishing when no custom reward
         */
        public static ConfigSetting EXP_REWARD;

        public static void init() {
            REWARD_CHANCE = new ConfigSetting(SETTINGS_FILE, "Main.Reward Chance", 30d,
                    "The chance of finding a custom reward while fishing"
                    , "can be a decimal number.");
            EXP_REWARD = new ConfigSetting(SETTINGS_FILE, "Main.Exp Reward", 6,
                    "Amount of experience to give the player when they HAVEN'T found a custom reward");
        }
    }

    /**
     * Settings dealing with title messages
     */
    public static class Title {

        /**
         * Ticks for the title to fade in
         */
        public static ConfigSetting TITLE_FADEIN;

        /**
         * Ticks for the title to actually display
         */
        public static ConfigSetting TITLE_DISPLAY;

        /**
         * Ticks for the title to fade out
         */
        public static ConfigSetting TITLE_FADEOUT;

        public static void init() {
            TITLE_FADEIN = new ConfigSetting(SETTINGS_FILE, "Title.Fade In", 5,
                    "Ticks for a title to fade in");
            TITLE_DISPLAY = new ConfigSetting(SETTINGS_FILE, "Title.Display", 40,
                    "Ticks for a title to stay on screen");
            TITLE_FADEOUT = new ConfigSetting(SETTINGS_FILE, "Title.Fade Out", 5,
                    "Ticks for a title to fade out");
        }
    }

    public Settings(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void loadSettings() {
        Reward.init();
        Title.init();

        // Only want our settings
        SETTINGS_FILE.setAutoRemove(true);
    }
}
