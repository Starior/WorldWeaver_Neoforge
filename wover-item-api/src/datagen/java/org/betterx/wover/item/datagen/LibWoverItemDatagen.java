package org.betterx.wover.item.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.PackBuilder;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.entrypoint.LibWoverItem;

public class LibWoverItemDatagen extends WoverDataGenEntryPoint {
    @Override
    protected void onInitializeProviders(PackBuilder globalPack) {

    }

    @Override
    protected ModCore modCore() {
        return LibWoverItem.C;
    }

}
