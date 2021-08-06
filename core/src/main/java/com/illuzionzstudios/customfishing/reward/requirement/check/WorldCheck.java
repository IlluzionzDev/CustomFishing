package com.illuzionzstudios.customfishing.reward.requirement.check;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.requirement.Check;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Check if in worlds
 */
public class WorldCheck implements Check {

    public static final String GLOBAL_WORLD = "all";

    @Override
    public boolean processCheck(Player player, FishingReward reward) {
        List<String> worlds = reward.getWorlds();

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
        return inWorld;
    }
}
