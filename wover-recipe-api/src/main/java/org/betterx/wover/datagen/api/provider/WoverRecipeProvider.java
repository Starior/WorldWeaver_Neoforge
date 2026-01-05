package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataProvider;
import org.betterx.wover.datagen.api.WoverRecipeGenerator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public abstract class WoverRecipeProvider implements WoverDataProvider<RecipeProvider>, WoverRecipeGenerator {
    /**
     * The title of the provider. Mainly used for logging.
     */
    public final String title;

    /**
     * The ModCore instance of the Mod that is providing this instance.
     */
    protected final ModCore modCore;

    public WoverRecipeProvider(
            ModCore modCore,
            String title
    ) {
        this.title = title;
        this.modCore = modCore;
    }

    protected abstract void bootstrap(HolderLookup.Provider provider, RecipeOutput context);

    @Override
    public void buildRecipes(HolderLookup.Provider lookup, RecipeOutput exporter) {
        bootstrap(lookup, exporter);
    }

    @Override
    public RecipeProvider getProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture,
            ExistingFileHelper existingFileHelper
    ) {
        return new RecipeProvider(output, registriesFuture) {
            @Override
            protected void buildRecipes(RecipeOutput exporter, HolderLookup.Provider lookup) {
                bootstrap(lookup, exporter);
            }
        };
    }
}
