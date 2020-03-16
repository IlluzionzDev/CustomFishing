package com.illuzionzstudios.customfishing;

import com.illuzionzstudios.command.CommandManager;
import com.illuzionzstudios.compatibility.ServerVersion;
import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.core.plugin.IlluzionzPlugin;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.command.CustomFishingCommand;
import com.illuzionzstudios.customfishing.controller.FishingController;
import com.illuzionzstudios.customfishing.controller.RequirementController;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.settings.Settings;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */

public final class CustomFishing extends IlluzionzPlugin {

    private static CustomFishing INSTANCE;
    public final Config rewardsConfig = new Config(this, "rewards.yml");

    /**
     * Plugin hooks
     */
    @Getter
    private boolean worldguardLoaded = false;

    public static CustomFishing getInstance() {
        return INSTANCE;
    }

    @Override
    public void onPluginLoad() {
        INSTANCE = this;
    }

    @Override
    public void onPluginEnable() {
        // Detect hooks
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            worldguardLoaded = true;
            Logger.info("Hooked into WorldGuard for region checks");
        } else {
            Logger.info("Couldn't find WorldGuard so region checks are disabled");
        }

        // Load configuration
        Settings.loadConfig();
        this.setLocale(Settings.LANGUGE_MODE.getString(), false);

        loadCommands();
        loadRewards();

        // Load FishingController
        FishingController.INSTANCE.initialize(this);
        RequirementController.INSTANCE.initialize(this);
    }

    @Override
    public void onPluginDisable() {

    }

    @Override
    public void onConfigReload() {
        this.setLocale(Settings.LANGUGE_MODE.getString(), true);
        this.locale.reloadMessages();
        loadRewards(); // Reload rewards into memory
    }

    /*
     * Insert default rewards into config.
     */
    private void setupRewards() {
        if (!rewardsConfig.contains("Rewards")) {

            // Changes based on version for default config
            String soundName = "";

            if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_13)) {
                soundName = "ENTITY_FIREWORK_ROCKET_LAUNCH";
            } else if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_12)) {
                soundName = "ENTITY_FIREWORK_LAUNCH";
            } else if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_8)) {
                soundName = "FIREWORK_LAUNCH";
            }

            rewardsConfig.createDefaultSection("Rewards",
                    "The rewards able to be caught.",
                    "Copy a reward and modify to add a new reward.")
                    .setDefault("Nothing.Commands", Arrays.asList("msg %player% sorry"),
                            "Commands to run when they've caught this reward.")
                    .setDefault("Nothing.Messages", Arrays.asList("&c&l(!) &cYou found NOTHING!"),
                            "Messages to send when they've caught this reward.")
                    .setDefault("Nothing.Broadcast", false,
                            "Should we broadcast a message to all players when this reward",
                            "is found?")
                    .setDefault("Nothing.Broadcasts", Arrays.asList(""),
                            "Messages to broadcast when they've caught this reward.")
                    .setDefault("Nothing.Title Enabled", true,
                            "Should we send a title to the player when this reward",
                            "is found?")
                    .setDefault("Nothing.Title", "&a&lYou found a reward!",
                            "Title message to send.")
                    .setDefault("Nothing.Sub Title", "",
                            "Subtitle message to send.")
                    .setDefault("Nothing.Chance", 50,
                            "The chance of this reward being caught.")
                    .setDefault("Nothing.Vanilla Rewards", false,
                            "Should we give them normal vanilla rewards when",
                            "they find this? For example enchanted books, fish etc.")
                    .setDefault("Nothing.Exp Amount", 6,
                            "Amount of exp to give player when they find this reward.")
                    .setDefault("Nothing.Sound", soundName,
                            "Sound to play when they find this reward.")
                    .setDefault("Nothing.Requirements.Permission", "default",
                            "Required permission to find this reward. The node is customfishing.<value>,",
                            "so here it would be customfishing.default.")
                    .setDefault("Nothing.Requirements.Worlds", Arrays.asList("all"),
                            "Worlds the reward can be caught in. Set to 'all' for every world")
                    .setDefault("Nothing.Requirements.Regions", Arrays.asList("global"),
                            "Region the player has to be in to catch the reward. Requires",
                            "WorldGuard in order to work. Set to 'global' for all regions")

                    .setDefault("Golden Apples.Commands", Arrays.asList("give %player% golden_apple 5"))
                    .setDefault("Golden Apples.Messages", Arrays.asList("&a&l(!) &aYou found &a&l5 Golden Apples!"))
                    .setDefault("Golden Apples.Broadcast", false)
                    .setDefault("Golden Apples.Broadcasts", Arrays.asList(""))
                    .setDefault("Golden Apples.Title Enabled", true)
                    .setDefault("Golden Apples.Title", "&a&lYou found a reward!")
                    .setDefault("Golden Apples.Sub Title", "")
                    .setDefault("Golden Apples.Chance", 50)
                    .setDefault("Golden Apples.Vanilla Rewards", false)
                    .setDefault("Golden Apples.Exp Amount", 6)
                    .setDefault("Golden Apples.Sound", soundName)
                    .setDefault("Golden Apples.Requirements.Permission", "default")
                    .setDefault("Golden Apples.Requirements.Worlds", Arrays.asList("all"))
                    .setDefault("Golden Apples.Requirements.Regions", Arrays.asList("global"))

                    .setDefault("Food.Commands", Arrays.asList("give %player% cooked_beef 64"))
                    .setDefault("Food.Messages", Arrays.asList("&a&l(!) &aYou found &a&l64 Steak!"))
                    .setDefault("Food.Broadcast", false)
                    .setDefault("Food.Broadcasts", Arrays.asList(""))
                    .setDefault("Food.Title Enabled", true)
                    .setDefault("Food.Title", "&a&lYou found a reward!")
                    .setDefault("Food.Sub Title", "")
                    .setDefault("Food.Chance", 50)
                    .setDefault("Food.Vanilla Rewards", false)
                    .setDefault("Food.Exp Amount", 6)
                    .setDefault("Food.Sound", soundName)
                    .setDefault("Food.Requirements.Permission", "default")
                    .setDefault("Food.Requirements.Worlds", Arrays.asList("all"))
                    .setDefault("Food.Requirements.Regions", Arrays.asList("global"));
        }

        rewardsConfig.setRootNodeSpacing(1).setCommentSpacing(0);
    }

    /**
     * Load all rewards from config
     */
    private void loadRewards() {
        rewardsConfig.clearConfig(true);
        rewardsConfig.load();
        setupRewards();
        rewardsConfig.saveChanges();

        RewardsController.INSTANCE.initialize(this);
    }

    /**
     * Register all plugin commands
     */
    private void loadCommands() {
        new CommandManager(this).initialize(this);

        CommandManager.get().register(new CustomFishingCommand(this));
    }

    @Override
    public List<Config> getExtraConfig() {
        return Collections.singletonList(rewardsConfig);
    }
}
