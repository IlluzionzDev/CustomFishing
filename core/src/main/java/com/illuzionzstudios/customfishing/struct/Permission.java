package com.illuzionzstudios.customfishing.struct;

import com.illuzionzstudios.core.bukkit.permission.IPermission;
import lombok.RequiredArgsConstructor;

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
 * A permission node
 */
@RequiredArgsConstructor
public enum Permission implements IPermission {

    ADMIN("admin"),
    RELOAD("reload"),
    REWARDS("rewards");

    private static final String PREFIX = "customfishing.";
    private final String node;

    @Override
    public String getPermissionNode() {
        return PREFIX + this.node;
    }

}
