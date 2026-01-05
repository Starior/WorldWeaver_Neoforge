package org.betterx.wover.entrypoint;


import org.betterx.wover.config.api.Configs;
import org.betterx.wover.core.api.ModCore;

import net.neoforged.bus.api.IEventBus;
public class LibWoverCore {
    public static final ModCore C = ModCore.create("wover-core", "wover");

    public LibWoverCore(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        Configs.saveConfigs();
    }
}
