package com.illuzionzstudios.customfishing.controller.worldguard;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;

/**
 * Created by Illuzionz on 12 2019
 */
public class WorldGuardCheck_1_12_R1 implements IWorldGuardCheck {

    @Override
    public boolean playerInRegion(String regionName, Player player) {
        WorldGuardPlugin guard = WGBukkit.getPlugin();
        RegionManager manager = guard.getRegionManager(player.getWorld());

        for (ProtectedRegion region : manager.getApplicableRegions(player.getLocation())) {
            if (region.getId().equalsIgnoreCase(regionName)) return true;
        }

        return false;
    }
}
