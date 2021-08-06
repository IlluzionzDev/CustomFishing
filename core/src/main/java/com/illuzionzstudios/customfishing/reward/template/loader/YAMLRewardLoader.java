package com.illuzionzstudios.customfishing.reward.template.loader;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.template.AbstractRewardTemplate;
import com.illuzionzstudios.customfishing.reward.template.yaml.DefaultRewardTemplate;
import com.illuzionzstudios.customfishing.reward.template.yaml.YAMLRewardTemplate;
import com.illuzionzstudios.mist.Logger;
import lombok.Getter;

import java.io.File;
import java.util.*;

/**
 * Load our YAML templates
 */
public class YAMLRewardLoader implements AbstractRewardLoader {

    /**
     * Static list of all defaults to load
     */
    public static ArrayList<FishingReward> defaults = new ArrayList<>();

    /**
     * @param template Add a new default template
     */
    public static void addDefault(FishingReward template) {
        defaults.add(template);
    }

    /**
     * Indicates if to load our default rewards
     */
    public static boolean shouldLoadDefaults = false;

    /**
     * Loaded templates
     */
    @Getter
    public static Map<String, AbstractRewardTemplate> templates = new HashMap<>();

    /**
     * Clear template cache
     */
    public static void clear() {
        templates.clear();
    }

    /**
     * Directory to load files
     */
    public final String directory;

    public YAMLRewardLoader(String directory) {
        this.directory = directory;

        // Add defaults only when initialized

        // Reward directory
        File dir = new File(CustomFishing.getInstance().getDataFolder().getPath() + File.separator + directory);

        // No files in directory
        if (!dir.exists() || dir.listFiles() == null) {
            // Can't create directory, FATAL
            if (!dir.mkdirs()) return;
            Logger.info("Loading default rewards as /" + directory + " doesn't exist");

            shouldLoadDefaults = true;
        }
    }

    @Override
    public Map<String, AbstractRewardTemplate> loadTemplates() {
        // Reward directory
        File dir = new File(CustomFishing.getInstance().getDataFolder().getPath() + File.separator + directory);

        // Ensure exists
        if (dir.listFiles() == null || !dir.exists()) return templates;

        // Go through files
        for (File file : dir.listFiles()) {
            // Get name without extension
            String name = file.getName().split("\\.")[0];

            // Add to cache
            templates.put(name, new YAMLRewardTemplate(name, directory));
        }

        return templates;
    }
}
