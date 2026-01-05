package org.betterx.wover.poi.api;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public interface PoiTypeExtension {
    void wover_setTag(TagKey<Block> tag);
    TagKey<Block> wover_getTag();
}
