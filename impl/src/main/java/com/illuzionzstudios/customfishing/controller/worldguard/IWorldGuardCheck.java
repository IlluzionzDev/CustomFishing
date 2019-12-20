package com.illuzionzstudios.customfishing.controller.worldguard;

/**
 * Created by Illuzionz on 12 2019
 */

import org.bukkit.entity.Player;

/**
 * Implemented by different versions
 */
public interface IWorldGuardCheck {

    /**
     * Check if player is in a region
     *
     * @param regionName The region name
     * @param player The player to check
     * @return If they're in the region
     */
    boolean playerInRegion(String regionName, Player player);

}