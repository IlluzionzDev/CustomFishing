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
import com.illuzionzstudios.core.util.Logger;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.page.DividedInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;

/**
 * View all rewards and an option to edit them
 */
public class ViewRewardsUI extends DividedInterface {

    @Override
    public void generateInventory() {
        inventory = Bukkit.createInventory(null, 54, Message.of("gui.view-rewards.title").toString());

        int slot = 0;
        // Loop through all rewards and show
        for (FishingReward reward : RewardsController.INSTANCE.getLoadedRewards().values()) {
            addButton(InterfaceButton.builder()
                    .icon(new ItemStackFactory(Material.PAPER)
                            .name(Message.of("gui.view-rewards.item.name")
                                .processPlaceholder("reward_name", reward.getName()))
                            .lore(Message.of("gui.view-rewards.item.lore")
                                .processPlaceholder("chance", reward.getChance()))
                            .get())
                    .listener((player, event) -> {
                        destroy();
                        new ConfigureRewardUI(reward).open(player);
                    })
                    .slot(slot++)
                    .build());
        }
    }
}
