package com.illuzionzstudios.customfishing.reward.fishing

import com.illuzionzstudios.customfishing.reward.RewardController
import com.illuzionzstudios.customfishing.settings.Settings
import com.illuzionzstudios.mist.compatibility.ServerVersion.V
import com.illuzionzstudios.mist.compatibility.ServerVersion.atLeast
import com.illuzionzstudios.mist.controller.PluginController
import com.illuzionzstudios.mist.plugin.SpigotPlugin
import com.illuzionzstudios.mist.random.RandomNumberGenerator
import com.illuzionzstudios.mist.util.MathUtil
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerFishEvent

object FishingController : PluginController {

    override fun initialize(plugin: SpigotPlugin) {
    }

    override fun stop(plugin: SpigotPlugin) {
    }

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        if (atLeast(V.v1_16)) {
            // Custom hook features
            val hook = event.hook
            hook.minWaitTime = Settings.MIN_WAIT_TIME.int
            hook.maxWaitTime = Settings.MAX_WAIT_TIME.int
        }

        // Detect if they catch a fish
        if (event.state == PlayerFishEvent.State.CAUGHT_FISH) {
            // Set experience default reward
            event.expToDrop = RandomNumberGenerator.parse(Settings.EXP_REWARD.getString("1 to 6")).generate().toInt()
            // Detect if they actually get a reward
            if (MathUtil.chance(Settings.REWARD_CHANCE.double))
                processRewards(event.player, event)
        }
    }

    /**
     * Process rewards for a player. All calculations
     * will be done here, and if null, will rerun until reward is picked.
     *
     * @param player Player to reward
     * @param event  The fishing event
     */
    private fun processRewards(player: Player, event: PlayerFishEvent) {
        // Try reward. Kotlin will handle null checks :)
        val reward: FishingReward? = RewardController.pickReward(player)
        reward?.reward(player, event)
    }
}