package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.customfishing.controller.ConverterController;
import com.illuzionzstudios.customfishing.reward.ui.ViewRewardsUI;
import com.illuzionzstudios.mist.command.SpigotSubCommand;
import com.illuzionzstudios.mist.command.response.ReturnType;

/**
 * Quickly open UI to view all loaded rewards
 */
public class ConvertCommand extends SpigotSubCommand {

    public ConvertCommand() {
        super("convert");

        setDescription("Convert old reward files to new");
    }

    @Override
    protected ReturnType onCommand() {
        ConverterController.INSTANCE.convertAll();
        tell("&aConverting old files");
        tell("&aPlease reload the plugin");

        return ReturnType.SUCCESS;
    }
}
