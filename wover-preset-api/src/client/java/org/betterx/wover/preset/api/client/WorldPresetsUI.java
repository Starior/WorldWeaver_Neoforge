package org.betterx.wover.preset.api.client;

import org.betterx.wover.preset.impl.client.WorldPresetsClientImpl;

import net.minecraft.client.gui.screens.worldselection.PresetEditor;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


/**
 * API to register custom Setup UIs for {@link net.minecraft.world.level.levelgen.presets.WorldPreset}s
 */
@OnlyIn(Dist.CLIENT)
public class WorldPresetsUI {
    public interface PresetEditorGetter {
        PresetEditor get(Holder<WorldPreset> holder);
    }

    /**
     * Registers a custom Setup UI for a {@link WorldPreset}.
     *
     * @param key         The key of the preset.
     * @param setupScreen The setup screen.
     */
    public static void registerCustomUI(ResourceKey<WorldPreset> key, PresetEditor setupScreen) {
        WorldPresetsClientImpl.registerCustomUI(key, setupScreen);
    }

    public static void registerCustomUI(WorldPresetsUI.PresetEditorGetter getter) {
        WorldPresetsClientImpl.registerCustomUI(getter);
    }

    public static boolean isKey(Holder<WorldPreset> holder, ResourceKey<WorldPreset> keyToTest) {
        return WorldPresetsClientImpl.isKey(holder, keyToTest);
    }
}
