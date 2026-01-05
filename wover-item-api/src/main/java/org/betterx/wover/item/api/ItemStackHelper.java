package org.betterx.wover.item.api;

import org.betterx.wover.common.item.api.ItemWithCustomStack;
import org.betterx.wover.state.api.WorldState;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;

public class ItemStackHelper {
    public static ItemStack callItemStackSetupIfPossible(ItemStack stack) {
        return callItemStackSetupIfPossible(stack, WorldState.registryAccess());
    }

    public static ItemStack callItemStackSetupIfPossible(ItemStack stack, HolderLookup.Provider provider) {
        if (stack.getItem() instanceof ItemWithCustomStack sStack && provider != null) {
            sStack.setupItemStack(stack, provider);
        }
        return stack;
    }
}
