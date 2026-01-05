package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.block.api.BlockRegistry;
import org.betterx.wover.block.api.model.BlockModelProvider;
import org.betterx.wover.block.api.model.WoverBlockModelGenerators;
import org.betterx.wover.block.impl.ModelProviderExclusions;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import org.betterx.wover.block.api.model.WoverBlockModelGeneratorsAccess;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.common.data.ExistingFileHelper;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class WoverModelProvider implements WoverDataProvider<DataProvider> {
    /**
     * The title of the provider. Mainly used for logging.
     */
    public final String title;

    /**
     * The ModCore instance of the Mod that is providing this instance.
     */
    protected final ModCore modCore;

    public WoverModelProvider(ModCore modCore) {
        this(modCore, modCore.namespace);
    }

    public WoverModelProvider(ModCore modCore, String title) {
        this.modCore = modCore;
        this.title = title;
    }

    protected void addFromRegistry(
            WoverBlockModelGenerators generator,
            BlockRegistry registry,
            boolean validate
    ) {
        addFromRegistry(generator, registry, validate, ModelOverides.create());
    }

    public static class ModelOverides {
        public interface BlockModelProvider {
            void provideModels(Block block);
        }

        private final Map<Block, BlockModelProvider> OVERRIDES = new HashMap<>();
        private static final BlockModelProvider IGNORE = (block) -> {
        };

        public static ModelOverides create() {
            return new ModelOverides();
        }

        public ModelOverides override(@Nullable Block block, @NotNull BlockModelProvider provider) {
            if (block == Blocks.AIR || block == null) return this;

            final var old = OVERRIDES.put(block, provider);
            if (old != null) {
                throw new IllegalStateException("Block " + block + " already has an override.");
            }
            return this;
        }

        public ModelOverides overrideLike(@Nullable Block block, @NotNull Block copyFromBlock) {
            if (block == Blocks.AIR || block == null) return this;
            return this.override(block, OVERRIDES.get(copyFromBlock));
        }

        public ModelOverides ignore(@Nullable Block block) {
            if (block == Blocks.AIR || block == null) return this;
            return this.override(block, IGNORE);
        }

        public boolean contain(Block block) {
            return OVERRIDES.containsKey(block);
        }

        boolean provideBlockModel(Block block) {
            final var override = OVERRIDES.get(block);
            if (override != null) {
                override.provideModels(block);
                return true;
            }
            return false;
        }

        private ModelOverides() {
        }
    }


    protected void addFromRegistry(
            WoverBlockModelGenerators generator,
            BlockRegistry registry,
            boolean validateMissing,
            ModelOverides overrides
    ) {
        registry
                .allBlocks()
                .forEach(block -> {
                    // If the block is not in the overrides, and it is a BlockModelProvider, provide the models.
                    if (!overrides.provideBlockModel(block) && block instanceof BlockModelProvider bmp) {
                        bmp.provideBlockModels(generator);
                    } else if (validateMissing) {
                        ModelProviderExclusions.excludeFromBlockModelValidation(block);
                    }

                    if (!validateMissing) {
                        ModelProviderExclusions.excludeFromBlockModelValidation(block);
                    }

                });
    }

    protected abstract void bootstrapBlockStateModels(WoverBlockModelGenerators generator);
    protected abstract void bootstrapItemModels(ItemModelGenerators itemModelGenerator);

    @Override
    public DataProvider getProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture,
            ExistingFileHelper existingFileHelper
    ) {
        return new DataProvider() {
            private final PackOutput.PathProvider blockStatePathProvider =
                    output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
            private final PackOutput.PathProvider modelPathProvider =
                    output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");

            @Override
            public CompletableFuture<?> run(CachedOutput cache) {
                Map<Block, BlockStateGenerator> blockStates = Maps.newHashMap();
                Consumer<BlockStateGenerator> blockStateConsumer = generator -> {
                    Block block = generator.getBlock();
                    if (blockStates.put(block, generator) != null) {
                        throw new IllegalStateException("Duplicate blockstate definition for " + block);
                    }
                };

                Map<ResourceLocation, Supplier<JsonElement>> models = new HashMap<>();
                BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput = (id, supplier) -> {
                    if (models.put(id, supplier) != null) {
                        throw new IllegalStateException("Duplicate model definition for " + id);
                    }
                };

                Set<Item> skippedItems = new HashSet<>();
                Consumer<Item> itemConsumer = skippedItems::add;

                WoverBlockModelGeneratorsAccess blockModelGenerators = new WoverBlockModelGeneratorsAccess(
                        blockStateConsumer, modelOutput, itemConsumer
                );
                bootstrapBlockStateModels(new WoverBlockModelGenerators(blockModelGenerators));

                ItemModelGenerators itemModelGenerators = new ItemModelGenerators(modelOutput);
                bootstrapItemModels(itemModelGenerators);

                validateBlockStates(blockStates);
                addItemModelDelegates(models, skippedItems, existingFileHelper);

                return CompletableFuture.allOf(
                        saveCollection(cache, blockStates, b -> blockStatePathProvider.json(
                                b.builtInRegistryHolder().key().location()
                        )),
                        saveCollection(cache, models, modelPathProvider::json)
                );
            }

            private void validateBlockStates(Map<Block, BlockStateGenerator> blockStates) {
                for (Block block : BuiltInRegistries.BLOCK) {
                    if (ModelProviderExclusions.isExcluded(block)) {
                        continue;
                    }
                    ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
                    if (id == null || !id.getNamespace().equals(modCore.namespace)) {
                        continue;
                    }
                    if (!blockStates.containsKey(block)) {
                        throw new IllegalStateException("Missing blockstate definition for " + block);
                    }
                }
            }

            private void addItemModelDelegates(
                    Map<ResourceLocation, Supplier<JsonElement>> models,
                    Set<Item> skippedItems,
                    ExistingFileHelper existingFileHelper
            ) {
                BuiltInRegistries.BLOCK.forEach(block -> {
                    ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
                    if (blockId == null || !blockId.getNamespace().equals(modCore.namespace)) {
                        return;
                    }
                    Item item = Item.BY_BLOCK.get(block);
                    if (item == null || skippedItems.contains(item)) {
                        return;
                    }
                    ResourceLocation modelId = ModelLocationUtils.getModelLocation(item);
                    if (hasModel(modelId, models, existingFileHelper)) {
                        return;
                    }
                    ResourceLocation blockModelId = ModelLocationUtils.getModelLocation(block);
                    if (!hasModel(blockModelId, models, existingFileHelper)) {
                        return;
                    }
                    models.put(modelId, new DelegatedModel(blockModelId));
                });
            }

            private boolean hasModel(
                    ResourceLocation modelId,
                    Map<ResourceLocation, Supplier<JsonElement>> models,
                    ExistingFileHelper existingFileHelper
            ) {
                if (models.containsKey(modelId)) {
                    return true;
                }
                if (!existingFileHelper.isEnabled()) {
                    return false;
                }
                return existingFileHelper.exists(modelId, PackType.CLIENT_RESOURCES, ".json", "models");
            }

            private <T> CompletableFuture<?> saveCollection(
                    CachedOutput cache,
                    Map<T, ? extends Supplier<JsonElement>> objectToJsonMap,
                    Function<T, Path> resolveObjectPath
            ) {
                return CompletableFuture.allOf(objectToJsonMap.entrySet().stream().map(entry -> {
                    Path path = resolveObjectPath.apply(entry.getKey());
                    JsonElement element = entry.getValue().get();
                    return DataProvider.saveStable(cache, element, path);
                }).toArray(CompletableFuture[]::new));
            }

            @Override
            public String getName() {
                return "Model Definitions - " + title;
            }
        };
    }
}
