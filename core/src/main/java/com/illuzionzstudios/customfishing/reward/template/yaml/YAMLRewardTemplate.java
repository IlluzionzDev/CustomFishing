/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.template.yaml;

import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.core.bukkit.util.ItemStackUtil;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.template.AbstractRewardTemplate;
import com.illuzionzstudios.customfishing.reward.template.RewardLoadException;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * A reward loaded from a YAMl file
 */
public class YAMLRewardTemplate implements AbstractRewardTemplate {

    /**
     * Config to load data from
     */
    protected final Config config;

    /**
     * Name of the reward
     */
    @Getter
    private String name;

    /**
     * @param fileName name of reward file in '/rewards'
     * @param directory The directory of the template
     */
    public YAMLRewardTemplate(String fileName, String directory) {
        // Load config
        this.config = new Config(CustomFishing.getInstance(), "/" + directory, fileName + ".yml");

        // Load the config
        this.name = fileName;
    }

    @Override
    public FishingReward create() throws RewardLoadException {
        // Save before creating
        save();

        // Lets try build the reward
        FishingReward.FishingRewardBuilder builder = FishingReward.builder();

        // Cause to send if fails
        String cause = "Failed to load reward file";

        // If fails throws error with cause message
        // set before trying to set builder
        try {
            // Load everything
            cause = "Could not load name";
            builder.name(config.getString("Name"));
            // Update local name
            this.name = config.getString("Name");

            cause = "Could not load commands";
            builder.commands(config.getStringList("Commands"));

            ArrayList<ItemStack> items = new ArrayList<>();
            // Parse custom items
            for (String item : config.getStringList("Items")) {
                // Deserialize item stack
                ItemStack stack = ItemStackUtil.deserialize(item);
                if (stack != null) {
                    items.add(stack);
                }
            }
            // Finally add item reward
            builder.items(items);

            cause = "Could not load messages";
            builder.messages(config.getStringList("Messages"));

            cause = "Could not load broadcasts";
            builder.broadcasts(config.getStringList("Broadcasts"));

            cause = "Could not load title";
            builder.title(new Message(config.getString("Title")));

            cause = "Could not load sub title";
            builder.subtitle(new Message(config.getString("Sub Title")));

            cause = "Could not load chance";
            builder.chance(config.getDouble("Chance"));

            cause = "Could not load if to give vanilla rewards";
            builder.vanillaRewards(config.getBoolean("Vanilla Rewards"));

            cause = "Could not load exp to give";
            builder.experience(config.getInt("Exp Amount"));

            // Set sound
            cause = "Sound " + config.getString("Sound") + " is not valid";
            try {
                Sound sound = Sound.valueOf(config.getString("Sound"));
                builder.sound(sound);
            } catch (Exception e) {
                builder.sound(null);
            }

            cause = "Could not load permission";
            builder.permission(config.getString("Requirements.Permission"));

            cause = "Could not load worlds";
            builder.worlds(config.getStringList("Requirements.Worlds"));

            cause = "Could not load regions";
            builder.regions(config.getStringList("Requirements.Regions"));

            cause = "Could not load blocked regions";
            builder.blockedRegions(config.getStringList("Requirements.BlockedRegions"));
        } catch (Exception ex) {
            ex.printStackTrace();
            // If exception throw load exception
            throw new RewardLoadException(cause, name);
        }

        return builder.build();
    }

    @Override
    public Config getTemplateFile() {
        return this.config;
    }

    @Override
    public boolean save() {
        this.config.load();
        this.config.saveChanges();
        return true;
    }
}
