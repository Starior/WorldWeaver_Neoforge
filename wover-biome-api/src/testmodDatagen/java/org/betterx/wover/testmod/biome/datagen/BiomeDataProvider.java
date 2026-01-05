package org.betterx.wover.testmod.biome.datagen;

import org.betterx.wover.biome.api.data.BiomeData;
import org.betterx.wover.biome.api.data.BiomeDataRegistry;
import org.betterx.wover.biome.api.data.BiomeGenerationDataContainer;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverRegistryContentProvider;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biomes;

public class BiomeDataProvider extends WoverRegistryContentProvider<BiomeData> {
    /**
     * Creates a new instance of {@link WoverRegistryContentProvider}.
     *
     * @param modCore The ModCore instance of the Mod that is providing this instance.
     */
    public BiomeDataProvider(
            ModCore modCore
    ) {
        super(modCore, "BiomeData", BiomeDataRegistry.BIOME_DATA_REGISTRY);
    }

    @Override
    protected void bootstrap(BootstrapContext<BiomeData> context) {
        BiomeData bd = new BiomeData(1.0f, Biomes.SAVANNA, BiomeGenerationDataContainer.EMPTY);
        context.register(BiomeDataRegistry.createKey(modCore.id("savanna")), bd);
    }
}
