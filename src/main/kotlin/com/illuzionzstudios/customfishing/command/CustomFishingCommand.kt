package com.illuzionzstudios.customfishing.command

import com.illuzionzstudios.mist.command.SpigotCommandGroup
import com.illuzionzstudios.mist.command.SpigotSubCommand
import com.illuzionzstudios.mist.command.response.ReturnType
import com.illuzionzstudios.mist.command.type.ReloadCommand

class CustomFishingCommand : SpigotCommandGroup() {

    override fun registerSubcommands() {
        registerSubCommand(ReloadCommand())
    }
}