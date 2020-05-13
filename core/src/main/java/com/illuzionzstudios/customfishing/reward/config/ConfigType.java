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

    SET_NAME(Material.NAME_TAG, 10, FishingReward::getName, false),
    SET_COMMANDS(Material.COMMAND_BLOCK, 11, reward -> StringUtil.loreListToString(reward.getCommands()), true),
    SET_MESSAGES(Material.PAPER, 12, reward -> StringUtil.loreListToString(reward.getMessages()), true),
    SET_BROADCASTS(Material.PAPER, 13, reward -> StringUtil.loreListToString(reward.getBroadcasts()), true),
    SET_TITLE(Material.PAPER, 14, reward -> reward.getTitle().toString(), false),
    SET_SUBTITLE(Material.PAPER, 15, reward -> reward.getSubtitle().toString(), false),
    SET_CHANCE(Material.SPAWNER, 16, reward -> "" + reward.getChance(), false),
    SET_VANILLA_REWARDS(Material.FISHING_ROD, 19, reward -> "" + reward.isVanillaRewards(), false),
    SET_EXP(Material.EXPERIENCE_BOTTLE, 20, reward -> "" + reward.getExperience(), false),
    SET_SOUND(Material.NOTE_BLOCK, 21, reward -> reward.getSound().name(), false),
    SET_PERMISSION(Material.BARRIER, 22, FishingReward::getPermission, false),
    SET_WORLDS(Material.GRASS_BLOCK, 23, reward -> StringUtil.loreListToString(reward.getWorlds()), true),
    SET_REGIONS(Material.DIRT, 24, reward -> StringUtil.loreListToString(reward.getRegions()), true),
    SET_BLOCKED_REGIONS(Material.BARRIER, 25, reward -> StringUtil.loreListToString(reward.getBlockedRegions()), true),
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
        return currentValue.apply(reward);
    }
}
