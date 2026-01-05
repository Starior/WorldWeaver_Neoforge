package org.betterx.wover.item.impl;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverAutoProvider;
import org.betterx.wover.datagen.api.WoverTagProvider;
import org.betterx.wover.item.api.ItemRegistry;
import org.betterx.wover.tag.api.event.context.ItemTagBootstrapContext;

import java.util.List;

/**
 * Creates item tags for all items that were registered with an
 * {@link org.betterx.wover.item.api.ItemRegistry} and had
 * some tags added to them.
 * <p>
 * This provider is automatically registered to the global datapack by {@link org.betterx.wover.datagen.api.WoverDataGenEntryPoint}.
 */
public class AutoItemRegistryTagProvider extends WoverTagProvider.ForItems implements WoverAutoProvider {

    public AutoItemRegistryTagProvider(ModCore modCore) {
        //do not filter any tags
        super(modCore, (List<String>) null);
    }

    @Override
    public void prepareTags(ItemTagBootstrapContext context) {
        ItemRegistry.streamAll().forEach(registry -> registry.bootstrapItemTags(context));
    }
}
