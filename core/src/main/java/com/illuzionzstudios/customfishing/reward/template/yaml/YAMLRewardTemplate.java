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

import com.illuzionzstudios.compatibility.ServerVersion;
import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.FishingRewardBuilder;
import com.illuzionzstudios.customfishing.reward.template.AbstractRewardTemplate;
import com.illuzionzstudios.customfishing.reward.template.RewardLoadException;
import org.bukkit.Sound;

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
    private String name;

    /**
     * @param fileName name of reward file in '/rewards'
     * @param directory The directory of the template
     */
    public YAMLRewardTemplate(String fileName, String directory) {
        // Load config
        this.config = new Config(CustomFishing.getInstance(), "/" + directory, fileName + ".yml");

        // Load the config
        this.config.load();
        this.name = fileName;
        this.config.saveChanges();
    }

    @Override
    public FishingReward create() throws RewardLoadException {
        // Lets try build the reward
        FishingRewardBuilder builder = new FishingRewardBuilder();

        // Cause to send if fails
        String cause = "Failed to load reward file";

        // If fails throws error with cause message
        // set before trying to set builder
        try {
            // Load everything

            cause = "Could not load name";
            builder.setName(config.getString("Name"));
            // Update local name
            this.name = config.getString("Name");

            cause = "Could not load commands";
            builder.setCommands(config.getStringList("Commands"));

            cause = "Could not load messages";
            builder.setMessages(config.getStringList("Messages"));

            cause = "Could not load if to broadcast";
            builder.setBroadcastEnabled(config.getBoolean("Broadcast"));

            cause = "Could not load broadcasts";
            builder.setBroadcasts(config.getStringList("Broadcasts"));

            cause = "Could not load if to send titles";
            builder.setTitleEnabled(config.getBoolean("Title Enabled"));

            cause = "Could not load title";
            builder.setTitle(new Message(config.getString("Title")));

            cause = "Could not load sub title";
            builder.setSubTitle(new Message(config.getString("Sub Title")));

            cause = "Could not load chance";
            builder.setChance(config.getDouble("Chance"));

            cause = "Could not load if to give vanilla rewards";
            builder.setVanillaRewards(config.getBoolean("Vanilla Rewards"));

            cause = "Could not load exp to give";
            builder.setExperience(config.getInt("Exp Amount"));

            // Set sound
            cause = "Sound " + config.getString("Sound") + " is not valid";
            Sound sound = Sound.valueOf(config.getString("Sound"));
            builder.setSound(sound);

            cause = "Could not load permission";
            builder.setPermission("customfishing." + config.getString("Requirements.Permission"));

            cause = "Could not load worlds";
            builder.setWorlds(config.getStringList("Requirements.Worlds"));

            cause = "Could not load regions";
            builder.setRegions(config.getStringList("Requirements.Regions"));
        } catch (Exception ex) {
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
    public void save() {
        this.config.saveChanges();
    }
}
