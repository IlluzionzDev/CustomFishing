package com.illuzionzstudios.customfishing.reward.requirement;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import org.bukkit.entity.Player;

/**
 * A requirement check to see if a player
 * is able to do something
 */
public interface Check {

    /**
     * See if the check passes
     *
     * @param player Player checking for
     * @param reward Reward to process checks for
     * @return If check passes
     */
    boolean processCheck(Player player, FishingReward reward);

}
