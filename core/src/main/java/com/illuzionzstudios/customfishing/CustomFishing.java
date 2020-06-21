package com.illuzionzstudios.customfishing;

import com.illuzionzstudios.customfishing.command.CustomFishingCommand;
import com.illuzionzstudios.customfishing.controller.FishingController;
import com.illuzionzstudios.customfishing.controller.RequirementController;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.template.defaults.AppleDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.defaults.FoodDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.defaults.NothingDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.loader.YAMLRewardLoader;
import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.config.PluginSettings;
import com.illuzionzstudios.mist.config.locale.Locale;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */

public final class CustomFishing extends SpigotPlugin {

    /**
     * Singleton instance of our {@link SpigotPlugin}
     */
    private static volatile CustomFishing INSTANCE;
    /**
     * Plugin hooks
     */
    @Getter
    private static boolean worldguardLoaded = false;

    /**
     * Return our instance of the {@link SpigotPlugin}
     * <p>
     * Should be overridden in your own {@link SpigotPlugin} class
     * as a way to implement your own methods per plugin
     *
     * @return This instance of the plugin
     */
    public static CustomFishing getInstance() {
        // Assign if null
        if (INSTANCE == null) {
            INSTANCE = JavaPlugin.getPlugin(CustomFishing.class);

            Objects.requireNonNull(INSTANCE, "Cannot create instance of plugin. Did you reload?");
        }

        return INSTANCE;
    }

    @Override
    public void onPluginLoad() {
    }

    @Override
    public void onPluginPreEnable() {

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

        // Add our default rewards
        YAMLRewardLoader.addDefault(new FoodDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new AppleDefaultTemplate("rewards"));
        YAMLRewardLoader.addDefault(new NothingDefaultTemplate("rewards"));

        RewardsController.INSTANCE.initialize(this);

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

        RewardsController.INSTANCE.stop(this);
        FishingController.INSTANCE.stop(this);
        RequirementController.INSTANCE.stop(this);
    }

    @Override
    public void onPluginPreReload() {

    }

    @Override
    public void onPluginReload() {
        // Load rewards again
        RewardsController.INSTANCE.initialize(this);
    }

    @Override
    public void onReloadablesStart() {
        registerMainCommand(new CustomFishingCommand(), "customfishing", "customf", "cfishing");

        registerListener(FishingController.INSTANCE);
    }

    @Override
    public PluginSettings getPluginSettings() {
        return new Settings(this);
    }

    @Override
    public Locale getPluginLocale() {
        return new FishingLocale(this);
    }
}
