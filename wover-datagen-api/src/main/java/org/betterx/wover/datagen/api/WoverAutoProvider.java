package org.betterx.wover.datagen.api;

import net.minecraft.data.DataProvider;

import org.jetbrains.annotations.Nullable;

/**
 * Marker interface for auto providers that are added to all global Datapacks. You can register an
 * auto provider by calling {@link WoverDataGenEntryPoint#registerAutoProvider(PackBuilder.ProviderFactory)}.
 * <p>
 * When a new DataPack is created, the system will verify that the registered provider actually implements
 * this interface or throw an IllegalArgumentException.
 */
public interface WoverAutoProvider {
    interface WithRedirect extends WoverAutoProvider {
        <T extends DataProvider> @Nullable WoverDataProvider<T> redirect(@Nullable WoverDataProvider<T> provider);
    }
}
