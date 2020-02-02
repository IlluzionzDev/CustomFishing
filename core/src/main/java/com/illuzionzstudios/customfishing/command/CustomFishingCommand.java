package com.illuzionzstudios.customfishing.command;

import com.illuzionzstudios.core.bukkit.command.type.GlobalCommand;
import com.illuzionzstudios.core.bukkit.command.type.PlayerCommand;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.struct.Permission;

/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
/**
 * Main command
 */
public class CustomFishingCommand extends GlobalCommand {

    private CustomFishing plugin;

    public CustomFishingCommand(CustomFishing plugin) {
        super("customfishing", "cfishing", "customf");
        this.plugin = plugin;

        minArgs = 1;
        setUsage("&c/customfishing rewards|reload|help");
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
