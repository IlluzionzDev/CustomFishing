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
import lombok.RequiredArgsConstructor;

/**
 * Thrown when there is an error loading rewards
 */
@RequiredArgsConstructor
public class RewardLoadException extends Exception {

    /**
     * Message or cause of error
     */
    @Getter
    private final String message;

    /**
     * Reward file name
     */
    private final String rewardName;

    /**
     * Request to print error
     */
    @Override
    public void printStackTrace() {
        // Log pretty
        Logger.severe("------------------------------");
        Logger.severe("Encountered an exception!");
        Logger.severe(" ");
        Logger.severe("Error occurred loading file " + rewardName + ".yml");
        Logger.severe(message);
        Logger.severe("------------------------------");
    }

}
