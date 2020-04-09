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
import com.illuzionzstudios.customfishing.reward.template.RewardLoadException;
import com.illuzionzstudios.customfishing.reward.template.loader.YAMLRewardLoader;
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
    private final LootTable<FishingReward> lootTable = new LootTable<>();

    @Override
    public void initialize(CustomFishing customFishing) {
        // Clear our already loaded data if any
        this.loadedRewards = new ArrayList<>();
        this.lootTable.clear();

        // Reward loader for "/rewards" dir
        YAMLRewardLoader loader = new YAMLRewardLoader("rewards");

        // Try load all templates
        loader.loadTemplates().forEach((fileName, template) -> {
            try {
                this.loadedRewards.add(template.create());

                // Log loaded file
                Logger.info("Loaded reward " + template.getTemplateFile().getString("Name") + " from file " + fileName + ".yml");
            } catch (RewardLoadException ex) {
                // Throw trace
                ex.printStackTrace();
            }
        });
        
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
        if (loadedRewards.isEmpty()) {
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
