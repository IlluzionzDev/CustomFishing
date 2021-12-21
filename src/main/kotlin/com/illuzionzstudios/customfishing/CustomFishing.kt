package com.illuzionzstudios.customfishing

import com.illuzionzstudios.customfishing.command.CustomFishingCommand
import com.illuzionzstudios.customfishing.reward.RewardController
import com.illuzionzstudios.customfishing.reward.fishing.FishingController
import com.illuzionzstudios.customfishing.settings.Locale
import com.illuzionzstudios.customfishing.settings.Settings
import com.illuzionzstudios.mist.config.PluginSettings
import com.illuzionzstudios.mist.config.locale.PluginLocale
import com.illuzionzstudios.mist.plugin.SpigotPlugin
import org.bukkit.ChatColor

class CustomFishing() : SpigotPlugin() {

    override val pluginColor: ChatColor = ChatColor.LIGHT_PURPLE
    override val pluginId: Int = 53634
    override val pluginLocale: PluginLocale
        get() = Locale(this)
    override val pluginSettings: PluginSettings
        get() = Settings(this)

    override fun onPluginDisable() {

    }

    override fun onPluginEnable() {

    }

    override fun onPluginLoad() {

    }

    override fun onPluginPreEnable() {

    }

    override fun onPluginPreReload() {

    }

    override fun onPluginReload() {

    }

    override fun onRegisterReloadables() {
        registerMainCommand(CustomFishingCommand(), "customfishing", "fishing")
        reloadables.registerController(RewardController)
        reloadables.registerController(FishingController)
    }
}