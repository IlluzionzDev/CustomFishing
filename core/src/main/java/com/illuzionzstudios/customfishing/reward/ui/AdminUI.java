/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.ui;

import com.illuzionzstudios.compatibility.CompatibleMaterial;
import com.illuzionzstudios.core.bukkit.item.ItemStackFactory;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.UserInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This is the main ui for configuring rewards
 * and things about the plugin
 */
public class AdminUI extends UserInterface {

    @Override
    public void generateInventory() {
        inventory = Bukkit.createInventory(null, 27, Message.of("gui.admin.title").toString());

        // Add a new reward
        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(CompatibleMaterial.GREEN_STAINED_GLASS_PANE.getItem())
                        .name(Message.of("gui.admin.add-reward.name"))
                        .lore(Message.of("gui.admin.add-reward.lore"))
                        .get())
                .listener((player, event) -> {
                    // Insert new reward to edit
                    FishingReward reward = new FishingReward(
                            "Undefined",
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
                    RewardsController.INSTANCE.getLoadedRewards().put("Undefined", reward);
                    new ConfigureRewardUI(reward).open(player);
                })
                .slot(12)
                .build());

        // View all rewards
        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(CompatibleMaterial.PAPER.getMaterial())
                        .name(Message.of("gui.admin.view-rewards.name"))
                        .lore(Message.of("gui.admin.view-rewards.lore"))
                        .get())
                .slot(14)
                .listener((player, event) -> {
                    new ViewRewardsUI().open(player);
                })
                .build());

        fillEmptySpaces(InterfaceButton.builder()
                .icon(new ItemStackFactory(CompatibleMaterial.BLACK_STAINED_GLASS_PANE.getItem())
                        .name(" ")
                        .get())
                .build());
    }
}
