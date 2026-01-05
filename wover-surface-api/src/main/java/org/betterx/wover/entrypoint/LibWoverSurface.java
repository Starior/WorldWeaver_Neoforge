package org.betterx.wover.entrypoint;


import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.surface.impl.SurfaceRuleRegistryImpl;
import org.betterx.wover.surface.impl.conditions.MaterialConditionRegistryImpl;
import org.betterx.wover.surface.impl.numeric.NumericProviderRegistryImpl;
import org.betterx.wover.surface.impl.rules.MaterialRuleRegistryImpl;
import org.betterx.wover.surface.datagen.WoverSurfaceDatagen;

import net.neoforged.bus.api.IEventBus;
public class LibWoverSurface {
    public static final ModCore C = ModCore.create("wover-surface", "wover");

    public LibWoverSurface(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        modEventBus.addListener(new WoverSurfaceDatagen()::onGatherData);
        modEventBus.addListener(net.neoforged.neoforge.registries.RegisterEvent.class, MaterialConditionRegistryImpl::register);
        modEventBus.addListener(net.neoforged.neoforge.registries.RegisterEvent.class, MaterialRuleRegistryImpl::register);
        NumericProviderRegistryImpl.bootstrap();
        SurfaceRuleRegistryImpl.initialize();
    }
}
