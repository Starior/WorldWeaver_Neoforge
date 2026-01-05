package org.betterx.wover.datagen.api.provider;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverRegistryContentProvider;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.enchantment.Enchantment;

public abstract class WoverEnchantmentProvider extends WoverRegistryContentProvider<Enchantment> {
    /**
     * Creates a new instance of {@link WoverRegistryContentProvider}.
     *
     * @param modCore The ModCore instance of the Mod that is providing this instance.
     * @param title   The title of the provider. Mainly used for logging.
     */
    public WoverEnchantmentProvider(
            ModCore modCore,
            String title
    ) {
        super(modCore, title, Registries.ENCHANTMENT);
    }

    @Override
    abstract protected void bootstrap(BootstrapContext<Enchantment> context);
}
