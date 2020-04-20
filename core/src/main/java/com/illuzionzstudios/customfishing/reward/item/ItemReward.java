/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.item;

import com.illuzionzstudios.core.bukkit.item.ItemStackFactory;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A wrapper for a custom item that comes
 * under {@link FishingReward}
 */
@Getter
public class ItemReward {

    /**
     * Material of the item
     */
    public final Material material;

    /**
     * Name of the item
     */
    @Setter
    public String name;

    /**
     * Lore of the item
     */
    @Setter
    public List<String> lore = new ArrayList<>();

    /**
     * List of item enchantments paired with level
     */
    @Setter
    public Map<Enchantment, Integer> enchantments = new HashMap<>();

    /**
     * Amount of the item
     */
    @Setter
    public int amount = 1;

    /**
     * Chance for item to be rewarded
     */
    @Setter
    public float chance;

    /**
     * Factory used to build the item
     */
    @Getter
    public ItemStackFactory factory;

    public ItemReward(Material material) {
        this.material = material;

        factory = new ItemStackFactory(material);
    }

    /**
     * @return The custom item
     */
    public ItemStack get() {
        // Construct from options
        if (name != null && !name.equals("")) {
            factory.name(name);
        }

        if (!lore.isEmpty()) {
            factory.setLore(lore);
        }

        ItemStack stack = factory.get();

        // Don't run if no enchantments
        if (!enchantments.isEmpty()) {
            // Apply enchantments
            ItemMeta meta = stack.getItemMeta();

            // Loop and add
            this.enchantments.forEach((ench, lvl) -> {
                meta.addEnchant(ench, lvl, true);
            });

            stack.setItemMeta(meta);
        }

        return stack;
    }

}
