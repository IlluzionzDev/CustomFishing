package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.customfishing.reward.ui.AdminUI;
import com.illuzionzstudios.customfishing.reward.ui.ViewRewardsUI;
import com.illuzionzstudios.mist.command.SpigotSubCommand;
import com.illuzionzstudios.mist.command.response.ReturnType;

/**
 * Quickly open UI to view all loaded rewards
 */
public class RewardsCommand extends SpigotSubCommand {

    public RewardsCommand() {
        super("rewards", "list");

        setDescription("View all loaded rewards");
    }

    @Override
    protected ReturnType onCommand() {
        checkConsole();
        new ViewRewardsUI(new AdminUI()).show(getPlayer());

        return ReturnType.SUCCESS;
    }
}
