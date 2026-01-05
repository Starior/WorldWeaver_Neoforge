package org.betterx.wover.testmod.item.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverTagProvider;
import org.betterx.wover.tag.api.event.context.TagBootstrapContext;
import org.betterx.wover.tag.api.predefined.CommonBlockTags;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class BlockTagProvider extends WoverTagProvider.ForBlocks {
    public BlockTagProvider(ModCore modCore) {
        super(modCore, List.of(modCore.namespace, modCore.modId, "c"));
    }

    @Override
    public void prepareTags(TagBootstrapContext<Block> context) {
        context.add(CommonBlockTags.IS_OBSIDIAN, Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN);
    }
}
