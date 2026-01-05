package org.betterx.wover.complex.api.equipment;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public enum ArmorSlot {
    HELMET_SLOT(0, "helmet", ArmorItem.Type.HELMET, RecipeCategory.COMBAT),
    CHESTPLATE_SLOT(1, "chestplate", ArmorItem.Type.CHESTPLATE, RecipeCategory.COMBAT),
    LEGGINGS_SLOT(2, "leggings", ArmorItem.Type.LEGGINGS, RecipeCategory.COMBAT),
    BOOTS_SLOT(3, "boots", ArmorItem.Type.BOOTS, RecipeCategory.COMBAT);


    public interface PropertiesBuilder {
        Item.Properties build(ArmorSlot slot, ArmorTier tier);
    }

    public final RecipeCategory category;
    public final String name;
    public final int slotIndex;
    public final ArmorItem.Type armorType;
    private final PropertiesBuilder propertiesBuilder;

    ArmorSlot(
            int slotIndex,
            String name,
            ArmorItem.Type armorType,
            RecipeCategory category
    ) {
        this.name = name;
        this.category = category;
        this.slotIndex = slotIndex;
        this.armorType = armorType;
        this.propertiesBuilder = (slot, material) -> {
            var values = material.getValues(slot);
            if (values == null)
                throw new IllegalArgumentException("No values for slot " + slot + " in tier " + material);
            return new Item.Properties().durability(slot.armorType.getDurability(values.durability()));
        };
    }

    public Item.Properties buildProperties(ArmorTier tier) {
        return propertiesBuilder.build(this, tier);
    }
}
