package com.illuzionzstudios.customfishing;

import com.illuzionzstudios.command.CommandManager;
import com.illuzionzstudios.config.Config;
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
import com.illuzionzstudios.customfishing.settings.Settings;
import lombok.Getter;

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

        // Add our default rewards
        YAMLRewardLoader.addDefault(new FoodDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new AppleDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new NothingDefaultTemplate("rewards"));

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
        new CommandManager(this).initialize(this);

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
        return "3.1.6";
    }
}
