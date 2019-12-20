package com.illuzionzstudios.customfishing.command;

import com.illuzionzstudios.core.bukkit.command.type.PlayerCommand;
import com.illuzionzstudios.core.config.Config;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.struct.Permission;

/**
 * Created by Illuzionz on 12 2019
 */

/**
 * Main command
 */
public class CustomFishingCommand extends PlayerCommand {

    private CustomFishing plugin;

    public CustomFishingCommand(CustomFishing plugin) {
        super("customfishing", "cfishing", "customf");
        this.plugin = plugin;

        minArgs = 1;
        notEnoughArgsMsg = "&c/customfishing rewards|reload|help";
    }

    @Override
    public void onCommand(String s, String[] strings) {
        sub("rewards", Permission.REWARDS, player -> {
            player.sendMessage(" ");
            plugin.getLocale().getMessage("rewards.list.header").sendMessage(player);
            RewardsController.INSTANCE.getLoadedRewards().forEach(reward -> {
                plugin.getLocale().getMessage("rewards.list.item")
                        .processPlaceholder("reward", reward.getName()).sendMessage(player);
            });
            player.sendMessage(" ");
        });

        sub("reload", Permission.RELOAD, player -> {
            plugin.reloadConfig();
            plugin.getLocale().getMessage("general.reload").sendPrefixedMessage(player);
        });
    }

    @Override
    public boolean isPublic() {
        return false;
    }
}
