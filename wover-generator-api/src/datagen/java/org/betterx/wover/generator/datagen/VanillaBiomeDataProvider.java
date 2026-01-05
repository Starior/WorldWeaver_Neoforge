package org.betterx.wover.generator.datagen;

import org.betterx.wover.biome.api.builder.BiomeBootstrapContext;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverRegistryContentProvider;
import org.betterx.wover.datagen.api.provider.multi.WoverBiomeProvider;
import org.betterx.wover.generator.api.biomesource.WoverBiomeBuilder;

import net.minecraft.world.level.biome.Biomes;

public class VanillaBiomeDataProvider extends WoverBiomeProvider {
    /**
     * Creates a new instance of {@link WoverRegistryContentProvider}.
     *
     * @param modCore The ModCore instance of the Mod that is providing this instance.
     */
    public VanillaBiomeDataProvider(
            ModCore modCore
    ) {
        super(modCore);
    }

    @Override
    protected void bootstrap(BiomeBootstrapContext context) {
        final var END_HIGHLANDS = WoverBiomeBuilder.wrappedKey(Biomes.END_HIGHLANDS);
        END_HIGHLANDS.bootstrap(context)
                     .genChance(0.20f)
                     .edgeSize(4)
                     .isEndHighlandBiome()
                     .register();

        WoverBiomeBuilder.wrappedKey(Biomes.END_MIDLANDS)
                         .bootstrap(context)
                         .genChance(0.05f)
                         .isEndMidlandBiome(END_HIGHLANDS)
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.END_BARRENS)
                         .bootstrap(context)
                         .genChance(0.03f)
                         .isEndBarrensBiome(END_HIGHLANDS)
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.THE_END)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isEndCenterIslandBiome()
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.SMALL_END_ISLANDS)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isEndSmallIslandBiome()
                         .register();


        WoverBiomeBuilder.wrappedKey(Biomes.NETHER_WASTES)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isNetherBiome()
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.SOUL_SAND_VALLEY)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isNetherBiome()
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.BASALT_DELTAS)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isNetherBiome()
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.CRIMSON_FOREST)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isNetherBiome()
                         .register();

        WoverBiomeBuilder.wrappedKey(Biomes.WARPED_FOREST)
                         .bootstrap(context)
                         .genChance(0.01f)
                         .isNetherBiome()
                         .register();
    }


}
