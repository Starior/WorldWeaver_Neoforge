package org.betterx.wover.testmod.recipe;

import org.betterx.wover.complex.api.equipment.*;
import org.betterx.wover.testmod.entrypoint.TestModWoverRecipe;

import net.minecraft.world.item.*;

import org.jetbrains.annotations.ApiStatus;

public class TestEquipmentSet extends EquipmentSet {
    public static final TestEquipmentSet INSTANCE = new TestEquipmentSet();

    public TestEquipmentSet() {
        super(TestModWoverRecipe.C, "test_equipment_set", ToolTiers.DIAMOND_TOOL, ArmorTiers.TURTLE_ARMOR, Items.STONE);

        add(ToolSlot.PICKAXE_SLOT, PickaxeItem::new);
        add(ToolSlot.AXE_SLOT, AxeItem::new);
        add(ToolSlot.SHOVEL_SLOT, ShovelItem::new);
        add(ToolSlot.HOE_SLOT, HoeItem::new);
        add(ToolSlot.SWORD_SLOT, SwordItem::new);

        add(ArmorSlot.HELMET_SLOT, ArmorItem::new);
        add(ArmorSlot.CHESTPLATE_SLOT, ArmorItem::new);
        add(ArmorSlot.LEGGINGS_SLOT, ArmorItem::new);
        add(ArmorSlot.BOOTS_SLOT, ArmorItem::new);
    }

    @ApiStatus.Internal
    public static void ensureStaticInit() {
        // NO-OP
    }
}
