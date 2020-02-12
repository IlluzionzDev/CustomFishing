package com.illuzionzstudios.customfishing.command;

import com.illuzionzstudios.command.type.GlobalCommand;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.command.sub.ReloadCommand;
import com.illuzionzstudios.customfishing.command.sub.RewardsCommand;
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

    public CustomFishingCommand(CustomFishing plugin) {
        super("customfishing", "cfishing", "customf");

        minArgs = 1;
        setUsage("&c/customfishing rewards|reload|help");

        addSubCommand(new RewardsCommand(plugin));
        addSubCommand(new ReloadCommand(plugin));
    }

    @Override
    public void onCommand(String s, String[] strings) {
    }

    @Override
    public boolean isPublic() {
        return false;
    }
}
