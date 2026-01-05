package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.state.impl.WorldConfigImpl;
import org.betterx.wover.state.impl.WorldDatapackConfigImpl;
import org.betterx.wover.state.impl.WorldStateImpl;

import net.neoforged.bus.api.IEventBus;
public class LibWoverEvents {
    public static final ModCore C = ModCore.create("wover-events", "wover");

    public LibWoverEvents(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        WorldConfigImpl.initialize();
        WorldDatapackConfigImpl.initialize();
        WorldStateImpl.ensureStaticallyLoaded();
    }
}
