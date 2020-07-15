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
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.Mist;
import com.illuzionzstudios.mist.config.locale.Message;
import com.illuzionzstudios.mist.controller.PluginController;
import com.illuzionzstudios.mist.util.LootTable;
import com.illuzionzstudios.mist.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Controls everything related to fishing
 */
public enum FishingController implements PluginController<CustomFishing>, Listener {
    INSTANCE;

    @Override
    public void initialize(CustomFishing customFishing) {
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
            event.setExpToDrop(Settings.Reward.EXP_REWARD.getInt());

            // Detect if they actually get a reward
            if (!MathUtil.chance(Settings.Reward.REWARD_CHANCE.getDouble())) return;
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
        List<ItemStack> items = reward.getItems();
        List<String> broadcasts = reward.getBroadcasts();

        boolean vanillaRewards = reward.isVanillaRewards();

        Message title = reward.getTitle();
        Message subtitle = reward.getSubtitle();

        Sound sound = reward.getSound() != null ? reward.getSound().parseSound() : null;

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
            title.setFadeIn(Settings.Title.TITLE_FADEIN.getInt());
            title.setStay(Settings.Title.TITLE_DISPLAY.getInt());
            title.setFadeOut(Settings.Title.TITLE_FADEOUT.getInt());

            // Send titles
            if (!title.getMessage().trim().equals("")) {
                // Null checking
                if (subtitle != null && !subtitle.getMessage().trim().equals("")) {
                    title.sendTitle(player, subtitle);
                } else {
                    title.sendTitle(player);
                }
            }
        }


        // Send messages
        if (messages != null)
        messages.forEach(msg -> player.sendMessage(Mist.colorize(msg)));

        // Send broadcasts
        if (broadcasts != null) {
            if (!broadcasts.isEmpty()) {
                broadcasts.forEach(msg -> {
                    if (msg.trim().equals("")) return;
                    msg = new Message(msg).processPlaceholder("player", player.getName()).getMessage();
                    Bukkit.getServer().broadcastMessage(Mist.colorize(msg));
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
                player.getInventory().addItem(item);
            });
        }
    }

}
