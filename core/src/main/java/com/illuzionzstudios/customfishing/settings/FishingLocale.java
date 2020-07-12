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

import com.illuzionzstudios.mist.config.locale.Locale;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;

public class FishingLocale extends Locale {

    public FishingLocale(SpigotPlugin plugin) {
        super(plugin);
    }

    /**
     * General messages
     */
    public static class General extends Locale.General {

        /**
         * Indicate saving objects
         */
        public static String PLUGIN_SAVE = "&7Saved all rewards";

        public static void init() {
            PLUGIN_SAVE = loadMessage("general.save", PLUGIN_SAVE);
        }
    }

    /**
     * Language to do with interface
     */
    public static class Interface extends Locale.Interface {

        /**
         * Name for adding values in the edit list inventory
         */
        public static String VIEW_REWARDS_REWARD_NAME = "&d&l{rewardName}";

        /**
         * Lore for adding values in the edit list inventory
         */
        public static String VIEW_REWARDS_REWARD_LORE = "&7Chance: &d{chance}\n&r\n&7&o(Left click to edit reward)\n&c&o(Right click to delete reward)";

        public static void init() {
            VIEW_REWARDS_REWARD_NAME = loadMessage("interface.view-rewards.reward.name", VIEW_REWARDS_REWARD_NAME);
            VIEW_REWARDS_REWARD_LORE = loadMessage("interface.view-rewards.reward.lore", VIEW_REWARDS_REWARD_LORE);
        }
    }


    @Override
    protected int getConfigVersion() {
        return 1;
    }

    @Override
    public void loadLocale() {
        General.init();
        Interface.init();
    }
}
