package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.datagen.impl.AutoBiomeTagProvider;
import org.betterx.wover.datagen.impl.AutoBlockTagProvider;
import org.betterx.wover.datagen.impl.AutoItemTagProvider;
import org.betterx.wover.events.api.WorldLifecycle;
import org.betterx.wover.tag.api.predefined.*;
import org.betterx.wover.tag.impl.TagBootstrapContextImpl;
import org.betterx.wover.tag.datagen.WoverTagDatagen;

import net.neoforged.bus.api.IEventBus;
import static org.betterx.wover.events.impl.AbstractEvent.SYSTEM_PRIORITY;

public class LibWoverTag {
    public static final ModCore C = ModCore.create("wover-tag", "wover");

    public LibWoverTag(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        modEventBus.addListener(new WoverTagDatagen()::onGatherData);
        WoverDataGenEntryPoint.registerAutoProvider(AutoBlockTagProvider::new);
        WoverDataGenEntryPoint.registerAutoProvider(AutoItemTagProvider::new);
        WoverDataGenEntryPoint.registerAutoProvider(AutoBiomeTagProvider::new);

        CommonBiomeTags.ensureStaticallyLoaded();
        CommonBlockTags.ensureStaticallyLoaded();
        CommonItemTags.ensureStaticallyLoaded();
        CommonPoiTags.ensureStaticallyLoaded();

        MineableTags.ensureStaticallyLoaded();
        ToolTags.ensureStaticallyLoaded();

        WorldLifecycle
                .BEFORE_LOADING_RESOURCES
                .subscribe((resourceManager, featureFlagSet) -> TagBootstrapContextImpl.invalidateCaches(), SYSTEM_PRIORITY);
    }
}
