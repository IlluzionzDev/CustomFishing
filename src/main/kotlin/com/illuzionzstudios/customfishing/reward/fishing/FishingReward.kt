package com.illuzionzstudios.customfishing.reward.fishing

import com.cryptomorin.xseries.XSound
import com.illuzionzstudios.customfishing.reward.EventReward
import com.illuzionzstudios.customfishing.reward.fishing.item.FishingItem
import com.illuzionzstudios.mist.config.locale.MistString
import com.illuzionzstudios.mist.random.RandomNumberGenerator
import com.illuzionzstudios.mist.requirement.PlayerRequirement
import com.illuzionzstudios.mist.util.MathUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerFishEvent

/**
 * A fishing reward that can be caught in the water
 */
class FishingReward : EventReward<PlayerFishEvent> {

    /**
     * Name of the reward
     */
    var name: String? = null

    /**
     * Commands to execute
     */
    var commands: List<String>? = ArrayList()

    /**
     * Custom items to give. Built in the reward loader
     */
    var items: List<FishingItem>? = ArrayList()

    /**
     * Messages to send
     */
    var messages: List<MistString>? = ArrayList()

    /**
     * Broadcast messages to send
     */
    var broadcasts: List<MistString>? = ArrayList()

    /**
     * Title message
     */
    var title: MistString? = null

    /**
     * Subtitle message
     */
    var subtitle: MistString? = null

    /**
     * Chance to find this reward
     */
    var chance = 0.0

    /**
     * If default vanilla rewards are enabled
     */
    var vanillaRewards = false

    /**
     * Experience to give the player in a range. E.g "1 to 6"
     */
    var experienceRange: String? = null

    /**
     * Sound to play
     */
    var sound: XSound? = null

    /**
     * Requirements for this reward
     */
    var requirements: List<PlayerRequirement>? = ArrayList()

    override fun reward(player: Player, event: PlayerFishEvent) {
        // Get global reward placeholders
        val rewardPlaceholders: MutableMap<String, Any> = HashMap()
        rewardPlaceholders["player"] = player.name

        // Try reward actual items (commands and items)
        commands?.forEach {
            var replacer = MistString(it)
            replacer = replacer.toString(rewardPlaceholders)
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replacer.toString())
        }

        items?.forEach {
            if (MathUtil.chance(it.chance)) it.givePlayer(player)
        }

        // Exp and vanilla rewards
        event.expToDrop = RandomNumberGenerator.parse(this.experienceRange).generate().toInt()
        if (!vanillaRewards) event.caught?.remove()

        // Messages and cosmetics
        this.sound?.play(player.location)
        title?.sendTitle(player, subtitle)

        if (messages?.get(0).toString().isNotEmpty()) {
            messages?.forEach { message ->
                val toSend = message.toString(rewardPlaceholders)
                toSend.sendMessage(player)
            }
        }

        if (broadcasts?.get(0).toString().isNotEmpty()) {
            broadcasts?.forEach { broadcast ->
                val toSend = broadcast.toString(rewardPlaceholders)
                Bukkit.getOnlinePlayers().forEach {
                    toSend.sendMessage(it)
                }
            }
        }

    }
}