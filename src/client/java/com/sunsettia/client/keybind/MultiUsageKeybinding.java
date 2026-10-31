package com.sunsettia.client.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MultiUsageKeybinding {
    public static final Map<KeyMapping, Supplier<Boolean>> MultiUsageKeybindings = new HashMap<>();

    public static KeyMapping register(String name, InputConstants.Type type, int key, KeyMapping.Category category, Supplier<Boolean> condition) {
        KeyMapping keyMapping = KeyMappingHelper.registerKeyMapping(new KeyMapping(name, type, key, category));
        MultiUsageKeybindings.put(keyMapping, condition);
        return keyMapping;
    }
}
