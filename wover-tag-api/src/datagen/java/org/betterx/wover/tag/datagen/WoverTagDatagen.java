package org.betterx.wover.tag.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.PackBuilder;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.entrypoint.LibWoverTag;
import org.betterx.wover.tag.api.TagManager;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WoverTagDatagen extends WoverDataGenEntryPoint {
    static final TagKey<Block> VILLAGER_JOB_SITES = TagManager.BLOCKS.makeWorldWeaverTag("villager_job_sites");

    @Override
    protected void onInitializeProviders(PackBuilder globalPack) {
        globalPack.addProvider(BlockTagProvider::new);
        globalPack.addProvider(ItemTagProvider::new);
        globalPack.addProvider(BiomeTagProvider::new);
    }


    @Override
    protected ModCore modCore() {
        return LibWoverTag.C;
    }
}
