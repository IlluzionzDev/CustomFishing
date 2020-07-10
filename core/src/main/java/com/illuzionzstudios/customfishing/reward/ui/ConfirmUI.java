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

import com.illuzionzstudios.customfishing.settings.FishingLocale;
import com.illuzionzstudios.mist.compatibility.XMaterial;
import com.illuzionzstudios.mist.conversation.SimpleConversation;
import com.illuzionzstudios.mist.conversation.SimplePrompt;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.button.Button;
import com.illuzionzstudios.mist.ui.render.ItemCreator;
import com.illuzionzstudios.mist.util.ReflectionUtil;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple interface to confirm an action
 */
public class ConfirmUI extends UserInterface {

    /**
     * Action to run when after answered.
     * Returns whether was accepted or not
     */
    public final Consumer<Boolean> confirmAction;

    /**
     * Button to deny
     */
    public final Button denyButton;

    /**
     * Button to confirm
     */
    public final Button confirmButton;

    /**
     * @param confirmAction Action to run after confirmed/answered
     */
    public ConfirmUI(Consumer<Boolean> confirmAction) {
        super(null, false);
        setTitle("&8Are you sure?");
        setSize(27);

        this.confirmAction = confirmAction;

        denyButton = Button.of(ItemCreator.builder()
                .material(XMaterial.RED_DYE)
                .name(FishingLocale.getMessage("interface.deny.name").getMessage())
                .lore(FishingLocale.getMessage("interface.deny.lore").getMessage())
                .glow(true)
                .build(),
                (player, ui, clickType, event) -> {
                    confirmAction.accept(false);
                });

        confirmButton = Button.of(ItemCreator.builder()
                .material(XMaterial.LIME_DYE)
                .name(FishingLocale.getMessage("interface.confirm.name").getMessage())
                .lore(FishingLocale.getMessage("interface.confirm.lore").getMessage())
                .glow(true)
                .build(),
                (player, ui, clickType, event) -> {
                    confirmAction.accept(true);
                });
    }

    @Override
    public ItemStack getItemAt(final int slot) {
        if (slot == 11) {
            return confirmButton.getItem();
        } else if (slot == 15) {
            return denyButton.getItem();
        }

        // Else placeholder item
        return ItemCreator.builder().name(" ").material(XMaterial.BLACK_STAINED_GLASS_PANE).build().makeUIItem();
    }

}
