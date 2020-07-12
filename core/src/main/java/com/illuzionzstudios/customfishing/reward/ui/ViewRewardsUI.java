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
import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.mist.compatibility.XMaterial;
import com.illuzionzstudios.mist.config.locale.Message;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.render.ItemCreator;
import com.illuzionzstudios.mist.ui.type.ConfirmUI;
import com.illuzionzstudios.mist.ui.type.PagedInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Menu to view all rewards in a paged interface
 */
public class ViewRewardsUI extends PagedInterface<FishingReward> {

    public ViewRewardsUI(UserInterface parent) {
        // All loaded rewards
        super(parent, RewardsController.INSTANCE.getLoadedRewards().values());

        setTitle("&8Custom Rewards");
    }

    @Override
    protected ItemStack convertToItemStack(FishingReward reward) {
        // Nicely display the reward
        return ItemCreator.builder()
                .material(XMaterial.PAPER)
                .name(new Message(FishingLocale.Interface.VIEW_REWARDS_REWARD_NAME)
                        .processPlaceholder("rewardName", reward.getName()).getMessage())
                .lore(new Message(FishingLocale.Interface.VIEW_REWARDS_REWARD_LORE)
                        .processPlaceholder("chance", reward.getChance()).getMessage())
                .build().makeUIItem();
    }

    @Override
    protected void onPageClick(Player player, FishingReward reward, ClickType clickType, InventoryClickEvent event) {
        // Don't want to modify items
        event.setCancelled(true);

        // Delete if right click
        if (clickType.isRightClick()) {
            new ConfirmUI(accepted -> {
                if (accepted) {
                    // Delete
                    RewardsController.INSTANCE.getLoadedRewards().remove(reward.getName());
                    newInstance().show(player);
                } else {
                    show(player);
                }
            }).show(player);
            return;
        }

        new ConfigureRewardUI(this, reward).show(player);
    }

    public UserInterface newInstance() {
        return new ViewRewardsUI(getParent().newInstance());
    }
}
