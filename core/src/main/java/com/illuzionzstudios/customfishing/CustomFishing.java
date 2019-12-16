package com.illuzionzstudios.customfishing;

import com.illuzionzstudios.core.config.Config;
import com.illuzionzstudios.core.plugin.IlluzionzPlugin;
import com.illuzionzstudios.core.util.Logger;

import java.util.List;

/**
 * Created by Illuzionz on 12 2019
 */
public class CustomFishing extends IlluzionzPlugin {

    private static CustomFishing INSTANCE;

    public static CustomFishing getInstance() {
        return INSTANCE;
    }

    @Override
    public void onPluginLoad() {
        INSTANCE = this;
    }

    @Override
    public void onPluginEnable() {
        Logger.info(getLocale().getMessage("general.test").getPrefixedMessage());
    }

    @Override
    public void onPluginDisable() {

    }

    @Override
    public void onConfigReload() {

    }

    @Override
    public List<Config> getExtraConfig() {
        return null;
    }
}
