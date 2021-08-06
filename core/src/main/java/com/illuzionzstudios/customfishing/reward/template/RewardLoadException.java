package com.illuzionzstudios.customfishing.reward.template;

import com.illuzionzstudios.mist.Logger;
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
