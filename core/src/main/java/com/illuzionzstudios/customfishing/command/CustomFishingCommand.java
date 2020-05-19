package com.illuzionzstudios.customfishing.command;

import com.illuzionzstudios.command.ReturnType;
import com.illuzionzstudios.command.type.AbstractCommand;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.command.sub.AdminCommand;
import com.illuzionzstudios.customfishing.command.sub.ReloadCommand;
import com.illuzionzstudios.customfishing.command.sub.RewardsCommand;
import com.illuzionzstudios.customfishing.command.sub.SaveCommand;

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
public class CustomFishingCommand extends AbstractCommand {

    public CustomFishingCommand(CustomFishing plugin) {
        super("customfishing", "cfishing", "customf");

        addSubCommand(new AdminCommand());
        addSubCommand(new RewardsCommand());
        addSubCommand(new ReloadCommand(plugin));
        addSubCommand(new SaveCommand());
    }

    @Override
    public ReturnType onCommand(String s, String[] strings) {
        // By default execute help command

        // Help message
        Message help = CustomFishing.getInstance().getLocale().getMessage("general.help")
                .processPlaceholder("version", CustomFishing.getInstance().getPluginVersion());

        // Send to executor
        help.sendMessage(commandSender);

        return ReturnType.SUCCESS;
    }

    @Override
    public boolean isConsoleAllowed() {
        return true;
    }
}
