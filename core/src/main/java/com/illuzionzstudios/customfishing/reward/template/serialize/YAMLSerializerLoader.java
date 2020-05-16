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
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;

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
    }

    private void purgeDirectory(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

    /**
     * Create serializers for every reward
     */
    public void saveRewards() {
        // Reward directory
        File dir = new File(IlluzionzPlugin.getInstance().getDataFolder().getPath() + File.separator + directory);

        // Clear directory so we can load rewards
        if (dir != null)
            purgeDirectory(dir);

        boolean loadedSuccessfully;

        for (FishingReward reward : RewardsController.INSTANCE.getLoadedRewards().values()) {
            loadedSuccessfully = new YAMLRewardSerializer(reward, reward.getName().toLowerCase().replace(" ", "_"), directory)
                    .save();

            // If didn't save, exit because we don't want to save bad rewards
            if (!loadedSuccessfully) return;
        }
    }

}
