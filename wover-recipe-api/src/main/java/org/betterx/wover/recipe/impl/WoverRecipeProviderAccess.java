package org.betterx.wover.recipe.impl;

import net.minecraft.data.recipes.RecipeProvider;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public final class WoverRecipeProviderAccess {
    private WoverRecipeProviderAccess() {
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike item) {
        return RecipeProvider.has(item);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> tag) {
        return RecipeProvider.has(tag);
    }
}
