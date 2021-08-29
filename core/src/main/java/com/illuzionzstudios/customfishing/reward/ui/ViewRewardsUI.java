package com.illuzionzstudios.customfishing.reward.ui;

import com.cryptomorin.xseries.XMaterial;
import com.illuzionzstudios.customfishing.controller.RewardsController;
import com.illuzionzstudios.customfishing.reward.FishingReward;
import com.illuzionzstudios.customfishing.settings.FishingLocale;
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
 *
 * TODO: Recode
 */
public class ViewRewardsUI extends PagedInterface<FishingReward> {

    public ViewRewardsUI() {
        // All loaded rewards
        super(RewardsController.INSTANCE.getLoadedRewards().values());

        setTitle("&8Custom Rewards");
    }

    @Override
    protected ItemStack convertToItemStack(FishingReward reward) {
        // Nicely display the reward
        return ItemCreator.builder()
                .material(XMaterial.PAPER)
                .name(FishingLocale.INTERFACE_VIEW_REWARDS_REWARD_NAME.toString("rewardName", reward.getName()).toString())
                .lore(FishingLocale.INTERFACE_VIEW_REWARDS_REWARD_LORE.toString("chance", reward.getChance()).toString())
                .build().makeUIItem();
    }

    @Override
    protected void onPageClick(Player player, FishingReward reward, ClickType clickType, InventoryClickEvent event) {
        // Don't want to modify items
        event.setCancelled(true);
    }

    public UserInterface newInstance() {
        return new ViewRewardsUI();
    }
}
