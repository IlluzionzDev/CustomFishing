package com.illuzionzstudios.customfishing.reward

import org.bukkit.entity.Player
import org.bukkit.event.Event

/**
 * An instance of a reward that triggers on a bukkit event
 */
interface EventReward<E : Event> {

    /**
     * Reward a player when the event triggers
     */
    fun reward(player: Player, event: E)

}