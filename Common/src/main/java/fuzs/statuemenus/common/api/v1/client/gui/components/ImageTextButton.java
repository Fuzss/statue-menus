package fuzs.statuemenus.common.api.v1.client.gui.components;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;

public class ImageTextButton extends ImageButton {

    public ImageTextButton(int x, int y, int width, int height, WidgetSprites sprites, OnPress onPress) {
        this(x, y, width, height, sprites, onPress, CommonComponents.EMPTY);
    }

    public ImageTextButton(int x, int y, int width, int height, WidgetSprites sprites, OnPress onPress, Component message) {
        super(x, y, width, height, sprites, onPress, message);
        this.setMessage(message);
    }

    /**
     * @see GuiGraphicsExtractor#centeredText(Font, String, int, int, int)
     */
    public static void centeredText(GuiGraphicsExtractor guiGraphics, Font font, Component component, int x, int y, int color, boolean dropShadow) {
        guiGraphics.text(font, component, x - font.width(component) / 2, y, color, dropShadow);
    }

    @Override
    public Component getMessage() {
        return this.active && this.isHoveredOrFocused() ? this.message : this.inactiveMessage;
    }

    @Override
    public void setMessage(Component message) {
        this.message = ComponentUtils.mergeStyles(message, Style.EMPTY.withColor(ChatFormatting.YELLOW));
        this.inactiveMessage = ComponentUtils.mergeStyles(message, Style.EMPTY.withColor(0x404040));
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.extractContents(guiGraphics, mouseX, mouseY, partialTicks);
        this.extractDefaultLabel(guiGraphics);
    }

    protected void extractDefaultLabel(GuiGraphicsExtractor guiGraphics) {
        Font font = Minecraft.getInstance().font;
        ImageTextButton.centeredText(guiGraphics,
                font,
                this.getMessage(),
                this.getX() + this.width / 2,
                this.getY() + (this.height - 8) / 2,
                -1,
                false);
    }
}
