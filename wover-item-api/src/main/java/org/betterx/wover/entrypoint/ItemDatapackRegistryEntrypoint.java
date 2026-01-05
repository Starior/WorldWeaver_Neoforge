package org.betterx.wover.entrypoint;

import org.betterx.wover.core.api.registry.DatapackRegistryEntrypoint;
import org.betterx.wover.enchantment.impl.EnchantmentManagerImpl;

public class ItemDatapackRegistryEntrypoint implements DatapackRegistryEntrypoint {
    @Override
    public void registerDatapackRegistries() {
        EnchantmentManagerImpl.initialize();
    }
}
