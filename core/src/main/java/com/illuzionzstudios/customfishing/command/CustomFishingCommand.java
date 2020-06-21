/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
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
