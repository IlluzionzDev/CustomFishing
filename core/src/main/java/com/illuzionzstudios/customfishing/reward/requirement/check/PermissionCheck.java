package com.illuzionzstudios.customfishing.reward.requirement.check;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.requirement.Check;
import org.bukkit.entity.Player;

/**
 * Check if they have the required permission for a reward
 */
public class PermissionCheck implements Check {

    @Override
    public boolean processCheck(Player player, FishingReward reward) {
        String permission = reward.getPermission();

        // If null is allowed
        if (permission == null) return true;

        // Simple check for if player has permission or is op
        return permission.trim().equals("") || player.hasPermission(permission) || player.isOp();
    }
}
