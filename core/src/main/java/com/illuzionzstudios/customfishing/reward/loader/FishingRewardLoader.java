package com.illuzionzstudios.customfishing.reward.loader;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.config.serialization.loader.YamlFileLoader;
import com.illuzionzstudios.mist.util.ItemStackUtil;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Disk file loader for a {@link com.illuzionzstudios.customfishing.reward.FishingReward}. Takes YAML files
 */
public class FishingRewardLoader extends YamlFileLoader<FishingReward> {

    public FishingRewardLoader(String directory, String fileName) {
        super(directory, fileName);
    }

    @Override
    public void saveYaml() {
        try {
            this.config.set("Name", object.getName());

            // TODO: Make custom item parser
            ArrayList<String> itemBlobs = new ArrayList<>();
            object.getItems().forEach(item -> {
                itemBlobs.add(ItemStackUtil.serialize(item));
            });
            this.config.set("Items", itemBlobs);

            this.config.set("Commands", object.getCommands());

            this.config.set("Messages", object.getMessages());

            this.config.set("Broadcasts", object.getBroadcasts());

            this.config.set("Title", object.getTitle() == null ? null : object.getTitle());

            this.config.set("Sub Title", object.getSubtitle() == null ? null : object.getSubtitle());

            this.config.set("Chance", object.getChance());

            this.config.set("Vanilla Rewards", object.isVanillaRewards());

            this.config.set("Exp Amount", object.getExperienceRange());

            this.config.set("Sound", object.getSound() == null ? null : object.getSound().toString());

            this.config.set("Requirements.Permission", object.getPermission());

            this.config.set("Requirements.Worlds", object.getWorlds());

            this.config.set("Requirements.Regions", object.getRegions());

            this.config.set("Requirements.BlockedRegions", object.getBlockedRegions());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public FishingReward loadYamlObject() {
        // Lets try build the reward
        FishingReward.FishingRewardBuilder builder = FishingReward.builder();

        // Cause to send if fails
        String cause = "Failed to load reward file";

        // If fails throws error with cause message
        // set before trying to set builder
        try {
            // Load everything
            cause = "Could not load name";
            builder.name(config.getString("Name"));

            cause = "Could not load commands";
            builder.commands(config.getStringList("Commands"));

            ArrayList<ItemStack> items = new ArrayList<>();
            // Parse custom items
            for (String item : config.getStringList("Items")) {
                // Deserialize item stack
                ItemStack stack = ItemStackUtil.deserialize(item);
                if (stack != null) {
                    items.add(stack);
                }
            }
            // Finally add item reward
            builder.items(items);

            cause = "Could not load messages";
            ArrayList<MistString> messages = new ArrayList<>();
            config.getStringList("Messages").forEach(string -> messages.add(new MistString(string)));
            builder.messages(messages);

            cause = "Could not load broadcasts";
            ArrayList<MistString> broadcasts = new ArrayList<>();
            config.getStringList("Broadcasts").forEach(string -> broadcasts.add(new MistString(string)));
            builder.broadcasts(broadcasts);

            cause = "Could not load title";
            builder.title(new MistString(config.getString("Title")));

            cause = "Could not load sub title";
            builder.subtitle(new MistString(config.getString("Sub Title")));

            cause = "Could not load chance";
            builder.chance(config.getDouble("Chance"));

            cause = "Could not load if to give vanilla rewards";
            builder.vanillaRewards(config.getBoolean("Vanilla Rewards"));

            cause = "Could not load exp to give";
            builder.experienceRange(config.getString("Exp Amount"));

            // Set sound
            cause = "Sound " + config.getString("Sound") + " is not valid";
            try {
                builder.sound(XSound.matchXSound(config.getString("Sound")).get());
            } catch (Exception e) {
                builder.sound(null);
            }

            cause = "Could not load permission";
            builder.permission(config.getString("Requirements.Permission"));

            cause = "Could not load worlds";
            builder.worlds(config.getStringList("Requirements.Worlds"));

            cause = "Could not load regions";
            builder.regions(config.getStringList("Requirements.Regions"));

            cause = "Could not load blocked regions";
            builder.blockedRegions(config.getStringList("Requirements.BlockedRegions"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return builder.build();
    }
}
