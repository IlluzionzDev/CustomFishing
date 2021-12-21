package com.illuzionzstudios.customfishing.reward

import com.illuzionzstudios.customfishing.reward.fishing.FishingReward
import com.illuzionzstudios.customfishing.reward.fishing.loader.FishingRewardLoader
import com.illuzionzstudios.mist.Logger
import com.illuzionzstudios.mist.config.serialization.loader.DirectoryLoader
import com.illuzionzstudios.mist.controller.PluginController
import com.illuzionzstudios.mist.plugin.SpigotPlugin
import com.illuzionzstudios.mist.random.LootTable
import org.bukkit.entity.Player

object RewardController : PluginController {

    /**
     * All loaded fishing rewards
     */
    val loadedRewards: MutableSet<FishingReward> = HashSet()

    override fun initialize(plugin: SpigotPlugin) {
        // Load rewards
        DirectoryLoader(
            FishingRewardLoader::class.java,
            "rewards",
            listOf("demo_reward.yml")
        ).loaders.forEach {
            loadedRewards.add(it.`object`)
            Logger.info("Loading fishing reward '${it.`object`.name}'")
        }
    }

    override fun stop(plugin: SpigotPlugin) {
    }

    fun pickReward(player: Player): FishingReward? {
        // Load loot for player
        val lootTable: LootTable<FishingReward> = LootTable()
        loadedRewards.forEach {
            var passTests = true
            it.requirements?.forEach { if (!it.test(player)) passTests = false }
            if (passTests) {
                lootTable.addLoot(it, it.chance)
            }
        }

        return lootTable.pick()
    }
}