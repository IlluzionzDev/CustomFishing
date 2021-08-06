package com.illuzionzstudios.customfishing.command;

import com.illuzionzstudios.customfishing.command.sub.AdminCommand;
import com.illuzionzstudios.customfishing.command.sub.RewardsCommand;
import com.illuzionzstudios.customfishing.command.sub.SaveCommand;
import com.illuzionzstudios.mist.command.SpigotCommandGroup;
import com.illuzionzstudios.mist.command.type.ReloadCommand;

/**
 * Main command
 */
public class CustomFishingCommand extends SpigotCommandGroup {

    @Override
    public void registerSubcommands() {
        registerSubCommand(new ReloadCommand());
        registerSubCommand(new AdminCommand());
        registerSubCommand(new RewardsCommand());
        registerSubCommand(new SaveCommand());
    }

}
