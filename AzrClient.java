package com.azr.client;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.azr.client.modules.combat.KillAura;

public class AzrClient implements ModInitializer {
    public static final String NAME = "Azr Client";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    // KillAura hilesini sisteme tanıtıyoruz
    public static final KillAura killAura = new KillAura();

    @Override
    public void onInitialize() {
        LOGGER.info(NAME + " başarıyla başlatıldı!");
    }
    
    // Oyunun her karesinde KillAura'nın çalışıp çalışmadığını kontrol eder
    public static void onTick() {
        if (killAura != null && killAura.isEnabled()) {
            killAura.onTick();
        }
    }
}
