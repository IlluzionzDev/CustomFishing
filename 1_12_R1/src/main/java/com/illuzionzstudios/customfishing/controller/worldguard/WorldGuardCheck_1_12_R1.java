package com.illuzionzstudios.customfishing.controller.worldguard;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
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
