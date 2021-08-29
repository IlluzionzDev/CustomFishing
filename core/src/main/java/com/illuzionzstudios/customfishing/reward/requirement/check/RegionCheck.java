package com.illuzionzstudios.customfishing.reward.requirement.check;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.controller.RequirementController;
import com.illuzionzstudios.customfishing.controller.worldguard.IWorldGuardCheck;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.requirement.Check;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Check if player meets the region specifications
 */
public class RegionCheck implements Check {

    /**
     * If to allow all regions
     */
    public static final String GLOBAL_REGION = "global";

    @Override
    public boolean processCheck(Player player, FishingReward reward) {
        List<String> regions = reward.getRegions();
        List<String> blockedRegions = reward.getBlockedRegions();

        IWorldGuardCheck worldGuardCheck = RequirementController.INSTANCE.getWorldGuardCheck();

        // Only check if world guard enabled
        if (CustomFishing.isWorldguardLoaded()) {
            // Check if in the region
            boolean inRegion = false;
            for (String region : regions) {
                if (worldGuardCheck.locationInRegion(region, player.getLocation()) || region.equalsIgnoreCase(GLOBAL_REGION)) {
                    inRegion = true;
                    break; // Don't need any more checks if true
                }
            }

            // Check blocked regions
            for (String region : blockedRegions) {
                if (worldGuardCheck.locationInRegion(region, player.getLocation())) {
                    // Here if in blocked region
                    // Instantly set to false as technically
                    // not in region
                    inRegion = false;
                    break;
                }
            }
            return inRegion;
        }

        return true;
    }
}
