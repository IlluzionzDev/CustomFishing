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
