package com.illuzionzstudios.customfishing.reward;

import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.reward.item.ItemReward;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

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

/**
 * Reward object the player can find.
 */
@Data
@Builder
@NoArgsConstructor
public class FishingReward {

    /**
     * Name of the reward
     */
    private String name;

    /**
     * Commands to execute
     */
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give
     */
    private List<ItemStack> items = new ArrayList<>();

    /**
     * Messages to send
     */
    private List<String> messages = new ArrayList<>();

    /**
     * If broadcasting is enabled
     */
    private boolean broadcastEnabled;

    /**
     * Broadcast messages to send
     */
    private List<String> broadcasts = new ArrayList<>();

    /**
     * If titles are enabled
     */
    private boolean titleEnabled;

    /**
     * Title message
     */
    private Message title;

    /**
     * Subtitle message
     */
    private Message subtitle;

    /**
     * Chance to find this reward
     */
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    private boolean vanillaRewards;

    /**
     * Experience to give the player
     */
    private int experience;

    /**
     * Sound to play
     */
    private Sound sound;

    /**
     * Required permission
     */
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
    private List<String> blockedRegions = new ArrayList<>();

}
