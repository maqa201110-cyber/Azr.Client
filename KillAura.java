package com.azr.client.modules.combat;

import com.azr.client.Module;
import com.azr.client.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KillAura extends Module {
    
    private double range = 3.8; // Vuruş mesafesi
    private long lastAttack = 0;
    private int cps = 12; // Saniyedeki vuruş hızı

    public KillAura() {
        super("KillAura", "Etrafındaki rakiplere otomatik vurur", 0, ModuleCategory.COMBAT);
    }

    // Bu fonksiyon her oyun karesinde (frame) çalışır
    public void onTick() {
        if (!this.isEnabled()) return;

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        // 1. En yakın hedefi bul
        List<Entity> targets = mc.world.getEntities().iterator().next().getCommandSource().getWorld().getEntities().iterator().next().getCommandSource().getWorld().getEntities()
            .stream()
            .filter(e -> e instanceof LivingEntity && e != mc.player)
            .filter(e -> mc.player.distanceTo(e) <= range)
            .filter(e -> ((LivingEntity) e).isAlive())
            .sorted(Comparator.comparingDouble(e -> mc.player.distanceTo(e)))
            .collect(Collectors.toList());

        if (targets.isEmpty()) return;

        LivingEntity target = (LivingEntity) targets.get(0);

        // 2. Vuruş zamanı kontrolü (CPS ayarı)
        if (System.currentTimeMillis() - lastAttack >= 1000 / cps) {
            attack(mc, target);
            lastAttack = System.currentTimeMillis();
        }
    }

    private void attack(MinecraftClient mc, LivingEntity target) {
        // Hedefe bak (Rotasyon - Basit versiyon)
        mc.player.lookAt(target.getCommandSource().getAnchor(), target.getPos());
        
        // Vur
        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
