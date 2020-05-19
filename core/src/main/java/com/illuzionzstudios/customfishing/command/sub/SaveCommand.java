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
import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.struct.Permission;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {

    public SaveCommand() {
        super("save", "s");

        this.requiredPermission = Permission.SAVE;
    }

    @Override
    public ReturnType onCommand(String s, String[] strings) {
        // Save all items into files
        try {
            new YAMLSerializerLoader("rewards").saveRewards();
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnType.ERROR;
        }

        CustomFishing.getInstance().getLocale().getMessage("general.save").sendPrefixedMessage(commandSender);

        return ReturnType.SUCCESS;
    }

    @Override
    public boolean isConsoleAllowed() {
        return true;
    }

}
