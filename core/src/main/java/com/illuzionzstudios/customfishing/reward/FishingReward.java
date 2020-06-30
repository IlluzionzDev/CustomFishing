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
     * Broadcast messages to send
     */
    private List<String> broadcasts = new ArrayList<>();

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
    private XSound sound;

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
