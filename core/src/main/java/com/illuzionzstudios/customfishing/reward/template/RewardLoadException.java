/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.template;

import com.illuzionzstudios.core.util.Logger;
import lombok.Getter;

/**
 * Thrown when there is an error loading rewards
 */
public class RewardLoadException extends Exception {

    /**
     * Message or cause of error
     */
    @Getter
    private String message;

    /**
     * If a fatal error occurred
     */
    @Getter
    private final boolean fatal;

    /**
     * Debug reward loading
     */
    private final boolean DEBUG = true;

    /**
     * Reward file name
     */
    private final String rewardName;

    /**
     * @param message Cause of failed loading
     * @param rewardName The name of the reward failing to load
     * @param fatal If fatal error so need to log
     */
    public RewardLoadException(String message, String rewardName, boolean fatal) {
        this.message = message;
        this.rewardName = rewardName;
        this.fatal = fatal;
    }

    public RewardLoadException(String message, String rewardName) {
        this(message, rewardName, false);
    }

    /**
     * Request to print error
     */
    @Override
    public void printStackTrace() {
        // Log depending on debug
        if (DEBUG || isFatal()) {
            super.printStackTrace();
        }

        // Log pretty
        Logger.severe("------------------------------");
        Logger.severe("Encountered an exception!");
        Logger.severe(" ");
        Logger.severe("Error occurred loading file " + rewardName + ".yml");
        Logger.severe(message);
        Logger.severe("------------------------------");
    }

}
