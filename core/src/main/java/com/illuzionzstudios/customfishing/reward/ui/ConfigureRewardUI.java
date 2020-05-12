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

import com.illuzionzstudios.core.bukkit.item.ItemStackFactory;
import com.illuzionzstudios.core.locale.player.Message;
import com.illuzionzstudios.customfishing.controller.ConfigController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.config.ConfigType;
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.UserInterface;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

/**
 * Display all options to configure a reward
 */
@RequiredArgsConstructor
public class ConfigureRewardUI extends UserInterface {

    /**
     * Reward to configure
     */
    private final FishingReward reward;

    @Override
    public void generateInventory() {
        inventory = Bukkit.createInventory(null, 54, Message.of("gui.configure.title").toString());

        // For every config type set an item
        for (ConfigType type : ConfigType.values()) {
            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(type.getIcon())
                            .name(Message.of("gui.configure." + type.getConfigIdentifier() + ".name"))
                            .lore(Message.of("gui.configure." + type.getConfigIdentifier() + ".lore")
                                .processPlaceholder("current_value", type.getCurrentValue(reward)))
                            .get())
                    .listener((player, event) -> {
                        if (event.getClick() != ClickType.LEFT) return;
                        // Allow them to set value
                        ConfigController.INSTANCE.prepareConfiguration(player, reward, type);
                        close();
                    })
                    .slot(type.getSlot())
                    .build());
        }
    }
}
