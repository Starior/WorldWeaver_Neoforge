package org.betterx.wover.testmod.recipe.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.PackBuilder;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.testmod.entrypoint.TestModWoverRecipe;

public class TestModWoverRecipeDatagen extends WoverDataGenEntryPoint {
    @Override
    protected void onInitializeProviders(PackBuilder globalPack) {
        globalPack.addProvider(TestRecipeProvider::new);
    }

    @Override
    protected ModCore modCore() {
        return TestModWoverRecipe.C;
    }

}
