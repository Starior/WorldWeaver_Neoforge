package org.betterx.wover.core.mixin.registry;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Datapack-biome holders from BetterX mods can be resolved via
 * {@code net.minecraft.core.RegistrySetBuilder.UniversalLookup#getOrCreate} during
 * a {@code VanillaRegistries} / {@code RegistrySetBuilder#build(RegistryAccess)} pass without
 * being "claimed" by a registry stub's {@code collectRegisteredValues}, which leaves spurious
 * "Unreferenced key" errors. This only discards the synthetic stand-alone references in the
 * builder's universal-lookup map for validation, not registries in the running game.
 * <p>
 * No {@code @Shadow} of {@code BuildState#lookup}: the released game uses obfuscated (SRG) field
 * names; this mod ships without a Mixin refmap, so the lookup field is found by scanning for the
 * universal-lookup map of biome resource keys.
 */
@Mixin(
        targets = "net.minecraft.core.RegistrySetBuilder$BuildState"
)
public class RegistrySetBuilderBuildStateMixin {
    private static final Set<String> UNREFERENCED_BIOME_LOOKUP_NAMESPACES = Set.of("betternether", "undergroundworlds");

    @Inject(method = "reportNotCollectedHolders", at = @At("HEAD"))
    private void wover$pruneUnreferencedBetterXDatapackBiomeLookups(CallbackInfo ci) {
        wover$pruneBiomeHoldersInUniversalLookup((Object) this);
    }

    @SuppressWarnings("unchecked")
    private static void wover$pruneBiomeHoldersInUniversalLookup(Object buildState) {
        try {
            for (Field bf : buildState.getClass().getDeclaredFields()) {
                bf.setAccessible(true);
                final Object component = bf.get(buildState);
                if (component == null) {
                    continue;
                }
                for (Field hf : component.getClass().getDeclaredFields()) {
                    if (!Map.class.isAssignableFrom(hf.getType())) {
                        continue;
                    }
                    hf.setAccessible(true);
                    final Map<ResourceKey<Object>, Object> m = (Map<ResourceKey<Object>, Object>) hf.get(component);
                    if (m == null || m.isEmpty()) {
                        continue;
                    }
                    final Object anyKey = m.keySet().iterator().next();
                    if (!(anyKey instanceof ResourceKey<?> k) || !k.isFor(Registries.BIOME)) {
                        continue;
                    }
                    m.keySet()
                            .removeIf(
                                    key -> key.isFor(Registries.BIOME) && UNREFERENCED_BIOME_LOOKUP_NAMESPACES
                                            .contains(key.location()
                                                    .getNamespace())
                            );
                    return;
                }
            }
        } catch (ReflectiveOperationException | ClassCastException ignored) {
        }
    }
}
