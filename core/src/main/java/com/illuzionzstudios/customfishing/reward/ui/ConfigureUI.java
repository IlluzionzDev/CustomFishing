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
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.reward.config.ConfigType;
import com.illuzionzstudios.customfishing.reward.ui.parser.ConfigParser;
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.UserInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;

/**
 * Final UI to change a certain option
 */
@RequiredArgsConstructor
@Getter
public class ConfigureUI extends UserInterface {

    /**
     * Option to configure
     */
    public final ConfigType option;

    /**
     * Reward to change
     */
    public final FishingReward reward;

    @Override
    public void generateInventory() {
        inventory = Bukkit.createInventory(null, 27, Message.of("gui.option.title").toString());

        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(Material.ARROW)
                        .name(Message.of("gui.buttons.back.name"))
                        .lore(Message.of("gui.buttons.back.lore"))
                        .get())
                .listener((player, event) -> {
                    destroy();
                    new ConfigureRewardUI(reward).open(player);
                })
                .slot(0)
                .build());

        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(option.getIcon())
                        .name(Message.of("gui.option.current-value.name"))
                        .lore(Message.of("gui.option.current-value.lore")
                                .processPlaceholder("current_value", "&7" + option.getCurrentValue(reward)))
                        .get())
                .slot(12)
                .build());

        // If configuring list
        if (option.isList()) {
            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(Material.BARRIER)
                            .name(Message.of("gui.option.list-clear.name"))
                            .lore(Message.of("gui.option.list-clear.lore"))
                            .get())
                    .listener((player, event) -> {
                        if (event.getClick() != ClickType.LEFT) return;
                        // Clear value
                        switch (option) {
                            case SET_COMMANDS:
                                reward.getCommands().clear();
                                break;
                            case SET_MESSAGES:
                                reward.getMessages().clear();
                                break;
                            case SET_BROADCASTS:
                                reward.getBroadcasts().clear();
                                break;
                            case SET_WORLDS:
                                reward.getWorlds().clear();
                                break;
                            case SET_REGIONS:
                                reward.getRegions().clear();
                                break;
                            case SET_BLOCKED_REGIONS:
                                reward.getBlockedRegions().clear();
                                break;
                        }

                        open(player);
                    })
                    .slot(13)
                    .build());

            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(Material.STONE_BUTTON)
                            .name(Message.of("gui.option.list-add.name"))
                            .lore(Message.of("gui.option.list-add.lore"))
                            .get())
                    .listener(new ConfigParser(Message.of("rewards.config.enter-value"), reward, option))
                    .slot(14)
                    .build());
        } else {
            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(Material.STONE_BUTTON)
                            .name(Message.of("gui.option.set-option.name"))
                            .lore(Message.of("gui.option.set-option.lore"))
                            .get())
                    .listener(new ConfigParser(Message.of("rewards.config.enter-value"), reward, option))
                    .slot(14)
                    .build());
        }

        fillEmptySpaces(InterfaceButton.builder()
                .icon(new ItemStackFactory(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .get())
                .build());
    }
}
