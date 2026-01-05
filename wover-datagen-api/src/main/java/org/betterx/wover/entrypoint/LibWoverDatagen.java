package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;

import net.neoforged.bus.api.IEventBus;
public class LibWoverDatagen {
    public static final ModCore C = ModCore.create("wover-datagen", "wover");

    public LibWoverDatagen(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
    }
}
