package com.illuzionzstudios.customfishing.reward.ui;

import com.cryptomorin.xseries.XMaterial;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.mist.ui.UserInterface;
import com.illuzionzstudios.mist.ui.button.Button;
import com.illuzionzstudios.mist.ui.button.type.InterfaceButton;
import com.illuzionzstudios.mist.ui.render.ItemCreator;
import org.bukkit.inventory.ItemStack;

/**
 * This is the main ui for configuring rewards
 * and things about the plugin
 */
public class AdminUI extends UserInterface {

    /**
     * Set our button to add a new item
     */
    public final Button addRewardButton;

    /**
     * View all current rewards to configure
     */
    public final InterfaceButton viewRewardsButton = new InterfaceButton(new ViewRewardsUI(this), ItemCreator.of(
            XMaterial.PAPER,
            "&a&lView All Rewards",
            "&7View all current rewards to configure")
            .glow(true));

    public AdminUI() {
        setTitle("&8Configure Rewards");

        addRewardButton = Button.of(ItemCreator.of(
                XMaterial.LIME_DYE,
                "&a&lCreate New Reward",
                "&7Create a new custom reward")
                .glow(true).build(),
                (player, ui, clickType, event) -> {
                    // Insert new reward to edit
                    FishingReward defaultReward = FishingReward.ofDefault();
                    RewardsController.INSTANCE.getLoadedRewards().put("New Reward", defaultReward);
                    new ConfigureRewardUI(new ViewRewardsUI(newInstance()), defaultReward).show(player);
                });
    }

    @Override
    public ItemStack getItemAt(final int slot) {
        if (slot == 11) {
            return addRewardButton.getItem();
        } else if (slot == 15) {
            return viewRewardsButton.getItem();
        }

        // Else placeholder item
        return ItemCreator.builder().name(" ").material(XMaterial.BLACK_STAINED_GLASS_PANE).build().makeUIItem();
    }

}
