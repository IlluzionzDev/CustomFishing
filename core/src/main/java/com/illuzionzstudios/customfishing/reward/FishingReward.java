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
import com.illuzionzstudios.mist.compatibility.XSound;
import com.illuzionzstudios.mist.config.locale.Message;
import lombok.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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
    @Configurable
    private String name;

    /**
     * Commands to execute
     */
    @Configurable
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give
     */
    @Configurable
    private List<ItemStack> items = new ArrayList<>();

    /**
     * Messages to send
     */
    @Configurable
    private List<String> messages = new ArrayList<>();

    /**
     * Broadcast messages to send
     */
    @Configurable
    private List<String> broadcasts = new ArrayList<>();

    /**
     * Title message
     */
    @Configurable
    private Message title;

    /**
     * Subtitle message
     */
    @Configurable
    private Message subtitle;

    /**
     * Chance to find this reward
     */
    @Configurable
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    @Configurable
    private boolean vanillaRewards;

    /**
     * Experience to give the player
     */
    @Configurable
    private int experience;

    /**
     * Sound to play
     */
    @Configurable
    private XSound sound;

    /**
     * Required permission
     */
    @Configurable
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    @Configurable
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    @Configurable
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
    @Configurable
    private List<String> blockedRegions = new ArrayList<>();

}
