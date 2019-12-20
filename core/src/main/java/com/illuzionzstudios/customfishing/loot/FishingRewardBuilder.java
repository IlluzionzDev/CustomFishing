package com.illuzionzstudios.customfishing.loot;

/**
 * Created by Illuzionz on 12 2019
 */

import com.illuzionzstudios.core.locale.player.Message;
import org.bukkit.Sound;
import org.bukkit.World;

import java.util.List;

/**
 * Used to build fishing rewards
 */
public class FishingRewardBuilder {

    private FishingReward reward = new FishingReward();

    public FishingRewardBuilder setName(String name) {
        reward.setName(name);
        return this;
    }

    public FishingRewardBuilder setCommands(List<String> commands) {
        reward.setCommands(commands);
        return this;
    }

    public FishingRewardBuilder addCommand(String command) {
        List<String> list = reward.getCommands();
        list.add(command);
        reward.setCommands(list);
        return this;
    }

    public FishingRewardBuilder setMessages(List<String> messages) {
        reward.setMessages(messages);
        return this;
    }

    public FishingRewardBuilder addMessage(String message) {
        List<String> list = reward.getMessages();
        list.add(message);
        reward.setMessages(list);
        return this;
    }

    public FishingRewardBuilder setBroadcastEnabled(boolean broadcast) {
        reward.setBroadcastEnabled(broadcast);
        return this;
    }

    public FishingRewardBuilder setBroadcasts(List<String> broadcasts) {
        reward.setBroadcasts(broadcasts);
        return this;
    }

    public FishingRewardBuilder addBroadcast(String broadcast) {
        List<String> list = reward.getBroadcasts();
        list.add(broadcast);
        reward.setBroadcasts(list);
        return this;
    }

    public FishingRewardBuilder setTitleEnabled(boolean enabled) {
        reward.setTitleEnabled(enabled);
        return this;
    }

    public FishingRewardBuilder setTitle(Message title) {
        reward.setTitle(title);
        return this;
    }

    public FishingRewardBuilder setSubTitle(Message subtitle) {
        reward.setSubtitle(subtitle);
        return this;
    }

    public FishingRewardBuilder setChance(double chance) {
        reward.setChance(chance);
        return this;
    }

    public FishingRewardBuilder setVanillaRewards(boolean vanilla) {
        reward.setVanillaRewards(vanilla);
        return this;
    }

    public FishingRewardBuilder setExperience(int experience) {
        reward.setExperience(experience);
        return this;
    }

    public FishingRewardBuilder setSound(Sound sound) {
        reward.setSound(sound);
        return this;
    }

    public FishingRewardBuilder setPermission(String permission) {
        reward.setPermission(permission);
        return this;
    }

    public FishingRewardBuilder setWorlds(List<String> worlds) {
        reward.setWorlds(worlds);
        return this;
    }

    public FishingRewardBuilder addWorld(String world) {
        List<String> list = reward.getWorlds();
        list.add(world);
        reward.setWorlds(list);
        return this;
    }

    public FishingRewardBuilder setRegions(List<String> regions) {
        reward.setRegions(regions);
        return this;
    }

    public FishingRewardBuilder addRegion(String region) {
        List<String> list = reward.getRegions();
        list.add(region);
        reward.setRegions(list);
        return this;
    }

    public FishingReward build() {
        return this.reward;
    }

}
