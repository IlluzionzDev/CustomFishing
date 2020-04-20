package com.illuzionzstudios.customfishing.controller;

/**
 * Created by Illuzionz on 12 2019
 */

import com.illuzionzstudios.chance.LootTable;
import com.illuzionzstudios.core.bukkit.controller.BukkitController;
import com.illuzionzstudios.core.locale.Locale;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.core.util.ChanceUtil;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.item.ItemReward;
import com.illuzionzstudios.customfishing.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

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
 * Controls everything related to fishing
 */
public enum FishingController implements BukkitController<CustomFishing>, Listener {
    INSTANCE;

    @Override
    public void initialize(CustomFishing customFishing) {
        Bukkit.getServer().getPluginManager().registerEvents(this, customFishing);
    }

    @Override
    public void stop(CustomFishing customFishing) {
    }

    /**
     * Used to detect when a fish is caught
     */
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        // Detect if they catch a fish
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            // Set experience default reward
            event.setExpToDrop(Settings.EXP_REWARD.getInt());

            // Detect if they actually get a reward
            if (!ChanceUtil.calculateChance(Settings.REWARD_CHANCE.getDouble())) return;
            processRewards(event.getPlayer(), event);
        }
    }

    /**
     * Process rewards for a player. All calculations
     * will be done here, and if null, will rerun until reward is picked.
     *
     * @param player Player to reward
     * @param event  The fishing event
     */
    public void processRewards(Player player, PlayerFishEvent event) {
        // Null check
        if (RewardsController.INSTANCE.pickReward() == null) return;

        // Null checks
        if (RequirementController.INSTANCE.getAvailableRewards(player).isEmpty()) return;

        // Pick rewards from available rewards
        LootTable<FishingReward> availableRewards = new LootTable<>();
        for (FishingReward availableReward : RequirementController.INSTANCE.getAvailableRewards(player)) {
            availableRewards.addLoot(availableReward, availableReward.getChance());
        }

        FishingReward reward = availableRewards.pick();

        // Don't want a somehow null reward
        if (reward == null) return;

        // Variables to use and check
        List<String> messages = reward.getMessages();
        List<String> commands = reward.getCommands();
        List<ItemReward> items = reward.getItems();
        List<String> broadcasts = reward.getBroadcasts();

        boolean shouldBroadcast = reward.isBroadcastEnabled();
        boolean vanillaRewards = reward.isVanillaRewards();

        boolean shouldSendTitle = reward.isTitleEnabled();
        Message title = reward.getTitle();
        Message subtitle = reward.getSubtitle();

        Sound sound = reward.getSound();

        int experience = reward.getExperience();

        // Set exp reward
        event.setExpToDrop(experience);

        // Play sound, no need for null check as was handled loading rewards
        if (sound != null) {
            player.playSound(player.getLocation(), sound, 10, 1);
        }

        // If should have default rewards
        if (!vanillaRewards) {
            if (event.getCaught() != null)
                event.getCaught().remove();
        }

        if (title != null) {
            // Set title times
            title.setFadeIn(Settings.TITLE_FADEIN.getInt());
            title.setStay(Settings.TITLE_DISPLAY.getInt());
            title.setFadeOut(Settings.TITLE_FADEOUT.getInt());

            // Send titles
            if (shouldSendTitle) {
                // Null checking
                if (subtitle != null) {
                    title.sendTitle(player, subtitle);
                } else {
                    title.sendTitle(player);
                }
            }
        }


        // Send messages
        if (messages != null)
        messages.forEach(msg -> player.sendMessage(Locale.color(msg)));

        // Send broadcasts
        if (shouldBroadcast) {
            if (broadcasts != null) {
                broadcasts.forEach(msg -> {
                    msg = new Message(msg).processPlaceholder("player", player.getName()).getMessage();
                    Bukkit.getServer().broadcastMessage(Locale.color(msg));
                });
            }
        }

        // Execute commands
        if (commands != null) {
            commands.forEach(command -> {
                // Do placeholders
                command = new Message(command).processPlaceholder("player", player.getName()).getMessage();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
            });
        }

        // Give custom items
        if (items != null) {
            items.forEach(item -> {
                // If chance for item
                if (ChanceUtil.calculateChance(item.getChance()))
                player.getInventory().addItem(item.get());
            });
        }
    }

}
