package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;

import net.neoforged.bus.api.IEventBus;
public class LibWoverMath {
    public static final ModCore C = ModCore.create("wover-math", "wover");

    public LibWoverMath(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
    }
}
