package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.block.api.BlockRegistry;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverAutoProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;

public class AutoBlockLootProvider extends WoverLootTableProvider implements WoverAutoProvider {
    public AutoBlockLootProvider(
            ModCore modCore
    ) {
        super(modCore, "Auto Block Loot", LootContextParamSets.BLOCK);
    }

    @Override
    protected void boostrap(
            HolderLookup.@NotNull Provider lookup,
            @NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer
    ) {
        BlockRegistry.streamAll().forEach(registry -> registry.bootstrapBlockLoot(lookup, biConsumer));
    }
}
