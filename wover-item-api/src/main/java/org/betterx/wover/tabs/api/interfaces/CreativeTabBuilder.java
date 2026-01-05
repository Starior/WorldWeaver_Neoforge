package org.betterx.wover.tabs.api.interfaces;

import org.betterx.wover.tabs.impl.CreativeTabBuilderImpl;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

public interface CreativeTabBuilder {
    CreativeTabBuilderImpl setIcon(ItemLike icon);
    CreativeTabBuilderImpl setPredicate(CreativeTabPredicate predicate);
    CreativeTabBuilderImpl setTitle(Component title);
    CreativeTabsBuilderWithTab buildAndAdd();
}
