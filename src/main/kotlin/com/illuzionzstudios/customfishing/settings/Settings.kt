package com.illuzionzstudios.customfishing.settings

import com.illuzionzstudios.mist.config.ConfigSetting
import com.illuzionzstudios.mist.config.PluginSettings
import com.illuzionzstudios.mist.plugin.SpigotPlugin

class Settings(plugin: SpigotPlugin) : PluginSettings(plugin) {

    override fun loadSettings() {
    }

    companion object {

        /**
         * The chance of finding a custom reward
         */
        var REWARD_CHANCE: ConfigSetting = GENERAL_GROUP.create(
            "fishing.reward-chance", 100.0,
            "The chance of finding a custom reward while fishing", "can be a decimal number."
        )

        /**
         * Amount of EXP rewarded from fishing when no custom reward
         */
        var EXP_REWARD: ConfigSetting = GENERAL_GROUP.create(
            "fishing.exp-reward",
            "1 to 6",
            "Amount of experience to give the player when they HAVEN'T found a custom reward.",
            "X to Y indicates a range between two numbers.",
            "0 indicates to not reward exp."
        )

        var MIN_WAIT_TIME: ConfigSetting = GENERAL_GROUP.create(
            "fishing.min-wait-time",
            100,
            "The minimum amount of ticks to wait for a bite. This is before processing lure and other things",
            "REQUIRES 1.16+"
        )

        var MAX_WAIT_TIME: ConfigSetting = GENERAL_GROUP.create(
            "fishing.max-wait-time",
            600,
            "The maximum amount of ticks to wait for a bite. This is before processing lure and other things",
            "REQUIRES 1.16+"
        )

        var REWARD_STARTUP_LOG: ConfigSetting = GENERAL_GROUP.create(
            "fishing.reward-startup-log",
            true,
            "Set to \"false\" to disable the server from showing all rewards that are successfully loaded on startup",
            "set to \"true\" by default"
        )
    }
}