package com.illuzionzstudios.customfishing.reward.fishing.loader

import com.cryptomorin.xseries.XSound
import com.illuzionzstudios.customfishing.reward.fishing.FishingReward
import com.illuzionzstudios.customfishing.reward.fishing.item.FishingItem
import com.illuzionzstudios.mist.config.ConfigSection
import com.illuzionzstudios.mist.config.YamlConfig
import com.illuzionzstudios.mist.config.locale.MistString
import com.illuzionzstudios.mist.config.serialization.loader.YamlFileLoader
import com.illuzionzstudios.mist.requirement.PlayerRequirement
import com.illuzionzstudios.mist.requirement.PlayerRequirementLoader

/**
 * Load a fishing reward from a file
 */
class FishingRewardLoader(directory: String, fileName: String) : YamlFileLoader<FishingReward>(directory, fileName) {

    override fun loadYamlObject(file: YamlConfig?): FishingReward {
        val reward = FishingReward()

        reward.name = file?.getString("name")
        reward.commands = file?.getStringList("commands")

        val items: MutableList<FishingItem> = ArrayList()
        file?.getSections("items")?.forEach {
            items.add(FishingItemLoader(it!!).`object`)
        }
        reward.items = items

        reward.messages = file?.getStringList("messages")
        reward.broadcasts = file?.getStringList("broadcasts")

        reward.title = file?.getString("title")
        reward.subtitle = file?.getString("sub-title")

        reward.chance = file?.getDouble("weight") ?: 0.0
        reward.vanillaRewards = file?.getBoolean("vanilla-rewards") ?: false
        reward.experienceRange = file?.getString("exp-amount") ?: "1 to 6"
        try {
            reward.sound = XSound.matchXSound(config?.getString("sound") ?: "").get()
        } catch (ignored: Exception) {
        }

        // Load requirements
        val requirements: MutableList<PlayerRequirement> = ArrayList()
        file?.getSections("requirements")?.forEach {
            requirements.add(PlayerRequirementLoader(it!!).`object`)
        }
        reward.requirements = requirements

        return reward
    }

    override fun saveYaml() {
        TODO("Not yet implemented")
    }
}