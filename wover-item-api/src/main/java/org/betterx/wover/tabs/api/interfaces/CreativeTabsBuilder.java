package org.betterx.wover.tabs.api.interfaces;

import org.betterx.wover.tabs.impl.CreativeTabBuilderImpl;

import net.minecraft.world.level.ItemLike;

public interface CreativeTabsBuilder {
    CreativeTabBuilderImpl createTab(String name);
    CreativeTabBuilderImpl createBlockOnlyTab(ItemLike icon);
    CreativeTabBuilderImpl createItemOnlyTab(ItemLike icon);
}
