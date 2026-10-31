package com.sunsettia.client.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.InputConstants;
import com.sunsettia.client.keybind.MultiUsageKeybinding;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

@Mixin(KeyMapping.class)
public class MultiUsageKeybindingMixin {
    @Inject(method = "forAllKeyMappings", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z", shift = At.Shift.AFTER), cancellable = true)
    private static void sunsettia$applyMultiUsageKeybinding(InputConstants.Key key, Consumer<KeyMapping> operation, CallbackInfo ci, @Local(name = "keyMappings") List<KeyMapping> keyMappings) {
        MultiUsageKeybinding.MultiUsageKeybindings.keySet().forEach((keyMapping -> {
            if (keyMappings.contains(keyMapping) && MultiUsageKeybinding.MultiUsageKeybindings.get(keyMapping).get()) {
                operation.accept(keyMapping);
                ci.cancel();
            }
        }));
    }
}
