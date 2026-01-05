package org.betterx.wover.recipe.api;

import org.betterx.wover.recipe.impl.SmithingRecipeBuilderImpl;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public interface SmithingRecipeBuilder extends BaseRecipeBuilder<SmithingRecipeBuilder>, BaseUnlockableRecipeBuilder<SmithingRecipeBuilder> {
    SmithingRecipeBuilderImpl template(SmithingTemplateItem in);

    SmithingRecipeBuilderImpl base(TagKey<Item> in);
    SmithingRecipeBuilderImpl base(ItemLike in);
    SmithingRecipeBuilderImpl base(Ingredient in);

    SmithingRecipeBuilderImpl addon(TagKey<Item> in);
    SmithingRecipeBuilderImpl addon(ItemLike in);
    SmithingRecipeBuilderImpl addon(Ingredient in);
}
