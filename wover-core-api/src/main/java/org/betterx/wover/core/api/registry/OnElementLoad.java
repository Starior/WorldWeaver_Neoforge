package org.betterx.wover.core.api.registry;

import net.minecraft.resources.ResourceKey;

public interface OnElementLoad<T> {
    void didLoadFromDatapack(ResourceKey<T> elementKey, T element);
}
