package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.item.impl.AutoItemRegistryTagProvider;
import org.betterx.wover.item.datagen.LibWoverItemDatagen;

import net.neoforged.bus.api.IEventBus;
public class LibWoverItem {
    public static final ModCore C = ModCore.create("wover-item", "wover");

    public LibWoverItem(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        modEventBus.addListener(new LibWoverItemDatagen()::onGatherData);
        //EnchantmentManagerImpl.initialize(); //done in the wover.datapack.registry entrypoint
        WoverDataGenEntryPoint.registerAutoProvider(AutoItemRegistryTagProvider::new);
    }
}
