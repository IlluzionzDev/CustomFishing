package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.customfishing.reward.ui.AdminUI;
import com.illuzionzstudios.mist.command.SpigotSubCommand;
import com.illuzionzstudios.mist.command.response.ReturnType;

/**
 * Open up the admin gui to configure rewards
 */
public class AdminCommand extends SpigotSubCommand {

    public AdminCommand() {
        super("config", "admin");

        setDescription("Open the GUI to configure rewards");
    }

    @Override
    protected ReturnType onCommand() {
        if (checkConsole())
            return ReturnType.PLAYER_ONLY;

        new AdminUI().show(getPlayer());

        return ReturnType.SUCCESS;
    }
}
