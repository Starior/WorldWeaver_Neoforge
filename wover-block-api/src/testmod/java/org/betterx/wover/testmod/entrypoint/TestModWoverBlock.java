package org.betterx.wover.testmod.entrypoint;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.tabs.api.CreativeTabs;
import org.betterx.wover.testmod.block.TestBlockRegistry;

import net.minecraft.world.item.Items;

import net.neoforged.fml.common.Mod;

@Mod("wover-block-testmod")
public class TestModWoverBlock {
    // ModCore for the TestMod. TestMod's do not share the wover namespace,
    // but (like other Mods that include Wover) have a unique one
    public static final ModCore C = ModCore.create("wover-block-testmod");

    public TestModWoverBlock() {
        TestBlockRegistry.ensureStaticallyLoaded();

        CreativeTabs.start(C)
                    .createBlockOnlyTab(Items.OAK_LOG)
                    .buildAndAdd()
                    .processRegistries()
                    .registerAllTabs();
    }
}
