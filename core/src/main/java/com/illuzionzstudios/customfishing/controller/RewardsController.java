package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.loader.FishingRewardLoader;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.config.YamlConfig;
import com.illuzionzstudios.mist.config.serialization.loader.DirectoryLoader;
import com.illuzionzstudios.mist.controller.PluginController;
import com.illuzionzstudios.mist.util.LootTable;
import lombok.Getter;

import java.util.HashMap;

/**
 * Actual handling loading all rewards
 */
public enum RewardsController implements PluginController<CustomFishing> {
    INSTANCE;

    /**
     * Loot table of rewards
     */
    private final LootTable<FishingReward> lootTable = new LootTable<>();

    /**
     * Map rewards to their names
     */
    @Getter
    private HashMap<String, FishingReward> loadedRewards;

    @Override
    public void initialize(CustomFishing customFishing) {
        // Clear our already loaded data if any
        this.loadedRewards = new HashMap<>();
        this.lootTable.clear();

        DirectoryLoader<FishingRewardLoader> directoryLoader = new DirectoryLoader<>(FishingRewardLoader.class, "rewards");

        // Only load defaults if no files to load
        if (directoryLoader.getLoaders().isEmpty()) {
            // Example
            YamlConfig.loadInternalYaml(customFishing, "rewards", "demo_reward.yml");
            // Now load
            directoryLoader.load();
        }

        directoryLoader.getLoaders().forEach(loader -> {
            try {
                FishingReward reward = loader.getObject();
                this.loadedRewards.put(reward.getName(), reward);

                // Log loaded file
                Logger.info("Loaded reward " + reward.getName() + " from file " + loader.getName() + ".yml");
            } catch (final Throwable ex) {
                // Throw trace
                Logger.displayError(ex, "Couldn't load reward from file " + loader.getName() + ".yml");
            }
        });

        // Load chance sum
        if (loadedRewards.isEmpty()) {
            return;
        }

        for (FishingReward reward : loadedRewards.values()) {
            lootTable.addLoot(reward, reward.getChance());
        }
    }

    @Override
    public void stop(CustomFishing customFishing) {
    }

    public FishingReward pickReward() {
        if (loadedRewards.isEmpty()) {
            // End up returning null if no loaded rewards.
            // Will be dealt with elsewhere
            return null;
        }

        try {
            return lootTable.pick();
        } catch (Exception ex) {
            return null;
        }
    }
}
