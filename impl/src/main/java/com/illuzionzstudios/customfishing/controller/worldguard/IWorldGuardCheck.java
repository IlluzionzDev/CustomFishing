package com.illuzionzstudios.customfishing.controller.worldguard;

import org.bukkit.Location;

/**
 * Implemented by different versions
 */
public interface IWorldGuardCheck {

    /**
     * Check if location is in a region
     *
     * @param regionName The region name
     * @param location   The location to check
     * @return If they're in the region
     */
    boolean locationInRegion(String regionName, Location location);

}