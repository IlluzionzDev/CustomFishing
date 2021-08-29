package com.illuzionzstudios.customfishing.reward;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.settings.Settings;
import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.util.MathUtil;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Reward object the player can find. Contains all logic for checks and giving so
 * we can just call methods from here
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
    private List<String> commands = new ArrayList<>();

    /**
     * Custom items to give. Built in the reward loader
     */
    private List<ItemStack> items = new ArrayList<>();

    /**
     * Messages to send
     */
    private List<MistString> messages = new ArrayList<>();

    /**
     * Broadcast messages to send
     */
    private List<MistString> broadcasts = new ArrayList<>();

    /**
     * Title message
     */
    private MistString title;

    /**
     * Subtitle message
     */
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
    private String experienceRange;

    /**
     * Sound to play
     */
    private XSound sound;

    /**
     * Required permission
     */
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    private List<String> worlds = new ArrayList<>();

    /**
     * Region the reward can be found in
     */
    private List<String> regions = new ArrayList<>();

    /**
     * Region the reward can't be found in
     */
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

        // Set exp reward
        int expToDrop = (int) MathUtil.range(experienceRange);
        event.setExpToDrop(expToDrop < 1 ? 0 : expToDrop);

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
            player.sendTitle(title.toString(), subtitle != null ? subtitle.toString() : "", Settings.TITLE_FADEIN.getInt(), Settings.TITLE_DISPLAY.getInt(), Settings.TITLE_FADEOUT.getInt());
        }

        // Send messages
        if (messages != null)
            messages.forEach(mistString -> mistString.sendMessage(player));

        // Send broadcasts
        if (broadcasts != null) {
            if (!broadcasts.isEmpty()) {
                broadcasts.forEach(msg -> {
                    msg = msg.toString(rewardPlaceholders);
                    Bukkit.getServer().broadcastMessage(msg.toString());
                });
            }
        }

        // Execute commands
        if (commands != null) {
            commands.forEach(command -> {
                MistString replacer = new MistString(command);
                replacer = replacer.toString(rewardPlaceholders);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replacer.toString());
            });
        }

        // Give custom items
        if (items != null) {
            items.forEach(item -> {
                player.getInventory().addItem(item);
            });
        }
    }

}
