package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.registry.DatapackRegistryEntrypoint;
import org.betterx.wover.surface.impl.SurfaceRuleRegistryImpl;

public class SurfaceDatapackRegistryEntrypoint implements DatapackRegistryEntrypoint {
    @Override
    public void registerDatapackRegistries() {
        SurfaceRuleRegistryImpl.initialize();
    }
}
