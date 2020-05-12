/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.config;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.function.Function;

/**
 * Different way to set reward options. Used for
 * the GUI and for parsing changing options
 */
@RequiredArgsConstructor
@Getter
public enum ConfigType {

    SET_NAME(Material.NAME_TAG, 0, FishingReward::getName),
    ;

    /**
     * Icon to display this option
     */
    private final Material icon;

    /**
     * Slot in the config GUI
     */
    private final int slot;

    /**
     * Function to return the current value for this
     * as a string
     */
    private final Function<FishingReward, String> currentValue;

    /**
     * @return The identifier for the config path
     */
    public String getConfigIdentifier() {
        return name().toLowerCase().replace("_", "-");
    }

    public String getCurrentValue(FishingReward reward) {
        return currentValue.apply(reward);
    }
}
