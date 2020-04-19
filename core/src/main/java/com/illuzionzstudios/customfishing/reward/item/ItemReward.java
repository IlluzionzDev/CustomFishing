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
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * A wrapper for a custom item that comes
 * under {@link FishingReward}
 */
@Getter
@RequiredArgsConstructor
public class ItemReward {

    /**
     * Material of the item
     */
    public final Material material;

    /**
     * Name of the item
     */
    public final String name;

    /**
     * Lore of the item
     */
    @Setter
    public List<String> lore;

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
    public ItemStackFactory factory = new ItemStackFactory(material);

    /**
     * @return The custom item
     */
    public ItemStack get() {
        // Construct from options
        return factory
                .name(name)
                .setLore(lore)
                .amount(amount)
                .get();
    }

}