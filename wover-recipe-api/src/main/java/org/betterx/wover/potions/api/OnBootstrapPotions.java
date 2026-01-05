package org.betterx.wover.potions.api;

import org.betterx.wover.events.api.Subscriber;

import net.minecraft.world.item.alchemy.PotionBrewing;

public interface OnBootstrapPotions extends Subscriber {
    void bootstrap(PotionBrewing.Builder builder);
}
