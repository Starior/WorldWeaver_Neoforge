package org.betterx.wover.enchantment.api;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EnchantmentKey {
    /**
     * The key for the {@link Enchantment} you can use to reference it.
     *
     * @return The key
     */
    ResourceKey<Enchantment> key();

    /**
     * Gets the {@link Holder} for the {@link Enchantment} from the given getter.
     *
     * @param getter The getter to get the holder from or {@code null}
     * @return The holder for the {@link Enchantment} or {@code null} if it is not present
     */
    @Nullable
    Holder<Enchantment> getHolder(@Nullable HolderGetter<Enchantment> getter);

    /**
     * Gets the {@link Holder} for the {@link Enchantment} from the given getter.
     *
     * @param access The registry access to get the holder from
     * @return The holder for the {@link Enchantment} or {@code null} if it is not present
     */
    @Nullable
    Holder<Enchantment> getHolder(@Nullable RegistryAccess access);

    /**
     * Gets the {@link Holder} for the {@link Enchantment} from the given getter.
     *
     * <p>
     * This method internally looks up {@link Registries#ENCHANTMENT}. If you need to retrieve
     * a lot of holders, it is recommended to manually lookup the
     * Registry first and use {@link #getHolder(HolderGetter)} instead.
     *
     * @param context The {@link BootstrapContext} to get the holder from
     * @return The holder for the {@link Enchantment} or {@code null} if it is not present
     */
    Holder<Enchantment> getHolder(@NotNull BootstrapContext<?> context);

    /**
     * Registers the {@link Enchantment} with the given builder to the given context.
     *
     * @param context The context to register the {@link Enchantment} with
     * @param builder The builder to use to build the {@link Enchantment}
     */
    void register(@NotNull BootstrapContext<Enchantment> context, Enchantment.Builder builder);
}
