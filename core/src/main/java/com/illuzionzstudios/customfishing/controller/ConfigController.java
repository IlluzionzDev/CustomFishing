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

import com.illuzionzstudios.core.bukkit.controller.BukkitController;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.config.ConfigObject;
import com.illuzionzstudios.customfishing.reward.config.ConfigType;
import com.illuzionzstudios.customfishing.reward.ui.ConfigureRewardUI;
import com.illuzionzstudios.customfishing.reward.ui.ConfigureUI;
import com.illuzionzstudios.customfishing.reward.ui.ViewRewardsUI;
import com.illuzionzstudios.scheduler.MinecraftScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * Help with configuring rewards
 */
public enum ConfigController implements BukkitController<Plugin>, Listener {
    INSTANCE;

    /**
     * Active config sessions in chat
     */
    private HashMap<UUID, ConfigObject> activeSessions = new HashMap<>();


    @Override
    public void initialize(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void stop(Plugin plugin) {

    }

    /**
     * Add a player to the queue for configuring a reward
     *
     * @param reward Reward to configure
     * @param type Option to configure
     */
    public void prepareConfiguration(Player player, FishingReward reward, ConfigType type) {
        this.activeSessions.put(player.getUniqueId(), new ConfigObject(reward, type));

        // Send message to type message
        Message.of("rewards.config.enter-value").sendMessage(player);
    }

    /**
     * Handle chat messages
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID playerUuid = event.getPlayer().getUniqueId();

        if (this.activeSessions.containsKey(playerUuid)) {
            event.setCancelled(true);
            // Check config types
            ConfigObject config = this.activeSessions.get(playerUuid);
            FishingReward reward = config.getReward();

            switch (config.getType()) {
                case SET_NAME:
                    reward.setName(event.getMessage());
            }

            // Reopen UI
            MinecraftScheduler.get().synchronize(() -> {
                new ConfigureUI(config.getType(), reward).open(event.getPlayer());
            });
            this.activeSessions.remove(playerUuid);
        }
    }
}
