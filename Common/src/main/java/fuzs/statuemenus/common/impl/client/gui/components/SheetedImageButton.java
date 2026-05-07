package fuzs.statuemenus.common.impl.client.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

@Deprecated
public class SheetedImageButton extends Button {
    protected final Identifier textureSheet;
    protected final int xTexStart;
    protected final int yTexStart;
    protected final int yDiffTex;
    protected final int textureWidth;
    protected final int textureHeight;

    public SheetedImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart, Identifier textureSheet, OnPress onPress) {
        this(x, y, width, height, xTexStart, yTexStart, height, textureSheet, onPress);
    }

    public SheetedImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffTex, Identifier textureSheet, OnPress onPress) {
        this(x, y, width, height, xTexStart, yTexStart, yDiffTex, textureSheet, 256, 256, onPress);
    }

    private SheetedImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffTex, Identifier textureSheet, int textureWidth, int textureHeight, OnPress onPress) {
        this(x,
                y,
                width,
                height,
                xTexStart,
                yTexStart,
                yDiffTex,
                textureSheet,
                textureWidth,
                textureHeight,
                onPress,
                CommonComponents.EMPTY);
    }

    private SheetedImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffTex, Identifier textureSheet, int textureWidth, int textureHeight, OnPress onPress, Component message) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.xTexStart = xTexStart;
        this.yTexStart = yTexStart;
        this.yDiffTex = yDiffTex;
        this.textureSheet = textureSheet;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                this.textureSheet,
                this.getX(),
                this.getY(),
                this.xTexStart,
                this.yTexStart + this.yDiffTex * this.getTextureY(),
                this.width,
                this.height,
                this.textureWidth,
                this.textureHeight,
                ARGB.white(this.alpha));
    }

    protected int getTextureY() {
        return !this.isActive() ? 2 : this.isHoveredOrFocused() ? 1 : 0;
    }
}
