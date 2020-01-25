package com.illuzionzstudios.customfishing.controller.worldguard;

import org.bukkit.entity.Player;

/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */

/**
 * Implemented by different versions
 */
public interface IWorldGuardCheck {

    /**
     * Check if player is in a region
     *
     * @param regionName The region name
     * @param player     The player to check
     * @return If they're in the region
     */
    boolean playerInRegion(String regionName, Player player);

}