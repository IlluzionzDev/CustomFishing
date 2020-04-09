/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.command.type.SubCommand;
import com.illuzionzstudios.compatibility.ServerVersion;
import com.illuzionzstudios.config.Config;
import com.illuzionzstudios.config.ConfigSection;
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.CustomFishing;
import com.illuzionzstudios.customfishing.reward.FishingRewardBuilder;
import com.illuzionzstudios.customfishing.reward.template.yaml.YAMLRewardTemplate;
import com.illuzionzstudios.customfishing.struct.Permission;
import org.bukkit.Sound;

/**
 * Temporary command to phase the rewards.yml file
 * into the new file templates. For those lazy people who
 * don't want to do it manually.
 */
public class ConvertCommand extends SubCommand {

    /**
     * Singleton instance of our plugin
     */
    private final CustomFishing plugin = CustomFishing.getInstance();

    public ConvertCommand() {
        super("convert");

        this.requiredPermission = Permission.RELOAD;
    }

    @Override
    public void onCommand(String s, String[] strings) {
        // The rewards config, loaded when they convert to get rewards
        // If this doesn't exist may create another ghost file
        Config config = new Config(plugin, "rewards.yml");
        config.load();
        config.setRootNodeSpacing(0).setCommentSpacing(0);

        if (config.isConfigurationSection("Rewards")) {
            for (ConfigSection section : config.getSections("Rewards")) {
                // Loop through the rewards and create template
                YAMLRewardTemplate template = new YAMLRewardTemplate(section.getNodeKey(), "rewards");

                // Load new template to write
                template.getTemplateFile().load();

                // Set template name
                template.getTemplateFile().set("Name", section.getNodeKey());

                for (String key : section.getKeys(true)) {
                    // If value at that key is valid
                    if (section.get(key) != null) {
                        // Set value in template
                        template.getTemplateFile().set(key, section.get(key));
                    }
                }

                // Save after all changes
                template.save();

                Logger.info("Converted reward " + section.getNodeKey() + " to reward template file "
                        + section.getNodeKey() + ".yml");

                // Reload config after converting
                plugin.reloadConfig();
                plugin.getLocale().getMessage("general.reload").sendPrefixedMessage(commandSender);
            }
        }
    }

    @Override
    public boolean isConsoleAllowed() {
        return true;
    }

}
