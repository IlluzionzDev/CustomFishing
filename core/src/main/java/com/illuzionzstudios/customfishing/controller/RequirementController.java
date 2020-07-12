/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.controller.worldguard.IWorldGuardCheck;
import com.illuzionzstudios.customfishing.controller.worldguard.WorldGuardCheck_1_12_R1;
import com.illuzionzstudios.customfishing.controller.worldguard.WorldGuardCheck_1_13_R1;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.requirement.check.PermissionCheck;
import com.illuzionzstudios.customfishing.reward.requirement.check.RegionCheck;
import com.illuzionzstudios.customfishing.reward.requirement.check.WorldCheck;
import com.illuzionzstudios.mist.compatibility.ServerVersion;
import com.illuzionzstudios.mist.controller.PluginController;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Control certain requirements to find certain rewards
 */
public enum RequirementController implements PluginController<CustomFishing> {
    INSTANCE;

    /**
     * Used in multi-version support for WorldGuard
     */
    @Getter
    private IWorldGuardCheck worldGuardCheck;

    @Override
    public void initialize(CustomFishing customFishing) {
        // Checks for version
        if (ServerVersion.atLeast(ServerVersion.V.v1_13)) {
            this.worldGuardCheck = new WorldGuardCheck_1_13_R1();

        } else if (ServerVersion.atLeast(ServerVersion.V.v1_8)) {
            this.worldGuardCheck = new WorldGuardCheck_1_12_R1();
        }
    }

    @Override
    public void stop(CustomFishing customFishing) {

    }

    /**
     * Get a list of all rewards a player can find
     * Better than recursion as we only need to run a few checks rather than looping
     * a ton
     *
     * @param player Player to check
     * @return List of all fishing rewards
     */
    public List<FishingReward> getAvailableRewards(Player player) {
        List<FishingReward> rewards = new ArrayList<>();

        for (FishingReward reward : RewardsController.INSTANCE.getLoadedRewards().values()) {
            // Add reward if all checks pass
            if (processChecks(player, reward)) rewards.add(reward);
        }

        return rewards;
    }

    /**
     * Run checks if they can find the reward
     *
     * @param reward The reward to check
     * @return If all checks pass
     */
    public boolean processChecks(Player player, FishingReward reward) {
        WorldCheck worldCheck = new WorldCheck();
        RegionCheck regionCheck = new RegionCheck();
        PermissionCheck permissionCheck = new PermissionCheck();

        // Go through all checks
        return worldCheck.processCheck(player, reward) &&
                regionCheck.processCheck(player, reward) &&
                permissionCheck.processCheck(player, reward);
    }
}
