package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.chance.LootTable;
import com.illuzionzstudios.compatibility.ServerVersion;
import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.config.ConfigSection;
import com.illuzionzstudios.core.bukkit.controller.BukkitController;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.FishingRewardBuilder;
import lombok.Getter;
import org.bukkit.Sound;

import java.util.ArrayList;
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

public enum RewardsController implements BukkitController<CustomFishing> {
    INSTANCE;

    @Getter
    private List<FishingReward> loadedRewards;

    /**
     * Loot table of rewards
     */
    private LootTable<FishingReward> lootTable = new LootTable<>();

    @Override
    public void initialize(CustomFishing customFishing) {
        Config config = customFishing.getExtraConfig().get(0).clearDefaults();

        // Clear our already loaded data if any
        this.loadedRewards = new ArrayList<>();
        this.lootTable.clear();

        // Load rewards into memory
        if (config.isConfigurationSection("Rewards")) {
            for (ConfigSection section : config.getSections("Rewards")) {
                // Detection if sound enum is valid
                Sound sound = null;

                if (section.getString("Sound") != null) {
                    try {
                        sound = Sound.valueOf(section.getString("Sound"));
                    } catch (Exception ex) {
                        // Not a valid sound or not loaded correctly
                        Logger.severe("Sound " + section.getString("Sound") + " is not valid for reward " + section.getNodeKey());
                        Logger.severe("The sound is not valid or is not available on your server version " + ServerVersion.getServerVersionString());
                        continue;
                    }
                }

                loadedRewards.add(new FishingRewardBuilder()
                        .setName(section.getNodeKey())
                        .setCommands(section.getStringList("Commands"))
                        .setMessages(section.getStringList("Messages"))
                        .setBroadcastEnabled(section.getBoolean("Broadcast"))
                        .setBroadcasts(section.getStringList("Broadcasts"))
                        .setTitleEnabled(section.getBoolean("Title Enabled"))
                        .setTitle(new Message(section.getString("Title")))
                        .setSubTitle(new Message(section.getString("Sub Title")))
                        .setChance(section.getDouble("Chance"))
                        .setVanillaRewards(section.getBoolean("Vanilla Rewards"))
                        .setExperience(section.getInt("Exp Amount"))
                        .setSound(sound)

                        .setPermission("customfishing." + section.getString("Requirements.Permission"))
                        .setWorlds(section.getStringList("Requirements.Worlds"))
                        .setRegions(section.getStringList("Requirements.Regions")).build());

                Logger.info("Loaded reward '" + section.getNodeKey() + "'");
            }
        }


        // Load chance sum
        if (loadedRewards.isEmpty()) {
            return;
        }

        for (FishingReward reward : loadedRewards) {
            lootTable.addLoot(reward, reward.getChance());
        }
    }

    @Override
    public void stop(CustomFishing customFishing) {
    }

    public FishingReward pickReward() {
        if (loadedRewards.isEmpty() || loadedRewards == null) {
            // End up returning null if no loaded rewards.
            // Will be dealt with elsewhere
            return null;
        }

        try {
            return lootTable.pick();
        } catch (Exception ex) {
            return null;
        }
    }
}
