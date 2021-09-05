package com.illuzionzstudios.customfishing.reward.loader;

import com.cryptomorin.xseries.XSound;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.config.ConfigSection;
import com.illuzionzstudios.mist.config.locale.MistString;
import com.illuzionzstudios.mist.config.serialization.loader.YamlFileLoader;
import com.illuzionzstudios.mist.exception.PluginException;

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
        // TODO: Implement saving
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
            builder.name(config.getString("name"));

            cause = "Could not load commands";
            builder.commands(config.getStringList("commands"));

            cause = "Could not load items";
            ArrayList<FishingItemLoader> items = new ArrayList<>();
            // Parse custom items
            for (ConfigSection section : config.getSections("items")) {
                items.add(new FishingItemLoader(section));
            }
            builder.items(items);

            cause = "Could not load messages";
            ArrayList<MistString> messages = new ArrayList<>();
            config.getStringList("messages").forEach(string -> messages.add(new MistString(string)));
            builder.messages(messages);

            cause = "Could not load broadcasts";
            ArrayList<MistString> broadcasts = new ArrayList<>();
            config.getStringList("broadcasts").forEach(string -> broadcasts.add(new MistString(string)));
            builder.broadcasts(broadcasts);

            cause = "Could not load title";
            builder.title(new MistString(config.getString("title")));

            cause = "Could not load sub title";
            builder.subtitle(new MistString(config.getString("sub-title")));

            cause = "Could not load chance";
            builder.chance(config.getDouble("weight"));

            cause = "Could not load if to give vanilla rewards";
            builder.vanillaRewards(config.getBoolean("vanilla-rewards"));

            cause = "Could not load exp to give";
            builder.experienceRange(config.get("exp-amount") != null ? config.get("exp-amount").toString() : "0");

            // Set sound
            cause = "Sound " + config.getString("sound") + " is not valid";
            try {
                builder.sound(XSound.matchXSound(config.getString("sound")).get());
            } catch (Exception e) {
                builder.sound(null);
            }

            cause = "Could not load permission";
            builder.permission(config.getString("requirements.permission"));

            cause = "Could not load worlds";
            builder.worlds(config.getStringList("requirements.worlds"));

            cause = "Could not load regions";
            builder.regions(config.getStringList("requirements.regions"));

            cause = "Could not load blocked regions";
            builder.blockedRegions(config.getStringList("requirements.blocked-regions"));
        } catch (final Throwable ex) {
            throw new PluginException(cause);
        }

        return builder.build();
    }
}
