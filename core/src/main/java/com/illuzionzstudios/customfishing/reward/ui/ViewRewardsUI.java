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

import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.compatibility.XMaterial;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.render.ItemCreator;
import com.illuzionzstudios.mist.ui.type.PagedInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Menu to view all rewards in a paged interface
 */
public class ViewRewardsUI extends PagedInterface<FishingReward> {

    protected ViewRewardsUI(UserInterface parent) {
        // All loaded rewards
        super(parent, RewardsController.INSTANCE.getLoadedRewards().values());

        setTitle("&8Custom Rewards");
    }

    @Override
    protected ItemStack convertToItemStack(FishingReward reward) {
        // Nicely display the reward
        // TODO: Format from lang
        return ItemCreator.builder().material(XMaterial.PAPER)
                .name("&d&l" + reward.getName())
                .lores(Arrays.asList("&7Chance: &d" + reward.getChance(),
                        "",
                        "&7&o(Click to edit reward)"))
                .build().makeUIItem();
    }

    @Override
    protected void onPageClick(Player player, FishingReward reward, ClickType clickType, InventoryClickEvent event) {
        // Don't want to modify items
        event.setCancelled(true);
        new ConfigureOptionsUI<>(this, reward).show(player);
    }
}
