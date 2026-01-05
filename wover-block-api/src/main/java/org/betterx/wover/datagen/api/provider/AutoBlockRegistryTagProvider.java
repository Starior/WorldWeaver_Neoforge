package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.block.api.BlockRegistry;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverAutoProvider;
import org.betterx.wover.datagen.api.WoverTagProvider;
import org.betterx.wover.tag.api.event.context.TagBootstrapContext;

import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * Creates item tags for all items that were registered with an
 * {@link org.betterx.wover.block.api.BlockRegistry} and had
 * some tags added to them.
 * <p>
 * This provider is automatically registered to the global datapack by {@link org.betterx.wover.datagen.api.WoverDataGenEntryPoint}.
 */
public class AutoBlockRegistryTagProvider extends WoverTagProvider.ForBlocks implements WoverAutoProvider {

    public AutoBlockRegistryTagProvider(ModCore modCore) {
        //do not filter any tags
        super(modCore, (List<String>) null);
    }

    @Override
    public void prepareTags(TagBootstrapContext<Block> context) {
        BlockRegistry.streamAll().forEach(registry -> registry.bootstrapBlockTags(context));
    }
}
