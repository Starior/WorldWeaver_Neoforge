package org.betterx.wover.common.generator.api.chunkgenerator;

import net.minecraft.world.level.chunk.ChunkGenerator;

public interface RebuildableFeaturesPerStep<G extends ChunkGenerator> {
    void wover_rebuildFeaturesPerStep();
}
