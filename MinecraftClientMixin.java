package com.azr.client.Mixin;

import com.azr.client.AzrClient;
import com.azr.client.ClickGUI;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.lwjgl.glfw.GLFW;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        // KillAura ve diğer modüllerin çalışması için onTick'i tetikliyoruz
        AzrClient.onTick();
        
        MinecraftClient mc = MinecraftClient.getInstance();
        
        // Sağ Shift (344) tuşuna basıldığında menüyü açar
        // mc.world != null kontrolü sayesinde sadece oyunun içindeyken çalışır
        if (mc.world != null && GLFW.glfwGetKey(mc.getWindow().getHandle(), 344) == 1) {
            if (mc.currentScreen == null) {
                mc.setScreen(new ClickGUI());
            }
        }
    }
}
