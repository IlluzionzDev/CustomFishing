package com.illuzionzstudios.customfishing.settings

import com.illuzionzstudios.mist.config.locale.PluginLocale
import com.illuzionzstudios.mist.plugin.SpigotPlugin

class Locale(plugin: SpigotPlugin): PluginLocale(plugin) {

    override fun loadLocale() {
    }

    companion object {
        var GENERAL_PLUGIN_SAVE = STARTUP_GROUP.create("general.save", "&7Saved all rewards")
    }

}