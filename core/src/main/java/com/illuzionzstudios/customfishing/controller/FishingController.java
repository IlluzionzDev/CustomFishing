package com.illuzionzstudios.customfishing.controller;

import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.compatibility.ServerVersion;
import com.illuzionzstudios.mist.controller.PluginController;
import com.illuzionzstudios.mist.util.LootTable;
import com.illuzionzstudios.mist.util.MathUtil;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

/**
 * Controls everything related to fishing
 */
public enum FishingController implements PluginController<CustomFishing>, Listener {
    INSTANCE;

    @Override
    public void initialize(final CustomFishing customFishing) {
    }

    @Override
    public void stop(final CustomFishing customFishing) {
    }

    /**
     * Used to detect when a fish is caught
     */
    @EventHandler
    public void onFish(final PlayerFishEvent event) {
        if (ServerVersion.atLeast(ServerVersion.V.v1_16)) {
            // Custom hook features
            FishHook hook = event.getHook();
//            hook.setMinWaitTime(Settings.MIN_WAIT_TIME.getInt());
//            hook.setMaxWaitTime(Settings.MAX_WAIT_TIME.getInt());
        }

        // Detect if they catch a fish
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            // Set experience default reward
            event.setExpToDrop(Settings.EXP_REWARD.getInt());

            // Detect if they actually get a reward
            if (!MathUtil.chance(Settings.REWARD_CHANCE.getDouble())) return;
            processRewards(event.getPlayer(), event);
        }
    }

    /**
     * Process rewards for a player. All calculations
     * will be done here, and if null, will rerun until reward is picked.
     *
     * @param player Player to reward
     * @param event  The fishing event
     */
    public void processRewards(final Player player, final PlayerFishEvent event) {
        // Null check
        if (RewardsController.INSTANCE.pickReward() == null) return;

        // Null checks
        if (RequirementController.INSTANCE.getAvailableRewards(player).isEmpty()) return;

        // Pick rewards from available rewards
        LootTable<FishingReward> availableRewards = new LootTable<>();
        for (FishingReward availableReward : RequirementController.INSTANCE.getAvailableRewards(player)) {
            availableRewards.addLoot(availableReward, availableReward.getChance());
        }

        FishingReward reward = availableRewards.pick();

        // Don't want a somehow null reward
        if (reward == null) return;

        // Invoke reward methods on reward
        reward.reward(player, event);
    }

}
