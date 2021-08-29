package com.illuzionzstudios.customfishing.controller.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * WorldGuard for 1.12+
 */
public class WorldGuardCheck_1_13_R1 implements IWorldGuardCheck {

    @Override
    public boolean locationInRegion(String regionName, Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        // BukkitAdapter turns normal world into WorldGuard world
        RegionManager regions = container.get(BukkitAdapter.adapt(location.getWorld())); // Get regions from player world

        // Make sure world has regions and region data isn't null
        if (regions != null) {
            // Check if region contains where the player is so hence they will be in this region
            ApplicableRegionSet regionSet = regions.getApplicableRegions(BukkitAdapter.asBlockVector(location));

            for (ProtectedRegion checkRegion : regionSet) {
                if (checkRegion.getId().equalsIgnoreCase(regionName)) {
                    return true; // Set inRegion to true if they are in the region
                }
            }
        }

        return false;
    }
}
