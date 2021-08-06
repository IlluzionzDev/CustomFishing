package com.illuzionzstudios.customfishing.reward;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.mist.config.Configurable;
import com.illuzionzstudios.mist.config.locale.Message;
import lombok.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reward object the player can find.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FishingReward {

    /**
     * Name of the reward
     */
    @Configurable(description = "rewards.config.descriptions.name", material = XMaterial.NAME_TAG)
    private String name;

    /**
     * Commands to execute
     */
    @Configurable(description = "rewards.config.descriptions.commands", material = XMaterial.COMMAND_BLOCK)
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give
     */
    @Configurable(description = "rewards.config.descriptions.items", material = XMaterial.APPLE)
    private List<ItemStack> items = new ArrayList<>();

    /**
     * Messages to send
     */
    @Configurable(description = "rewards.config.descriptions.messages", material = XMaterial.OAK_SIGN)
    private List<String> messages = new ArrayList<>();

    /**
     * Broadcast messages to send
     */
    @Configurable(description = "rewards.config.descriptions.broadcasts", material = XMaterial.ACACIA_SIGN)
    private List<String> broadcasts = new ArrayList<>();

    /**
     * Title message
     */
    @Configurable(description = "rewards.config.descriptions.title", material = XMaterial.PAPER)
    private Message title;

    /**
     * Subtitle message
     */
    @Configurable(description = "rewards.config.descriptions.subtitle", material = XMaterial.PAPER)
    private Message subtitle;

    /**
     * Chance to find this reward
     */
    @Configurable(description = "rewards.config.descriptions.chance", material = XMaterial.SPAWNER)
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    @Configurable(description = "rewards.config.descriptions.vanillaRewards", material = XMaterial.FISHING_ROD)
    private boolean vanillaRewards;

    /**
     * Experience to give the player
     */
    @Configurable(description = "rewards.config.descriptions.experience", material = XMaterial.EXPERIENCE_BOTTLE)
    private int experience;

    /**
     * Sound to play
     */
    @Configurable(description = "rewards.config.descriptions.sound", material = XMaterial.NOTE_BLOCK)
    private XSound sound;

    /**
     * Required permission
     */
    @Configurable(description = "rewards.config.descriptions.permission", material = XMaterial.BARRIER)
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    @Configurable(description = "rewards.config.descriptions.worlds", material = XMaterial.GRASS_BLOCK)
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    @Configurable(description = "rewards.config.descriptions.regions", material = XMaterial.DIRT)
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
    @Configurable(description = "rewards.config.descriptions.blockedRegions", material = XMaterial.COARSE_DIRT)
    private List<String> blockedRegions = new ArrayList<>();

    /**
     * @return Basic default {@link FishingReward} to be configured
     */
    public static FishingReward ofDefault() {
        return new FishingReward(
                "New Reward",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new Message(""),
                new Message(""),
                0.0,
                true,
                6,
                null,
                "",
                Collections.singletonList("all"),
                Collections.singletonList("global"),
                new ArrayList<>()
        );
    }

}
