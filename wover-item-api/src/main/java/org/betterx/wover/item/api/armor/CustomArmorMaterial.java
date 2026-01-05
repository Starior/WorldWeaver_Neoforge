package org.betterx.wover.item.api.armor;

import org.betterx.wover.core.api.registry.BuiltInRegistryManager;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class CustomArmorMaterial {
    public static CustomArmorMaterial.Builder start(ResourceLocation location) {
        return new CustomArmorMaterial.Builder(location);
    }

    public static class Builder {
        private final ResourceLocation location;
        private final EnumMap<ArmorItem.Type, Integer> defense;
        private int enchantmentValue;
        private Holder<SoundEvent> equipSound;
        private float toughness;
        private float knockbackResistance;
        private Supplier<Ingredient> repairIngredientSupplier;
        List<ArmorMaterial.Layer> layers;

        private Builder(ResourceLocation location) {
            this.location = location;
            this.defense = new EnumMap<>(ArmorItem.Type.class);
        }

        public Builder defense(int boots, int leggings, int chestplate, int helmet, int body) {
            defense(ArmorItem.Type.BOOTS, boots);
            defense(ArmorItem.Type.LEGGINGS, leggings);
            defense(ArmorItem.Type.CHESTPLATE, chestplate);
            defense(ArmorItem.Type.HELMET, helmet);
            defense(ArmorItem.Type.BODY, body);
            return this;
        }

        public Builder defense(ArmorItem.Type type, int defense) {
            this.defense.put(type, defense);
            return this;
        }

        public Builder enchantmentValue(int enchantmentValue) {
            this.enchantmentValue = enchantmentValue;
            return this;
        }

        public Builder equipSound(Holder<SoundEvent> equipSound) {
            this.equipSound = equipSound;
            return this;
        }

        public Builder toughness(float toughness) {
            this.toughness = toughness;
            return this;
        }

        public Builder knockbackResistance(float knockbackResistance) {
            this.knockbackResistance = knockbackResistance;
            return this;
        }

        public Builder repairIngredientSupplier(Supplier<Ingredient> repairIngredientSupplier) {
            this.repairIngredientSupplier = repairIngredientSupplier;
            return this;
        }

        public Builder layers(List<ArmorMaterial.Layer> layers) {
            this.layers = layers;
            return this;
        }

        protected void validate() throws IllegalStateException {
            if (defense.size() != ArmorItem.Type.values().length) {
                throw new IllegalStateException("Defense values must be set for all armor types");
            }

            if (enchantmentValue < 0) {
                throw new IllegalStateException("Enchantment value must be non-negative");
            }

            if (equipSound == null) {
                throw new IllegalStateException("Equip sound must be set");
            }

            if (toughness < 0) {
                throw new IllegalStateException("Toughness must be non-negative");
            }

            if (knockbackResistance < 0) {
                throw new IllegalStateException("Knockback resistance must be non-negative");
            }

            if (repairIngredientSupplier == null) {
                throw new IllegalStateException("Repair ingredient supplier must be set");
            }
        }

        public ArmorMaterial build() {
            if (layers == null) {
                layers = List.of(new ArmorMaterial.Layer(location));
            }
            validate();
            return new ArmorMaterial(
                    defense, enchantmentValue, equipSound,
                    repairIngredientSupplier, layers, toughness,
                    knockbackResistance
            );
        }

        public Holder<ArmorMaterial> buildAndRegister() {
            return BuiltInRegistryManager.registerForHolder(
                    BuiltInRegistries.ARMOR_MATERIAL,
                    location,
                    this.build()
            );
        }
    }
}
