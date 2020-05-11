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

import com.illuzionzstudios.command.ReturnType;
import com.illuzionzstudios.command.type.AbstractCommand;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.ui.AdminUI;
import com.illuzionzstudios.customfishing.struct.Permission;

/**
 * Open up the admin gui to configure rewards
 */
public class AdminCommand extends AbstractCommand {

    public AdminCommand() {
        super("admin");

        this.requiredPermission = Permission.ADMIN;
    }

    @Override
    public ReturnType onCommand(String s, String[] strings) {
        new AdminUI().open(player);

        return ReturnType.SUCCESS;
    }

    @Override
    public boolean isConsoleAllowed() {
        return false;
    }

}
