package com.illuzionzstudios.customfishing.settings

import com.illuzionzstudios.mist.config.locale.PluginLocale
import com.illuzionzstudios.mist.plugin.SpigotPlugin

class Locale(plugin: SpigotPlugin): PluginLocale(plugin) {

    companion object {
        var GENERAL_PLUGIN_SAVE = createString("general.save", "&7Saved all rewards")
    }

}