package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.core.bukkit.controller.BukkitController;
import com.illuzionzstudios.core.chance.LootTable;
import com.illuzionzstudios.core.compatibility.ServerVersion;
import com.illuzionzstudios.core.config.Config;
import com.illuzionzstudios.core.config.ConfigSection;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.loot.FishingReward;
import com.illuzionzstudios.customfishing.loot.FishingRewardBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Illuzionz on 12 2019
 */
public enum RewardsController implements BukkitController<CustomFishing> {
    INSTANCE;

    @Getter
    private List<FishingReward> loadedRewards;

    /**
     * Loottable of rewards
     */
    private LootTable<FishingReward> lootTable = new LootTable<>();

    /**
     * Rewards config
     */
    private Config config;

    @Override
    public void initialize(CustomFishing customFishing) {
        config = customFishing.getExtraConfig().get(0);

        loadedRewards = new ArrayList<>();

        // Load rewards into memory
        if (config.isConfigurationSection("Rewards")) {
            for (ConfigSection section : config.getSections("Rewards")) {
                // Make sure config values are valid
                if (Sound.valueOf(section.getString("Sound")) == null) {
                    Logger.severe("Sound " + section.getString("Sound") + " is not valid for reward " + section.getNodeKey());
                    Logger.severe("The sound is not valid or is not available on your server version " + ServerVersion.getServerVersionString());
                    return;
                }

                loadedRewards.add(new FishingRewardBuilder()
                        .setName(section.getNodeKey())
                        .setCommands(section.getStringList("Commands"))
                        .setMessages(section.getStringList("Messages"))
                        .setBroadcastEnabled(section.getBoolean("Broadcast"))
                        .setBroadcasts(section.getStringList("Broadcasts"))
                        .setTitleEnabled(section.getBoolean("Title Enabled"))
                        .setTitle(new Message(section.getString("Title")))
                        .setSubTitle(new Message(section.getString("Sub Title")))
                        .setChance(section.getDouble("Chance"))
                        .setVanillaRewards(section.getBoolean("Vanilla Rewards"))
                        .setExperience(section.getInt("Exp Amount"))
                        .setSound(Sound.valueOf(section.getString("Sound")))

                        .setPermission("customfishing." + section.getString("Requirements.Permission"))
                        .setWorlds(section.getStringList("Requirements.Worlds"))
                        .setRegions(section.getStringList("Requirements.Regions")).build());

                Logger.info("Loaded reward '" + section.getNodeKey() + "'");
            }
        }


        // Load chance sum
        if (loadedRewards.isEmpty()) {
            return;
        }

        for (FishingReward reward : loadedRewards) {
            lootTable.addLoot(reward, reward.getChance());
        }
    }

    @Override
    public void stop(CustomFishing customFishing) {
    }

    public FishingReward pickReward() {
        if (loadedRewards.isEmpty() || loadedRewards == null) {
            // End up returning null if no loaded rewards.
            // Will be dealt with elsewhere
            return null;
        }

        return lootTable.pick();
    }
}
