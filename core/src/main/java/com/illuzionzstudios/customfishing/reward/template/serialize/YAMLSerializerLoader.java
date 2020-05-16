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

import com.illuzionzstudios.core.plugin.IlluzionzPlugin;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Handle serializing all rewards
 */
public class YAMLSerializerLoader {

    /**
     * Directory to load files to
     */
    public final String directory;

    public YAMLSerializerLoader(String directory) throws IOException {
        this.directory = directory;

        // Reward directory
        File dir = new File(IlluzionzPlugin.getInstance().getDataFolder().getPath() + File.separator + directory);

        // Clear directory so we can load rewards
        FileUtils.cleanDirectory(dir);
    }

    /**
     * Create serializers for every reward
     */
    public void saveRewards() {
        RewardsController.INSTANCE.getLoadedRewards().forEach((name, reward) -> {
            new YAMLRewardSerializer(reward, reward.getName().toLowerCase().replace(" ", "_"), directory)
                    .save();
        });
    }

}
