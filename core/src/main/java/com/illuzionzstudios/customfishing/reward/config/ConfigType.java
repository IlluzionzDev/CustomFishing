/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.config;

import com.illuzionzstudios.compatibility.CompatibleMaterial;
import com.illuzionzstudios.core.util.StringUtil;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.function.Function;

/**
 * Different way to set reward options. Used for
 * the GUI and for parsing changing options
 */
@RequiredArgsConstructor
@Getter
public enum ConfigType {

    SET_NAME(CompatibleMaterial.NAME_TAG.getMaterial(), 13, FishingReward::getName, false),

    /**
     * Custom case when adding items
     */
    SET_ITEMS(CompatibleMaterial.APPLE.getMaterial(), 28, reward -> "", false),

    SET_COMMANDS(CompatibleMaterial.COMMAND_BLOCK.getMaterial(), 29, reward -> StringUtil.loreListToString(reward.getCommands()), true),
    SET_MESSAGES(CompatibleMaterial.PAPER.getMaterial(), 30, reward -> StringUtil.loreListToString(reward.getMessages()), true),
    SET_BROADCASTS(CompatibleMaterial.PAPER.getMaterial(), 31, reward -> StringUtil.loreListToString(reward.getBroadcasts()), true),
    SET_TITLE(CompatibleMaterial.PAPER.getMaterial(), 32, reward -> reward.getTitle().toString(), false),
    SET_SUBTITLE(CompatibleMaterial.PAPER.getMaterial(), 33, reward -> reward.getSubtitle().toString(), false),
    SET_CHANCE(CompatibleMaterial.SPAWNER.getMaterial(), 34, reward -> "" + reward.getChance(), false),
    SET_VANILLA_REWARDS(CompatibleMaterial.FISHING_ROD.getMaterial(), 37, reward -> "" + reward.isVanillaRewards(), false),
    SET_EXP(CompatibleMaterial.EXPERIENCE_BOTTLE.getMaterial(), 38, reward -> "" + reward.getExperience(), false),
    SET_SOUND(CompatibleMaterial.NOTE_BLOCK.getMaterial(), 39, reward -> {
        if (reward.getSound() != null)
            return reward.getSound().name();
        return null;
    }, false),
    SET_PERMISSION(CompatibleMaterial.BARRIER.getMaterial(), 40, FishingReward::getPermission, false),
    SET_WORLDS(CompatibleMaterial.GRASS_BLOCK.getMaterial(), 41, reward -> StringUtil.loreListToString(reward.getWorlds()), true),
    SET_REGIONS(CompatibleMaterial.DIRT.getMaterial(), 42, reward -> StringUtil.loreListToString(reward.getRegions()), true),
    SET_BLOCKED_REGIONS(CompatibleMaterial.BARRIER.getMaterial(), 43, reward -> StringUtil.loreListToString(reward.getBlockedRegions()), true),
    ;

    /**
     * Icon to display this option
     */
    private final Material icon;

    /**
     * Slot in the config GUI
     */
    private final int slot;

    /**
     * Function to return the current value for this
     * as a string
     */
    private final Function<FishingReward, String> currentValue;

    /**
     * If the option is a list value
     */
    private final boolean list;

    /**
     * @return The identifier for the config path
     */
    public String getConfigIdentifier() {
        return name().toLowerCase().replace("_", "-");
    }

    public String getCurrentValue(FishingReward reward) {
        String base = currentValue.apply(reward);
        return base == null || base.trim().equals("") ? "Undefined" : base;
    }
}
