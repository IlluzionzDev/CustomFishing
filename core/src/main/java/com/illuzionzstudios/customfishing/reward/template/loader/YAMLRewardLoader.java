/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.template.loader;

import com.illuzionzstudios.core.plugin.IlluzionzPlugin;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.reward.template.AbstractRewardTemplate;
import com.illuzionzstudios.customfishing.reward.template.defaults.FoodDefaultTemplate;
import com.illuzionzstudios.customfishing.reward.template.yaml.YAMLRewardTemplate;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.util.*;

/**
 * Load our YAML templates
 */
@RequiredArgsConstructor
public class YAMLRewardLoader implements AbstractRewardLoader {

    /**
     * Directory to load files
     */
    public final String directory;

    @Override
    public Map<String, AbstractRewardTemplate> loadTemplates() {
        Map<String, AbstractRewardTemplate> templates = new HashMap<>();

        // Reward directory
        File dir = new File(IlluzionzPlugin.getInstance().getDataFolder().getPath() + File.separator + directory);
        File[] files = dir.listFiles();

        // No files in directory
        if (files == null || files.length == 0 || !dir.exists()) {
            // Save default rewards
            new FoodDefaultTemplate(directory).save();

            // Rerun as we set some defaults
            return loadTemplates();
        }

        // Go through files
        for (File file : files) {
            // Get name without extension
            String name = file.getName().split("\\.")[0];

            // Add to cache
            templates.put(name, new YAMLRewardTemplate(name, directory));
        }

        return templates;
    }
}
