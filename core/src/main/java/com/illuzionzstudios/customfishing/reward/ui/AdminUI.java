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
import com.illuzionzstudios.ui.button.InterfaceButton;
import com.illuzionzstudios.ui.types.UserInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;

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
                .icon(new ItemStackFactory(Material.GREEN_STAINED_GLASS_PANE)
                        .name(Message.of("gui.admin.add-reward.name"))
                        .lore(Message.of("gui.admin.add-reward.lore"))
                        .get())
                .slot(12)
                .build());

        // View all rewards
        addButton(InterfaceButton.builder()
                .icon(new ItemStackFactory(Material.PAPER)
                        .name(Message.of("gui.admin.view-rewards.name"))
                        .lore(Message.of("gui.admin.view-rewards.lore"))
                        .get())
                .slot(14)
                .listener((player, event) -> {
                    new ViewRewardsUI().open(player);
                })
                .build());

        fillEmptySpaces(InterfaceButton.builder()
                .icon(new ItemStackFactory(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .get())
                .build());
    }
}
