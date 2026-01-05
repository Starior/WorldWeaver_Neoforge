package org.betterx.wover.entrypoint;

import org.betterx.wover.config.impl.CachedConfig;
import org.betterx.wover.core.api.ModCore;

import net.neoforged.bus.api.IEventBus;
public class LibWoverUi {
    public static final ModCore C = ModCore.create("wover-ui", "wover");

    public LibWoverUi(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        CachedConfig.ensureStaticallyLoaded();
    }
}
