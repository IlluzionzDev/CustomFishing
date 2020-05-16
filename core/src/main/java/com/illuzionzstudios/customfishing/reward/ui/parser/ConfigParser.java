/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.ui.parser;

import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.config.ConfigType;
import com.illuzionzstudios.customfishing.reward.ui.ConfigureUI;
import com.illuzionzstudios.ui.button.listeners.StringPromptListener;
import org.bukkit.Sound;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

/**
 * Parse when trying to set the different config values
 */
public class ConfigParser extends StringPromptListener {

    /**
     * Fishing reward we're setting
     */
    private final FishingReward reward;

    /**
     * Type of option we're setting
     */
    private final ConfigType type;

    public ConfigParser(Message message, FishingReward reward, ConfigType type) {
        super(message);

        this.reward = reward;
        this.type = type;
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, Player player, String message, MessagePrompt messagePrompt) {
        // Set options based on type
        // Cancel
        if (message.equalsIgnoreCase("cancel")) return null;

        // Simple single value
        try {
            if (type == ConfigType.SET_NAME) {
                // Remap reward
                RewardsController.INSTANCE.getLoadedRewards().remove(reward.getName());
                reward.setName(message);
                RewardsController.INSTANCE.getLoadedRewards().put(reward.getName(), reward);
            } else if (type == ConfigType.SET_TITLE) {
                reward.setTitle(new Message(message));
            } else if (type == ConfigType.SET_SUBTITLE) {
                reward.setSubtitle(new Message(message));
            } else if (type == ConfigType.SET_CHANCE) {
                // Parse into double
                double value = Double.parseDouble(message);
                reward.setChance(value);
            } else if (type == ConfigType.SET_EXP) {
                int value = Integer.parseInt(message);
                reward.setExperience(value);
            } else if (type == ConfigType.SET_SOUND) {
                Sound value = Sound.valueOf(message.toUpperCase());
                reward.setSound(value);
            } else if (type == ConfigType.SET_VANILLA_REWARDS) {
                boolean value = Boolean.parseBoolean(message);
                reward.setVanillaRewards(value);
            } else if (type == ConfigType.SET_PERMISSION) {
                reward.setPermission(message);
            }

            // Now set lists
            else if (type == ConfigType.SET_COMMANDS) {
                reward.getCommands().add(message);
            } else if (type == ConfigType.SET_MESSAGES) {
                reward.getMessages().add(message);
            } else if (type == ConfigType.SET_BROADCASTS) {
                reward.getBroadcasts().add(message);
            } else if (type == ConfigType.SET_WORLDS) {
                reward.getWorlds().add(message);
            } else if (type == ConfigType.SET_REGIONS) {
                reward.getRegions().add(message);
            } else if (type == ConfigType.SET_BLOCKED_REGIONS) {
                reward.getBlockedRegions().add(message);
            }
        } catch (Exception ex) {
            // If fails conversion, just re prompt them
            new Message("&cEntered an invalid value").sendMessage(player);
            return messagePrompt;
        }

        // Open back up interface
        new ConfigureUI(type, reward).open(player);

        // Return null as we just want to terminate
        return null;
    }
}
