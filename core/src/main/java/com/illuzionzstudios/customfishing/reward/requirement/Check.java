/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.requirement;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import org.bukkit.entity.Player;

/**
 * A requirement check to see if a player
 * is able to do something
 */
public interface Check {

    /**
     * See if the check passes
     *
     * @param player Player checking for
     * @param reward Reward to process checks for
     * @return If check passes
     */
    boolean processCheck(Player player, FishingReward reward);

}
