package org.betterx.wover.testmod.entrypoint;

import org.betterx.wover.core.api.ModCore;

import net.neoforged.fml.common.Mod;

@Mod("wover-ui-testmod")
public class TestModWoverUi {
    // ModCore for the TestMod. TestMod's do not share the wover namespace,
    // but (like other Mods that include Wover) have a unique one
    public static final ModCore C = ModCore.create("wover-ui-testmod");
}
