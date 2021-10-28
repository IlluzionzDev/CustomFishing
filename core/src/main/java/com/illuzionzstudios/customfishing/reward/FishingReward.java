package com.illuzionzstudios.customfishing.reward;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.reward.loader.FishingItemLoader;
import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.util.MathUtil;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reward object the player can find. Contains all logic for checks and giving so
 * we can just call methods from here
 * <p>
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
	 * Mob Spawn
	 */
	@Nullable
	private List<FishingItemLoader> mob = new ArrayList<>();

	/**
	 * Vanilla Mob Vector
	 */
	private Vector getVector(Location owner, Entity entity) {
		double d0 = owner.getX() - entity.getLocation().getX();
		double d1 = owner.getY() - entity.getLocation().getY();
		double d2 = owner.getZ() - entity.getLocation().getZ();
		return new Vector(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
	}

	/**
	 * MythicMob Vector
	 */
	private AbstractVector getVector(Location owner, ActiveMob activeMob) {
		double d0 = owner.getX() - activeMob.getLocation().getX();
		double d1 = owner.getY() - activeMob.getLocation().getY();
		double d2 = owner.getZ() - activeMob.getLocation().getZ();
		return new AbstractVector(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
	}

	/**
	 * Reward the player with this fishing reward
	 *
	 * @param player The player to process rewards for
	 * @param event  The fishing event invoked too to process that
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

		if (mob != null) {
			mob.forEach(mob -> {
				// Only spawn if meets chance
				if (MathUtil.chance(mob.getObject().getChance())) {
					if (mob.getSectionName().equals("mythicMob")) {
						Entity caughtEntity = event.getCaught();
						ActiveMob activeMob = MythicMobs.inst().getMobManager().spawnMob(mob.getSection().getString("mobName"), caughtEntity.getLocation());
						AbstractVector abstractVector = getVector(player.getLocation(), activeMob);
						activeMob.getEntity().setVelocity(abstractVector);
					} else if (mob.getSectionName().equals("vanillaMob")) {
						Entity caughtEntity = event.getCaught();
						Entity entity = caughtEntity.getWorld().spawnEntity(caughtEntity.getLocation(), EntityType.valueOf(mob.getSection().getString("mobName")));
						Vector vector = getVector(player.getLocation(), entity);
						entity.setVelocity(vector);
					}
				}
			});
		}
	}

}
