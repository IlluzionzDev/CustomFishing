package com.illuzionzstudios.customfishing.loot;

/**
 * Created by Illuzionz on 12 2019
 */

import com.illuzionzstudios.core.locale.player.Message;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.World;

import java.util.List;

/**
 * Reward object the player can find.
 */
@Data
public class FishingReward {

    /**
     * Name of the reward
     */
    private String name;

    /**
     * Commands to execute
     */
    private List<String> commands;

    /**
     * Messages to send
     */
    private List<String> messages;

    /**
     * If broadcasting is enabled
     */
    private boolean broadcastEnabled;

    /**
     * Broadcast messages to send
     */
    private List<String> broadcasts;

    /**
     * If titles are enabled
     */
    private boolean titleEnabled;

    /**
     * Title message
     */
    private Message title;

    /**
     * Subtitle message
     */
    private Message subtitle;

    /**
     * Chance to find this reward
     */
    private double chance;

    /**
     * If default vanilla rewards are enabled
     */
    private boolean vanillaRewards;

    /**
     * Experience to give the player
     */
    private int experience;

    /**
     * Sound to play
     */
    private Sound sound;

    /**
     * Required permission
     */
    private String permission;

    /**
     * Worlds the reward can be found in
     */
    private List<String> worlds;

    /**
     * Region the reward can be found in
     */
    private List<String> regions;

    public FishingReward() {
    }

}
