package com.illuzionzstudios.customfishing.reward;

import com.illuzionzstudios.mist.item.CustomItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A custom item that can be rewarded from fishing
 */

@Getter
@Setter
@ToString
public class FishingItem extends CustomItem {

	/**
	 * Chance of getting this item
	 */
	private double chance;
	private String mobName;

}
