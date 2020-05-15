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
import com.illuzionzstudios.customfishing.reward.ui.parser.AddItemsListener;
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.UserInterface;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Actual UI to add items
 */
@RequiredArgsConstructor
public class AddItemsUI extends UserInterface {

    /**
     * Reward to change
     */
    public final FishingReward reward;

    @Override
    public void generateInventory() {
        inventory = Bukkit.createInventory(null, 54, Message.of("gui.add-items.title").toString());

        // Listener to add items
        setPlayerInventoryListener(new AddItemsListener(true, 54));

        // Back button
        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(Material.ARROW)
                        .name(Message.of("gui.buttons.back.name"))
                        .lore(Message.of("gui.buttons.back.lore"))
                        .get())
                .listener((player, event) -> {
                    onClose();
                    new ConfigureUI(ConfigType.SET_ITEMS, reward).open(player);
                })
                .slot(0)
                .build());

        int slot = 1;
        // Render all items
        for (ItemStack stack : reward.getItems()) {
            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(stack)
                            .get())
                    .slot(slot++)
                    .build());
        }

        // Add listeners
        for (int i = 1; i < inventory.getSize() - 1; i++) {
            // Add back into inventory
            addSlotListener(i, (player, event) -> {
                ItemStack stack = event.getCurrentItem();

                if (stack == null || stack.getType() == Material.AIR) return;

                event.setCurrentItem(new ItemStack(Material.AIR));
                player.getInventory().addItem(stack);
            });
        }
    }

    @Override
    public boolean onClose() {
        // Populate items
        reward.getItems().clear();

        ArrayList<ItemStack> items = new ArrayList<>();

        for (int i = 1; i < inventory.getSize() - 1; i++) {
            ItemStack stack = inventory.getItem(i);

            if (stack == null || stack.getType() == Material.AIR) continue;

            items.add(stack);
        }

        reward.getItems().addAll(items);

        return true;
    }
}
