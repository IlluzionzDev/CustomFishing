package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.command.SpigotSubCommand;
import com.illuzionzstudios.mist.command.response.ReturnType;
import com.illuzionzstudios.mist.config.locale.Message;
import com.illuzionzstudios.mist.config.locale.PluginLocale;

import java.io.IOException;

/**
 * Save all the plugin rewards and data
 */
public class SaveCommand extends SpigotSubCommand {

    public SaveCommand() {
        super("save");

        setDescription("Manually save rewards to disk");
    }

    @Override
    protected ReturnType onCommand() {
        // Save all items into files
        try {
            new YAMLSerializerLoader("rewards").saveRewards();
        } catch (IOException e) {
            Logger.displayError(e, "Couldn't save rewards manually");
        }

        // Send message from locale
        PluginLocale.GENERAL_PLUGIN_PREFIX.concat(" " + FishingLocale.GENERAL_PLUGIN_SAVE).sendMessage(getSender());

        return ReturnType.SUCCESS;
    }
}
