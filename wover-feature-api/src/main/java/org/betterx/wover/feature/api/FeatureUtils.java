package org.betterx.wover.feature.api;

import org.betterx.wover.feature.impl.configured.ConfiguredFeatureManagerImpl;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FeatureUtils {
    public static boolean placeInWorld(
            ConfiguredFeature<?, ?> feature,
            WorldGenLevel level,
            BlockPos pos,
            RandomSource random,
            boolean unchanged
    ) {
        return ConfiguredFeatureManagerImpl.placeInWorld(feature, level, pos, random, null, unchanged);
    }

    public static boolean placeInWorld(
            ConfiguredFeature<?, ?> feature,
            WorldGenLevel level,
            BlockPos pos,
            RandomSource random,
            ChunkGenerator generator,
            boolean unchanged
    ) {
        return ConfiguredFeatureManagerImpl.placeInWorld(feature, level, pos, random, generator, unchanged);
    }

    private FeatureUtils() {
    }
}
