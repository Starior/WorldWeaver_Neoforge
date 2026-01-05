package org.betterx.test.wover;

import org.betterx.wover.core.api.ModCore;

import net.neoforged.fml.common.Mod;

@Mod("wover-testmod")
public class TestModWover {
    public static final ModCore C = ModCore.create("wover-testmod");

    public TestModWover() {
        C.log.info("Hello from TestModWover!");
    }
}
