/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.template.defaults;

import com.illuzionzstudios.compatibility.ServerVersion;
import com.illuzionzstudios.core.bukkit.util.ItemStackUtil;
import com.illuzionzstudios.customfishing.reward.template.yaml.DefaultRewardTemplate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class NothingDefaultTemplate extends DefaultRewardTemplate {

    /**
     * @param directory The directory of the template
     */
    public NothingDefaultTemplate(String directory) {
        super("nothing", directory);
    }

    @Override
    public void setDefaults() {
        // Changes based on version for default config
        String soundName = "";

        if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_13)) {
            soundName = "ENTITY_FIREWORK_ROCKET_LAUNCH";
        } else if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_12)) {
            soundName = "ENTITY_FIREWORK_LAUNCH";
        } else if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_8)) {
            soundName = "FIREWORK_LAUNCH";
        }

        this.config.setHeader("A custom reward that can be caught by fishing.",
                "This files defines all the options for a specific reward",
                "identified by 'Name'. Change the options here as you like",
                "to make your own reward. To add more than one catchable reward,",
                "copy this file and then change the options to your liking.",
                " ",
                "Any queries join our discord at https://discord.gg/DbJXzWq");

        this.config.set("Name", "Nothing",
                "The name or identifer of the reward. Make it something",
                "relating to what the reward is.");
        this.config.set("Commands", Arrays.asList("msg %player% sorry"),
                "A list of commands to run when this reward is caught.");

        // Items
        this.config.set("Items", Arrays.asList(""));

        this.config.set("Messages",  Arrays.asList("&c&l(!) &cYou found NOTHING!"),
                "A list of messages to send the player when this reward is caught.");
        this.config.set("Broadcasts", Arrays.asList(""),
                "Broadcasts to send if the above variable is set to true");
        this.config.set("Title", "&a&lYou found a reward!",
                "Title to send if 'Title Enabled' is set to true");
        this.config.set("Sub Title", "",
                "Sub Title to send if 'Title Enabled' is set to true");
        this.config.set("Chance", 50, "The chance of this reward being caught when the player",
                "finds a custom reward. Can be a decimal number");
        this.config.set("Vanilla Rewards", false,
                "If the player should still receive default minecraft rewards.",
                "This means fish, enchanted books, enchanted items etc.",
                "If set to false only the custom reward will be given");
        this.config.set("Exp Amount", 6,
                "The amount of experience to give the player",
                "when they find this reward");
        this.config.set("Sound", soundName,
                "The sound to play when this reward is caught");
        this.config.set("Requirements.Permission", "default",
                "The permission required to find this reward. Defined as",
                "customfishing.<value>. So the above would be the permission customfishing.default.",
                "Make sure to give this to players by default to receive rewards");
        this.config.set("Requirements.Worlds", Arrays.asList("all"),
                "The worlds this reward can be found in. Set",
                "to 'all' for all worlds");
        this.config.set("Requirements.Regions", Arrays.asList("global"),
                "The WorldGuard regions this reward can be found in.",
                "Set to 'global' for all regions",
                "MUST HAVE WORLDGUARD TO WORK");
        this.config.set("Requirements.BlockedRegions", Arrays.asList(""),
                "The WorldGuard regions this reward is blocked",
                "in. This means if the player is in this region,",
                "they can't find this reward");
    }

}
