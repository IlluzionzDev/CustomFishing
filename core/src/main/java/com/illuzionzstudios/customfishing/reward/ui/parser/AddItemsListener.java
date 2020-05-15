/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.reward.ui.parser;

import com.illuzionzstudios.ui.button.listeners.AddItemFromInventoryListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Listen for adding custom items to the reward
 */
public class AddItemsListener extends AddItemFromInventoryListener {

    public AddItemsListener(boolean addAll, int invSize) {
        super(addAll, invSize);
    }

    @Override
    public boolean addItem(Player player, ItemStack itemStack) {
        // Add to inventory
        player.getOpenInventory().getTopInventory().addItem(itemStack);
        return true;
    }
}
