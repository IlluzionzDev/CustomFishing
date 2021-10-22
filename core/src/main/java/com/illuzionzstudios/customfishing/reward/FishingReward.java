package com.illuzionzstudios.customfishing.reward;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.reward.loader.FishingItemLoader;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.util.MathUtil;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Reward object the player can find. Contains all logic for checks and giving so
 * we can just call methods from here
 *
 * Anything annotated with @Nullable isn't required for the reward to be
 * loaded, it simply means it won't apply this reward
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FishingReward {

    /**
     * Name of the reward
     */
    private String name;

    /**
     * Commands to execute
     */
    @Nullable
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give. Built in the reward loader
     */
    @Nullable
    private List<FishingItemLoader> items = new ArrayList<>();

    /**
     * Messages to send
     */
    @Nullable
    private List<MistString> messages = new ArrayList<>();

    /**
     * Broadcast messages to send
     */
    @Nullable
    private List<MistString> broadcasts = new ArrayList<>();

    /**
     * Title message
     */
    @Nullable
    private MistString title;

    /**
     * Subtitle message
     */
    @Nullable
    private MistString subtitle;

    /**
     * Chance to find this reward
     */
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    private boolean vanillaRewards;

    /**
     * Experience to give the player in a range. E.g "1--6"
     */
    @Nullable
    private String experienceRange;

    /**
     * Sound to play
     */
    @Nullable
    private XSound sound;

    /**
     * Required permission
     */
    @Nullable
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    @Nullable
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    @Nullable
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
    @Nullable
    private List<String> blockedRegions = new ArrayList<>();

    /**
     * Reward the player with this fishing reward
     *
     * @param player The player to process rewards for
     * @param event The fishing event invoked too to process that
     */
    public void reward(@NotNull final Player player, final PlayerFishEvent event) {
        Sound sound = getSound() != null ? getSound().parseSound() : null;

        // Play sound, no need for null check as was handled loading rewards
        if (sound != null) {
            player.playSound(player.getLocation(), sound, 1, 1);
        }

        if (experienceRange != null) {
            // Set exp reward
            int expToDrop = (int) MathUtil.range(experienceRange);
            event.setExpToDrop(expToDrop < 1 ? 0 : expToDrop);
        }

        // If you should have default rewards
        if (!vanillaRewards) {
            if (event.getCaught() != null)
                event.getCaught().remove();
        }

        // Get global reward placeholders
        Map<String, Object> rewardPlaceholders = new HashMap<>();
        rewardPlaceholders.put("player", player.getName());

        // Send titles
        if (title != null) {
            title.sendTitle(player, subtitle);
        }

        // Send messages
        if (messages != null) {
            if (!(messages.size() == 1 && messages.get(0).toString().isEmpty())) {
                messages.forEach(mistString -> mistString.sendMessage(player));
            }
        }

        // Send broadcasts
        if (broadcasts != null) {
            if (!(broadcasts.size() == 1 && broadcasts.get(0).toString().isEmpty())) {
                broadcasts.forEach(msg -> {
                    msg = msg.toString(rewardPlaceholders);
                    Bukkit.getServer().broadcastMessage(msg.toString());
                });
            }
        }

        // Execute commands
        if (commands != null) {
            if (!(commands.size() == 1 && commands.get(0).toString().isEmpty())) {
                commands.forEach(command -> {
                    MistString replacer = new MistString(command);
                    replacer = replacer.toString(rewardPlaceholders);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replacer.toString());
                });
            }
        }

        // Give custom items
        if (items != null) {
            items.forEach(item -> {
                // Only add if meets chance
                if (MathUtil.chance(item.getObject().getChance()))
                    item.getObject().givePlayer(player);
            });
        }
    }

}
