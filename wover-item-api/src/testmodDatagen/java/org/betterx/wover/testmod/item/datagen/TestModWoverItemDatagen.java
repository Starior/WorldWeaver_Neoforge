package org.betterx.wover.testmod.item.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.PackBuilder;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.testmod.entrypoint.TestModWoverItem;

public class TestModWoverItemDatagen extends WoverDataGenEntryPoint {
    @Override
    protected void onInitializeProviders(PackBuilder globalPack) {
        globalPack.addProvider(TestEnchantmentProvider::new);
        globalPack.addProvider(BlockTagProvider::new);
    }

    @Override
    protected ModCore modCore() {
        return TestModWoverItem.C;
    }

}
