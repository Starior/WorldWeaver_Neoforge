package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.recipe.datagen.LibWoverRecipeDatagen;

import net.neoforged.bus.api.IEventBus;
public class LibWoverRecipe {
    public static final ModCore C = ModCore.create("wover-recipe", "wover");

    public LibWoverRecipe(IEventBus modEventBus) {
        C.registerDatapackListener(modEventBus);
        modEventBus.addListener(new LibWoverRecipeDatagen()::onGatherData);
    }
}
