package org.betterx.wover.block.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public interface CustomBlockItemProvider {
    /**
     * Used to generate a custom Block Item when a block is registered to the {@link BlockRegistry}.
     *
     * @return {@link BlockItem}
     */
    BlockItem getCustomBlockItem(ResourceLocation blockID, Item.Properties settings);
}
