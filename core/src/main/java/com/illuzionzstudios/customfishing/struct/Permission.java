package com.illuzionzstudios.customfishing.struct;

/**
 * Created by Illuzionz on 12 2019
 */

import com.illuzionzstudios.core.bukkit.permission.IPermission;
import lombok.RequiredArgsConstructor;

/**
 * A permission node
 */
@RequiredArgsConstructor
public enum Permission implements IPermission {

    RELOAD("reload"),
    REWARDS("rewards");

    private final String node;
    private static final String PREFIX = "customfishing";

    @Override
    public String getPermissionNode() {
        return PREFIX + this.node;
    }

}
