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
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */

public class WorldGuardCheck_1_13_R1 implements IWorldGuardCheck {

    @Override
    public boolean playerInRegion(String regionName, Player player) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        // BukkitAdapter turns normal world into WorldGuard world
        RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld())); // Get regions from player world

        // Make sure world has regions and region data isn't null
        if (regions != null) {

            Location playerPos = player.getLocation();
            // Check if region contains where the player is so hence they will be in this region
            ApplicableRegionSet regionSet = regions.getApplicableRegions(BukkitAdapter.asBlockVector(playerPos));

            for (ProtectedRegion checkRegion : regionSet) {
                if (checkRegion.getId().equalsIgnoreCase(regionName)) {
                    return true; // Set inRegion to true if they are in the region
                }
            }
        }

        return false;
    }

}
