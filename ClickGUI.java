package com.azr.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class ClickGUI extends Screen {
    public ClickGUI() {
        super(Text.literal("Azr Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Blur olmaması için renderBackground'ı çağırmıyoruz.
        // Sadece menünün açıldığını anlamak için köşeye bir yazı:
        context.drawTextWithShadow(this.textRenderer, "AZR MENU AKTIF", 10, 10, 0xFFFFFF);
        
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false; // Menü açıkken oyun arkada akmaya devam eder
    }
}
