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
import com.illuzionzstudios.customfishing.reward.template.yaml.DefaultRewardTemplate;

import java.util.Arrays;

public class FoodDefaultTemplate extends DefaultRewardTemplate {

    /**
     * @param directory The directory of the template
     */
    public FoodDefaultTemplate(String directory) {
        super("food", directory);
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

        this.config.set("Name", "Food");
        this.config.set("Commands", Arrays.asList("give %player% cooked_beef 64"));
        this.config.set("Messages", Arrays.asList("&a&l(!) &aYou found &a&l64 Steak!"));
        this.config.set("Broadcast", false);
        this.config.set("Broadcasts", Arrays.asList(""));
        this.config.set("Title Enabled", true);
        this.config.set("Title", "&a&lYou found a reward!");
        this.config.set("Sub Title", "");
        this.config.set("Chance", 50);
        this.config.set("Vanilla Rewards", false);
        this.config.set("Exp Amount", 6);
        this.config.set("Sound", soundName);
        this.config.set("Requirements.Permission", "default");
        this.config.set("Requirements.Worlds", Arrays.asList("all"));
        this.config.set("Requirements.Regions", Arrays.asList("global"));
    }
}
