/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.requirement.check;

import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.requirement.Check;
import org.bukkit.entity.Player;

/**
 * Check if they have the required permission for a reward
 */
public class PermissionCheck implements Check {

    @Override
    public boolean processCheck(Player player, FishingReward reward) {
        String permission = reward.getPermission();

        // Simple check for if player has permission or is op
        return permission.trim().equals("") || player.hasPermission(permission) || player.isOp();
    }
}
