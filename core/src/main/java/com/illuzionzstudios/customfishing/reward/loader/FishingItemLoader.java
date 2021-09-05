package com.illuzionzstudios.customfishing.reward.loader;

import com.illuzionzstudios.customfishing.reward.FishingItem;
import com.illuzionzstudios.mist.config.ConfigSection;
import com.illuzionzstudios.mist.item.loader.BaseCustomItemLoader;

/**
 * Load a fishing item from a config section
 */
public class FishingItemLoader extends BaseCustomItemLoader<FishingItem> {

    public FishingItemLoader(ConfigSection section) {
        super(section);
    }

    @Override
    protected FishingItem returnImplementedObject(ConfigSection configSection) {
        // Lets try build the reward
        FishingItem item = new FishingItem();
        item.setChance(section.getDouble("chance"));

        return item;
    }
}
