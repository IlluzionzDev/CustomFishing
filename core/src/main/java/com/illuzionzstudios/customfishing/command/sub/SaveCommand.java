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

import com.illuzionzstudios.customfishing.reward.template.serialize.YAMLSerializerLoader;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.command.SpigotSubCommand;
import com.illuzionzstudios.mist.config.locale.Message;

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
    protected void onCommand() {
        // Save all items into files
        try {
            new YAMLSerializerLoader("rewards").saveRewards();
        } catch (IOException e) {
            Logger.displayError(e, "Couldn't save rewards manually");
        }

        // Send message from locale
        new Message(FishingLocale.General.PLUGIN_SAVE).sendPrefixedMessage(getSender());
    }
}
