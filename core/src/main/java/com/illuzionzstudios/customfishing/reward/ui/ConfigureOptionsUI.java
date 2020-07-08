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

import com.illuzionzstudios.customfishing.reward.config.Configurable;
import com.illuzionzstudios.mist.Logger;
import com.illuzionzstudios.mist.compatibility.XMaterial;
import com.illuzionzstudios.mist.compatibility.XSound;
import com.illuzionzstudios.mist.config.locale.Message;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.button.Button;
import com.illuzionzstudios.mist.ui.render.ItemCreator;
import com.illuzionzstudios.mist.util.ReflectionUtil;
import com.illuzionzstudios.mist.util.TextUtil;
import org.bukkit.inventory.ItemStack;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu to view all options to configure for a {@link com.illuzionzstudios.customfishing.reward.FishingReward}
 * Abstracted to an item to configure those fields
 *
 * A maximum of 54 options per object
 *
 * TODO: May abstract to Mist Library
 *
 * @param <T> Type of item to configure for
 *           All public non-final fields will appear for configuration
 *
 *           Supported Types:
 *           {@link Integer}
 *           {@link Double}
 *           {@link Float}
 *           {@link String}
 *           {@link com.illuzionzstudios.mist.compatibility.XSound}
 *           {@link com.illuzionzstudios.mist.compatibility.XMaterial}
 *           {@link com.illuzionzstudios.mist.config.locale.Message}
 *           {@link org.bukkit.inventory.ItemStack}
 *           And {@link java.util.Collection} of aforementioned
 *
 *           Else a blank menu appears with no options to configure
 */
public class ConfigureOptionsUI<T> extends UserInterface {

    /**
     * The object to configure
     */
    public T object;

    /**
     * List of option buttons
     */
    private List<Button> options;

    /**
     * @param object The object instance to configure
     */
    public ConfigureOptionsUI(T object) {
        this(null, object);
    }

    /**
     * @param parent Parent {@link UserInterface}
     * @param object The object instance to configure
     */
    public ConfigureOptionsUI(UserInterface parent, T object) {
        super(parent, false);
        setTitle("&8Configure Options");

        this.options = new ArrayList<>();
        this.object = object;

        // Set all options
        prepareOptions();
    }

    /**
     * Called to scan object for classes and set buttons to
     * open further configuring fields
     */
    private void prepareOptions() {
        // Make sure fresh list
        options.clear();

        // Scan each field and set button based on type
        // Type of object
        Class<?> lookup = object.getClass();

        // Now scan fields to set buttons
        for (final Field f : lookup.getDeclaredFields()) {
            // Make sure field is annotated
            if (f.getAnnotationsByType(Configurable.class).length == 0) continue;

            // Get the type of field for checking
            Class<?> type = f.getType();
            
            // Checks
//            if (Integer.class.isAssignableFrom(type)) {
//
//            } else if (Float.class.isAssignableFrom(type)) {
//
//            } else if (Double.class.isAssignableFrom(type)) {
//
//            } else if (String.class.isAssignableFrom(type)) {
//
//            } else if (XSound.class.isAssignableFrom(type)) {
//
//            } else if (XMaterial.class.isAssignableFrom(type)) {
//
//            } else if (Message.class.isAssignableFrom(type)) {
//
//            } else if (ItemStack.class.isAssignableFrom(type)) {
//
//            }

            // Finally construct button
            Button button = Button.of(ItemCreator.builder()
                    .material(XMaterial.PAPER)
                    .name(TextUtil.convertCamelCase(f.getName()))
                    .lore("&7Value: " + ReflectionUtil.getFieldContent(f, object))
                    .build(),
                    (player, ui, clickType) -> {

                    });
            options.add(button);
        }

        // TODO: Set size of menu based on how many options
        setSize(54);
    }

    /**
     * Map options to each slot
     */
    public ItemStack getItemAt(final int slot) {
        if (slot >= options.size()) return ItemCreator.builder().name(" ").material(XMaterial.BLACK_STAINED_GLASS_PANE).build().makeUIItem();
        return options.get(slot) != null ? options.get(slot).getItem() : null;
    }

    /**
     * @return List of each button for each option
     */
    @Override
    protected List<Button> getButtonsToRegister() {
        return options;
    }

}
