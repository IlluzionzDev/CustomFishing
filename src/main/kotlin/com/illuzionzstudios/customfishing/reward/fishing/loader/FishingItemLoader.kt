package com.illuzionzstudios.customfishing.reward.fishing.loader

import com.illuzionzstudios.customfishing.reward.fishing.item.FishingItem
import com.illuzionzstudios.mist.config.ConfigSection
import com.illuzionzstudios.mist.item.loader.BaseCustomItemLoader

class FishingItemLoader(section: ConfigSection) : BaseCustomItemLoader<FishingItem>(section) {

    override fun returnImplementedObject(section: ConfigSection?): FishingItem {
        // Lets try build the reward
        val item = FishingItem()
        item.chance = section?.getDouble("chance") ?: 0.0

        return item
    }
}