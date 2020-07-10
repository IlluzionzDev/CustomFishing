/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.customfishing.reward.ui.AdminUI;
import com.illuzionzstudios.customfishing.reward.ui.ViewRewardsUI;
import com.illuzionzstudios.mist.command.SpigotSubCommand;

/**
 * Quickly open UI to view all loaded rewards
 */
public class RewardsCommand extends SpigotSubCommand {

    public RewardsCommand() {
        super("rewards", "list");

        setDescription("View all loaded rewards");
    }

    @Override
    protected void onCommand() {
        checkConsole();
        new ViewRewardsUI(new AdminUI()).show(getPlayer());
    }
}
