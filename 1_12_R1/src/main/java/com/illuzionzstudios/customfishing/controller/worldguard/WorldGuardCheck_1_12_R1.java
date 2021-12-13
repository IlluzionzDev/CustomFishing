package com.illuzionzstudios.customfishing.controller.worldguard;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

/**
 * WorldGuard for 1.8-1.12
 */
public class WorldGuardCheck_1_12_R1 implements IWorldGuardCheck {

    @Override
    public boolean locationInRegion(String regionName, Location location) {
        WorldGuardPlugin guard = WGBukkit.getPlugin();
        RegionManager manager = guard.getRegionManager(location.getWorld());

        for (ProtectedRegion region : manager.getApplicableRegions(location)) {
            if (region.getId().equalsIgnoreCase(regionName)) return true;
        }

        return false;
    }
}
