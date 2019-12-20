package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.core.bukkit.controller.BukkitController;
import com.illuzionzstudios.core.compatibility.ServerVersion;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.controller.worldguard.IWorldGuardCheck;
import com.illuzionzstudios.customfishing.controller.worldguard.WorldGuardCheck_1_12_R1;
import com.illuzionzstudios.customfishing.controller.worldguard.WorldGuardCheck_1_13_R1;
import com.illuzionzstudios.customfishing.loot.FishingReward;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuzionz on 12 2019
 */
public enum RequirementController implements BukkitController<CustomFishing> {
    INSTANCE;

    @Override
    public void initialize(CustomFishing customFishing) {
        // Checks for version
        if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_13)) {
            this.worldGuardCheck = new WorldGuardCheck_1_13_R1();

        } else if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_8)) {
            this.worldGuardCheck = new WorldGuardCheck_1_12_R1();
        }
    }

    @Override
    public void stop(CustomFishing customFishing) {

    }

    public static final String GLOBAL_WORLD = "all";
    public static final String GLOBAL_REGION = "global";

    /**
     * Used in multi-version support for WorldGuard
     */
    private IWorldGuardCheck worldGuardCheck;

    /**
     * Get a list of all rewards a player can find
     * Better than recursion as we only need to run a few checks rather than looping
     * a ton
     *
     * @param player Player to check
     * @return List of all fishingrewards
     */
    public List<FishingReward> getAvailableRewards(Player player) {
        List<FishingReward> rewards = new ArrayList<>();

        for (FishingReward reward : RewardsController.INSTANCE.getLoadedRewards()) {
            // Add reward if all checks pass
            if (processChecks(player, reward)) rewards.add(reward);
        }

        return rewards;
    }

    /**
     * Run checks if they can find the reward
     *
     * @param reward The reward to check
     * @return If all checks pass
     */
    public boolean processChecks(Player player, FishingReward reward) {
        String permission = reward.getPermission();
        List<String> worlds = reward.getWorlds();
        List<String> regions = reward.getRegions();

        // Run requirements checks
        // Will use recursion to rerun for new reward
        if (!player.hasPermission(permission) && !player.isOp()) return false;

        // Only check if worldguard enabled
        if (CustomFishing.getInstance().isWorldguardLoaded()) {
            // Check if in the region
            boolean inRegion = false;
            for (String region : regions) {
                if (worldGuardCheck.playerInRegion(region, player) || region.equalsIgnoreCase(GLOBAL_REGION)) {
                    inRegion = true;
                    break; // Don't need any more checks if true
                }
            }
            if (!inRegion) return false;
        }

        // Check if in worlds
        boolean inWorld = false;
        for (String world : worlds) {
            if (world.equalsIgnoreCase(GLOBAL_WORLD)) {
                inWorld = true;
                break;
            }

            if (Bukkit.getWorld(world) != null) {
                if (player.getWorld().equals(Bukkit.getWorld(world))) {
                    inWorld = true;
                    break; // Don't need any more checks if true
                }
            }

        }
        return inWorld; // Just return last check, as if it passes all have passed
    }
}
