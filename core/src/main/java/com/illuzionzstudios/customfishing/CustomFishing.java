package com.illuzionzstudios.customfishing;

import com.illuzionzstudios.command.CommandManager;
import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.core.IlluzionzCore;
import com.illuzionzstudios.core.plugin.IlluzionzPlugin;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.command.CustomFishingCommand;
import com.illuzionzstudios.customfishing.controller.FishingController;
import com.illuzionzstudios.customfishing.controller.RequirementController;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.template.defaults.AppleDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.defaults.FoodDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.defaults.NothingDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.loader.YAMLRewardLoader;
import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.scheduler.bukkit.BukkitScheduler;
import com.illuzionzstudios.ui.controller.InterfaceController;
import lombok.Getter;

import java.io.IOException;
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

    public static CustomFishing getInstance() {
        return INSTANCE;
    }

    /**
     * Plugin hooks
     */
    @Getter
    private boolean worldguardLoaded = false;

    @Override
    public void onPluginLoad() {
        setPlugin(this);
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
        this.setLocale(Settings.LANGUAGE_MODE.getString(), false);

        new BukkitScheduler(this).initialize();
        new InterfaceController<>(this);

        loadCommands();

        // Add our default rewards
        YAMLRewardLoader.addDefault(new FoodDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new AppleDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new NothingDefaultTemplate("rewards"));

        loadRewards();

        // Load FishingController
        FishingController.INSTANCE.initialize(this);
        RequirementController.INSTANCE.initialize(this);

        // Plugin metrics
        int pluginId = 7247;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onPluginDisable() {
        // Save all items into files
        try {
            new YAMLSerializerLoader("rewards").saveRewards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigReload() {
        this.setLocale(Settings.LANGUAGE_MODE.getString(), true);
        this.getLocale().reloadMessages();
        loadRewards(); // Reload rewards into memory
    }

    /**
     * Load all rewards from config
     */
    private void loadRewards() {
        RewardsController.INSTANCE.initialize(this);
    }

    /**
     * Register all plugin commands
     */
    private void loadCommands() {
        new CommandManager().initialize(this);

        CommandManager.get().register(new CustomFishingCommand(this));
    }

    @Override
    public List<Config> getExtraConfig() {
        return null;
    }

    @Override
    public String getPluginName() {
        return "CustomFishing";
    }

    @Override
    public String getPluginVersion() {
        return "4.0.1";
    }
}
