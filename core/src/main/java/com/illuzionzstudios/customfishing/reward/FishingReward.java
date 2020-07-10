/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward;

import com.illuzionzstudios.customfishing.reward.config.Configurable;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.mist.compatibility.XSound;
import com.illuzionzstudios.mist.config.locale.Message;
import lombok.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reward object the player can find.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FishingReward {

    /**
     * Name of the reward
     */
    @Configurable(description = "rewards.config.descriptions.name")
    private String name;

    /**
     * Commands to execute
     */
    @Configurable(description = "rewards.config.descriptions.commands")
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give
     */
    @Configurable(description = "rewards.config.descriptions.items")
    private List<ItemStack> items = new ArrayList<>();

    /**
     * Messages to send
     */
    @Configurable(description = "rewards.config.descriptions.messages")
    private List<String> messages = new ArrayList<>();

    /**
     * Broadcast messages to send
     */
    @Configurable(description = "rewards.config.descriptions.broadcasts")
    private List<String> broadcasts = new ArrayList<>();

    /**
     * Title message
     */
    @Configurable(description = "rewards.config.descriptions.title")
    private Message title;

    /**
     * Subtitle message
     */
    @Configurable(description = "rewards.config.descriptions.subtitle")
    private Message subtitle;

    /**
     * Chance to find this reward
     */
    @Configurable(description = "rewards.config.descriptions.chance")
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    @Configurable(description = "rewards.config.descriptions.vanillaRewards")
    private boolean vanillaRewards;

    /**
     * Experience to give the player
     */
    @Configurable(description = "rewards.config.descriptions.experience")
    private int experience;

    /**
     * Sound to play
     */
    @Configurable(description = "rewards.config.descriptions.sound")
    private XSound sound;

    /**
     * Required permission
     */
    @Configurable(description = "rewards.config.descriptions.permission")
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    @Configurable(description = "rewards.config.descriptions.worlds")
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    @Configurable(description = "rewards.config.descriptions.regions")
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
    @Configurable(description = "rewards.config.descriptions.blockedRegions")
    private List<String> blockedRegions = new ArrayList<>();

    /**
     * @return Basic default {@link FishingReward} to be configured
     */
    public static FishingReward ofDefault() {
        return new FishingReward(
                "New Reward",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new Message(""),
                new Message(""),
                0.0,
                true,
                6,
                null,
                "",
                Collections.singletonList("all"),
                Collections.singletonList("global"),
                new ArrayList<>()
        );
    }

}
