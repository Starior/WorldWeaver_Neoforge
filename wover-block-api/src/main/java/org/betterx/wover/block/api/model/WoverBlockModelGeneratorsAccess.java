package org.betterx.wover.block.api.model;

import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

import com.google.gson.JsonElement;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class WoverBlockModelGeneratorsAccess extends BlockModelGenerators {
    public WoverBlockModelGeneratorsAccess(
            Consumer<BlockStateGenerator> blockStateOutput,
            BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput,
            Consumer<Item> skippedAutoModelsOutput
    ) {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    public Consumer<BlockStateGenerator> blockStateOutput() {
        return this.blockStateOutput;
    }

    public BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput() {
        return this.modelOutput;
    }

    public Map<Block, TexturedModel> texturedModels() {
        return this.texturedModels;
    }

    @Override
    public void skipAutoItemBlock(Block block) {
        super.skipAutoItemBlock(block);
    }

    @Override
    public void delegateItemModel(Block block, ResourceLocation model) {
        super.delegateItemModel(block, model);
    }

    @Override
    public void createSimpleFlatItemModel(Item item) {
        super.createSimpleFlatItemModel(item);
    }

    public void createSimpleFlatItemModel(Block block) {
        Item item = block.asItem();
        if (item == Items.AIR) {
            return;
        }
        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(item),
                TextureMapping.layer0(block),
                this.modelOutput
        );
    }

    @Override
    public void createDoor(Block block) {
        super.createDoor(block);
    }

    @Override
    public void createOrientableTrapdoor(Block block) {
        super.createOrientableTrapdoor(block);
    }

    @Override
    public void createTrapdoor(Block block) {
        super.createTrapdoor(block);
    }

    public void createCraftingTableLike(
            Block craftingTableBlock,
            Block craftingTableMaterialBlock,
            BiFunction<Block, Block, TextureMapping> textureMappingGetter
    ) {
        TextureMapping textureMapping = textureMappingGetter.apply(craftingTableBlock, craftingTableMaterialBlock);
        this.blockStateOutput.accept(createSimpleBlock(
                craftingTableBlock,
                ModelTemplates.CUBE.create(craftingTableBlock, textureMapping, this.modelOutput)
        ));
    }

    public void createNonTemplateHorizontalBlock(Block block) {
        this.blockStateOutput.accept(MultiVariantGenerator
                .multiVariant(
                        block,
                        Variant.variant()
                                .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))
                )
                .with(createHorizontalFacingDispatch())
        );
    }

    public PropertyDispatch createColumnWithFacing() {
        return PropertyDispatch
                .property(BlockStateProperties.FACING)
                .select(Direction.DOWN, Variant
                        .variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                .select(Direction.UP, Variant.variant())
                .select(Direction.NORTH, Variant
                        .variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                .select(Direction.SOUTH, Variant
                        .variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .select(Direction.WEST, Variant
                        .variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .select(Direction.EAST, Variant
                        .variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
    }

    public static PropertyDispatch createHorizontalFacingDispatch() {
        return PropertyDispatch
                .property(BlockStateProperties.HORIZONTAL_FACING)
                .select(Direction.EAST, Variant
                        .variant()
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .select(Direction.SOUTH, Variant
                        .variant()
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                .select(Direction.WEST, Variant
                        .variant()
                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                .select(Direction.NORTH, Variant.variant());
    }

    public static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation modelLocation) {
        return BlockModelGenerators.createSimpleBlock(block, modelLocation);
    }

    public static BlockStateGenerator createButton(Block block, ResourceLocation button, ResourceLocation pressed) {
        return BlockModelGenerators.createButton(block, button, pressed);
    }

    public static BlockStateGenerator createCustomFence(
            Block block,
            ResourceLocation post,
            ResourceLocation sideNorth,
            ResourceLocation sideEast,
            ResourceLocation sideSouth,
            ResourceLocation sideWest
    ) {
        return BlockModelGenerators.createCustomFence(block, post, sideNorth, sideEast, sideSouth, sideWest);
    }

    public static BlockStateGenerator createFence(Block block, ResourceLocation post, ResourceLocation side) {
        return BlockModelGenerators.createFence(block, post, side);
    }

    public static BlockStateGenerator createWall(
            Block block,
            ResourceLocation post,
            ResourceLocation sideLow,
            ResourceLocation sideTall
    ) {
        return BlockModelGenerators.createWall(block, post, sideLow, sideTall);
    }

    public static BlockStateGenerator createFenceGate(
            Block block,
            ResourceLocation open,
            ResourceLocation closed,
            ResourceLocation wallOpen,
            ResourceLocation wallClosed,
            boolean uvlock
    ) {
        return BlockModelGenerators.createFenceGate(block, open, closed, wallOpen, wallClosed, uvlock);
    }

    public static BlockStateGenerator createStairs(
            Block block,
            ResourceLocation inner,
            ResourceLocation straight,
            ResourceLocation outer
    ) {
        var dispatch = PropertyDispatch.properties(
                BlockStateProperties.HORIZONTAL_FACING,
                BlockStateProperties.HALF,
                BlockStateProperties.STAIRS_SHAPE,
                BlockStateProperties.WATERLOGGED
        );

        // Bottom half
        selectStairsVariant(dispatch, Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, false));
        selectStairsVariant(dispatch, Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, false));
        selectStairsVariant(dispatch, Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, false));
        selectStairsVariant(dispatch, Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, false));
        selectStairsVariant(dispatch, Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, false));
        selectStairsVariant(dispatch, Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, true));

        // Top half
        selectStairsVariant(dispatch, Direction.EAST, Half.TOP, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.TOP, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, stairsVariant(straight, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, stairsVariant(inner, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, true));

        selectStairsVariant(dispatch, Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R90, true));
        selectStairsVariant(dispatch, Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R270, true));
        selectStairsVariant(dispatch, Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R180, true));
        selectStairsVariant(dispatch, Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, stairsVariant(outer, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0, true));

        return MultiVariantGenerator.multiVariant(block).with(dispatch);
    }

    private static void selectStairsVariant(
            PropertyDispatch.C4<Direction, Half, StairsShape, Boolean> dispatch,
            Direction facing,
            Half half,
            StairsShape shape,
            Variant variant
    ) {
        dispatch.select(facing, half, shape, false, variant);
        dispatch.select(facing, half, shape, true, variant);
    }

    private static Variant stairsVariant(
            ResourceLocation model,
            VariantProperties.Rotation xRot,
            VariantProperties.Rotation yRot,
            boolean uvLock
    ) {
        Variant variant = Variant.variant().with(VariantProperties.MODEL, model);
        if (xRot != VariantProperties.Rotation.R0) {
            variant = variant.with(VariantProperties.X_ROT, xRot);
        }
        if (yRot != VariantProperties.Rotation.R0) {
            variant = variant.with(VariantProperties.Y_ROT, yRot);
        }
        if (uvLock) {
            variant = variant.with(VariantProperties.UV_LOCK, true);
        }
        return variant;
    }

    public static BlockStateGenerator createAxisAlignedPillarBlock(Block block, ResourceLocation modelLocation) {
        return BlockModelGenerators.createAxisAlignedPillarBlock(block, modelLocation);
    }

    public static BlockStateGenerator createPressurePlate(
            Block block,
            ResourceLocation up,
            ResourceLocation down
    ) {
        return BlockModelGenerators.createPressurePlate(block, up, down);
    }

    public static BlockStateGenerator createSlab(
            Block block,
            ResourceLocation bottom,
            ResourceLocation top,
            ResourceLocation fullBlock
    ) {
        return BlockModelGenerators.createSlab(block, bottom, top, fullBlock);
    }
}
