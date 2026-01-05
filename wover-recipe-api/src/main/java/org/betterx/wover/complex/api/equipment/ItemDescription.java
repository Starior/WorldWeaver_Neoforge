package org.betterx.wover.complex.api.equipment;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.item.api.ItemRegistry;
import org.betterx.wover.recipe.api.CraftingRecipeBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

abstract class ItemDescription<I extends Item> {
    public final I item;
    public final ResourceLocation location;

    public ItemDescription(
            ModCore modCore,
            String path,
            Supplier<I> creator,
            TagKey<Item>... tags
    ) {
        this.location = modCore.mk(path);
        this.item = creator.get();
        ItemRegistry.forMod(modCore).registerAsTool(path, item, tags);
    }

    static boolean buildRecipe(Item tool, ItemLike stick, CraftingRecipeBuilder builder) {
        if (tool instanceof ShearsItem) {
            builder.shape(" #", "# ");
        } else if (tool instanceof ArmorItem bai) {
            if (bai.getType().getSlot() == EquipmentSlot.FEET) {
                builder.shape("# #", "# #");
            } else if (bai.getType().getSlot() == EquipmentSlot.HEAD) {
                builder.shape("###", "# #");
            } else if (bai.getType().getSlot() == EquipmentSlot.CHEST) {
                builder.shape("# #", "###", "###");
            } else if (bai.getType().getSlot() == EquipmentSlot.LEGS) {
                builder.shape("###", "# #", "# #");
            } else return true;
        } else {
            builder.addMaterial('I', stick);
            if (tool instanceof PickaxeItem) {
                builder.shape("###", " I ", " I ");
            } else if (tool instanceof AxeItem) {
                builder.shape("##", "#I", " I");
            } else if (tool instanceof HoeItem) {
                builder.shape("##", " I", " I");
            } else if (tool instanceof ShovelItem) {
                builder.shape("#", "I", "I");
            } else if (tool instanceof SwordItem) {
                builder.shape("#", "#", "I");
            } else return true;
        }

        return false;
    }

    public I getItem() {
        return item;
    }
}
