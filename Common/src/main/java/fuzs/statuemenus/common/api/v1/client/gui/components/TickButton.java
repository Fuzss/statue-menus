package fuzs.statuemenus.common.api.v1.client.gui.components;

import fuzs.statuemenus.common.api.v1.client.gui.screens.AbstractStatueScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.Util;

public class TickButton extends FlatButton {
    private final Component title;
    private final Component clickedTitle;
    private long lastClickedTime;
    protected int lastClickedTicksDelay = 30;

    public TickButton(int x, int y, int width, int height, Component title, Component clickedTitle, OnPress onPress) {
        super(x, y, width, height, 0, 184, AbstractStatueScreen.WIDGETS_LOCATION, title, onPress);
        this.title = title;
        this.clickedTitle = clickedTitle;
    }

    @Override
    public void onPress(InputWithModifiers inputWithModifiers) {
        super.onPress(inputWithModifiers);
        this.lastClickedTime = Util.getMillis();
        this.setMessage(this.clickedTitle);
    }

    protected boolean wasClicked() {
        return Util.getMillis() - this.lastClickedTime < this.lastClickedTicksDelay * 50L;
    }

    @Override
    protected void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        if (this.wasClicked()) {
            this.setMessage(this.clickedTitle);
            int yImage = this.getYImage();
            Font font = Minecraft.getInstance().font;
            int titleWidth = font.width(this.clickedTitle);
            final int startX = (this.width - titleWidth - (titleWidth > 0 ? 4 : 0) - 16) / 2;
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                    this.textureLocation,
                    this.getX() + startX,
                    this.getY() + 2,
                    196,
                    16 + yImage * 16,
                    16,
                    16,
                    256,
                    256,
                    ARGB.white(this.alpha));
        } else {
            this.setMessage(this.title);
        }
    }

    @Override
    protected int getMessageXOffset() {
        if (this.wasClicked()) {
            return (16 + 4) / 2;
        } else {
            return super.getMessageXOffset();
        }
    }
}
