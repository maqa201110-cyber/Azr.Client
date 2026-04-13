package com.azr.client.mixin;

import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityVelocityUpdateS2CPacket.class)
public class EntityVelocityUpdateS2CPacketMixin {
    @Inject(method = "apply*", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfo ci) {
        // Bu satır gelen itme paketini iptal eder
        ci.cancel();
    }
}
