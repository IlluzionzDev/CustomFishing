/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.ui;

import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.type.ConfigureOptionsUI;

import java.lang.reflect.Field;

/**
 * Implement to configure a {@link FishingReward}
 */
public class ConfigureRewardUI extends ConfigureOptionsUI<FishingReward> {

    public ConfigureRewardUI(FishingReward object) {
        super(object);
    }

    public ConfigureRewardUI(UserInterface parent, FishingReward object) {
        super(parent, object);
    }

    @Override
    protected void preSetValue(FishingReward object, Field field, Object value) {
        // Remove so we can reinsert
        if (field.getName().equalsIgnoreCase("name")) {
            RewardsController.INSTANCE.getLoadedRewards().remove(object.getName());
        }
    }

    @Override
    protected void onSetValue(FishingReward object, Field field, Object value) {
        // Reinsert back into memory
        if (field.getName().equalsIgnoreCase("name")) {
            RewardsController.INSTANCE.getLoadedRewards().put(value.toString(), object);
        }

        // We have to recreate menu with updated object
        new ConfigureRewardUI(getParent(), object).show(getViewer());
    }

}
