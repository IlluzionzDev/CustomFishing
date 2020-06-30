/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.template.serialize;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.template.yaml.YAMLRewardTemplate;
import com.illuzionzstudios.mist.util.ItemStackUtil;

import java.util.ArrayList;

/**
 * Save rewards as a template to disk
 */
public class YAMLRewardSerializer extends YAMLRewardTemplate {

    /**
     * Reward to save
     */
    private FishingReward reward;

    /**
     * @param fileName  name of reward file in '/rewards'
     * @param directory The directory of the template
     */
    public YAMLRewardSerializer(FishingReward reward, String fileName, String directory) {
        super(fileName, directory);

        this.reward = reward;
    }

    /**
     * Save to disk
     */
    @Override
    public boolean save() {
        // Save to disk
        boolean successful = serialize();
        super.save();
        return successful;
    }

    public boolean serialize() {
        try {
            this.config.set("Name", reward.getName());

            // Serialize items
            ArrayList<String> itemBlobs = new ArrayList<>();
            reward.getItems().forEach(item -> {
                itemBlobs.add(ItemStackUtil.serialize(item));
            });
            this.config.set("Items", itemBlobs);

            this.config.set("Commands", reward.getCommands());

            this.config.set("Messages", reward.getMessages());

            this.config.set("Broadcasts", reward.getBroadcasts());

            this.config.set("Title", reward.getTitle() == null ? null : reward.getTitle().getUnformattedMessage());

            this.config.set("Sub Title", reward.getSubtitle() == null ? null : reward.getSubtitle().getUnformattedMessage());

            this.config.set("Chance", reward.getChance());

            this.config.set("Vanilla Rewards", reward.isVanillaRewards());

            this.config.set("Exp Amount", reward.getExperience());

            this.config.set("Sound", reward.getSound() == null ? null : reward.getSound().toString());

            this.config.set("Requirements.Permission", reward.getPermission());

            this.config.set("Requirements.Worlds", reward.getWorlds());

            this.config.set("Requirements.Regions", reward.getRegions());

            this.config.set("Requirements.BlockedRegions", reward.getBlockedRegions());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
