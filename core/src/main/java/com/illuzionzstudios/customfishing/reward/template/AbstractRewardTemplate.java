package com.illuzionzstudios.customfishing.reward.template;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.config.YamlConfig;

/**
 * Base template for a new {@link FishingReward}
 */
public interface AbstractRewardTemplate {

    /**
     * @return The {@link FishingReward} from the template
     */
    FishingReward create() throws RewardLoadException;

    /**
     * @return The file used as the template
     */
    YamlConfig getTemplateFile();

    /**
     * Save the template file to disk
     * @return If saved correctly
     */
    boolean save();

}
