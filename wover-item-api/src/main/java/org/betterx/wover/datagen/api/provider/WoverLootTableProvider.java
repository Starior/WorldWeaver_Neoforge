package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataProvider;
import org.betterx.wover.datagen.api.WoverLootProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;

public abstract class WoverLootTableProvider implements WoverDataProvider<LootTableProvider>, WoverLootProvider {
    /**
     * The title of the provider. Mainly used for logging.
     */
    public final String title;

    /**
     * The ModCore instance of the Mod that is providing this instance.
     */
    protected final ModCore modCore;

    protected final LootContextParamSet lootContextType;

    public WoverLootTableProvider(
            ModCore modCore,
            LootContextParamSet lootContextType
    ) {
        this(modCore, modCore.namespace, lootContextType);
    }

    public WoverLootTableProvider(
            ModCore modCore,
            String title,
            LootContextParamSet lootContextType
    ) {
        this.modCore = modCore;
        this.title = title;
        this.lootContextType = lootContextType;
    }

    protected abstract void boostrap(
            @NotNull HolderLookup.Provider lookup,
            @NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer
    );

    @Override
    public LootTableProvider getProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture,
            ExistingFileHelper existingFileHelper
    ) {
        return new LootTableProvider(
                output,
                Set.of(),
                List.of(toSubProviderEntry()),
                registriesFuture
        );
    }

    @Override
    public LootTableProvider.SubProviderEntry toSubProviderEntry() {
        return new LootTableProvider.SubProviderEntry(
                (lookup) -> (LootTableSubProvider) (biConsumer) -> boostrap(lookup, biConsumer),
                lootContextType
        );
    }
}
