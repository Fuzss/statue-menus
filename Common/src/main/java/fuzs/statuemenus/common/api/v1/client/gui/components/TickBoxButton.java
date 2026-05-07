package fuzs.statuemenus.common.api.v1.client.gui.components;

import fuzs.statuemenus.common.api.v1.client.gui.screens.AbstractStatueScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

import java.util.function.BooleanSupplier;

public class TickBoxButton extends Button {
    private final int textMargin;
    private final BooleanSupplier supplier;

    public TickBoxButton(int posX, int posY, int textMargin, int textWidth, Component component, BooleanSupplier supplier, OnPress onPress) {
        super(posX, posY, 20 + textMargin + textWidth, 20, component, onPress, DEFAULT_NARRATION);
        this.textMargin = textMargin;
        this.supplier = supplier;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, AbstractStatueScreen.WIDGETS_LOCATION,
                this.getX() + 2,
                this.getY() + 2,
                196,
                this.isHoveredOrFocused() ? 16 : 0,
                16,
                16,
                256,
                256,
                ARGB.white(this.alpha));
        if (this.supplier.getAsBoolean()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, AbstractStatueScreen.WIDGETS_LOCATION,
                    this.getX() + 2,
                    this.getY() + 2,
                    196,
                    32 + (this.isHoveredOrFocused() ? 16 : 0),
                    16,
                    16,
                    256,
                    256,
                    ARGB.white(this.alpha));
        }
        final int textColor =
                this.active ? (this.isHoveredOrFocused() ? ChatFormatting.YELLOW.getColor() : 16777215) : 10526880;
        Font font = Minecraft.getInstance().font;
        guiGraphics.text(font,
                this.getMessage(),
                this.getX() + 20 + this.textMargin,
                this.getY() + 2 + 4,
                ARGB.color(this.alpha, textColor));
    }
}
