package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.registry.DatapackRegistryEntrypoint;
import org.betterx.wover.structure.impl.StructureManagerImpl;
import org.betterx.wover.structure.impl.pools.StructurePoolManagerImpl;
import org.betterx.wover.structure.impl.sets.StructureSetManagerImpl;

public class StructureDatapackRegistryEntrypoint implements DatapackRegistryEntrypoint {
    @Override
    public void registerDatapackRegistries() {
        StructurePoolManagerImpl.initialize();
        StructureManagerImpl.initialize();
        StructureSetManagerImpl.initialize();
    }
}
