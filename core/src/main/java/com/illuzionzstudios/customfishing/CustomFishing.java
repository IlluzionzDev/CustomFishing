package com.illuzionzstudios.customfishing;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.command.CustomFishingCommand;
import com.illuzionzstudios.customfishing.controller.FishingController;
import com.illuzionzstudios.customfishing.controller.RequirementController;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.template.loader.YAMLRewardLoader;
import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.config.PluginSettings;
import com.illuzionzstudios.mist.config.locale.Locale;
import com.illuzionzstudios.mist.config.locale.Message;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        YAMLRewardLoader.addDefault(FishingReward.builder()
                .name("Golden Apples")
                .commands(new ArrayList<>())
                .items(Collections.singletonList(new ItemStack(Material.GOLDEN_APPLE, 5)))
                .messages(Collections.singletonList("&a&l(!) &aYou found &a&l5 Golden Apples!"))
                .broadcasts(new ArrayList<>())
                .title(new Message("&a&lYou found a reward!"))
                .subtitle(new Message(""))
                .chance(50)
                .vanillaRewards(false)
                .experience(6)
                .sound(XSound.ENTITY_FIREWORK_ROCKET_LAUNCH)
                .permission("default")
                .worlds(Collections.singletonList("all"))
                .regions(Collections.singletonList("global"))
                .blockedRegions(new ArrayList<>())
                .build());

        YAMLRewardLoader.addDefault(FishingReward.builder()
                .name("Food")
                .commands(new ArrayList<>())
                .items(Collections.singletonList(new ItemStack(Material.COOKED_BEEF, 64)))
                .messages(Collections.singletonList("&a&l(!) &aYou found &a&l64 Beef!"))
                .broadcasts(new ArrayList<>())
                .title(new Message("&a&lYou found a reward!"))
                .subtitle(new Message(""))
                .chance(50)
                .vanillaRewards(false)
                .experience(6)
                .sound(XSound.ENTITY_FIREWORK_ROCKET_LAUNCH)
                .permission("default")
                .worlds(Collections.singletonList("all"))
                .regions(Collections.singletonList("global"))
                .blockedRegions(new ArrayList<>())
                .build());

        YAMLRewardLoader.addDefault(FishingReward.builder()
                .name("Nothing")
                .commands(Collections.singletonList("msg %player% sorry"))
                .items(new ArrayList<>())
                .messages(Collections.singletonList("&c&l(!) &cYou found NOTHING!"))
                .broadcasts(Collections.singletonList(""))
                .title(new Message("&a&lYou found a reward!"))
                .subtitle(new Message(""))
                .chance(50)
                .vanillaRewards(false)
                .experience(6)
                .sound(XSound.ENTITY_FIREWORK_ROCKET_LAUNCH)
                .permission("default")
                .worlds(Collections.singletonList("all"))
                .regions(Collections.singletonList("global"))
                .blockedRegions(new ArrayList<>())
                .build());

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
            Logger.displayError(e, "Couldn't save rewards");
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
