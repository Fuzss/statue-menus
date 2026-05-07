package fuzs.statuemenus.common.api.v1.client.gui.components;

import fuzs.statuemenus.common.impl.StatueMenus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.DyeColor;

public abstract class TickTextButton extends ImageButton {
    public static final WidgetSprites UNTICKED_BUTTON_SPRITES = new WidgetSprites(StatueMenus.id(
            "container/statue/unticked_button"), StatueMenus.id("container/statue/unticked_button_highlighted"));
    public static final WidgetSprites TICKED_BUTTON_SPRITES = new WidgetSprites(StatueMenus.id(
            "container/statue/ticked_button"), StatueMenus.id("container/statue/ticked_button_highlighted"));

    private final int textMargin;

    public TickTextButton(int posX, int posY, int textMargin, int textWidth, OnPress onPress, Component component) {
        super(posX, posY, 20 + textMargin + textWidth, 20, UNTICKED_BUTTON_SPRITES, onPress, component);
        this.textMargin = textMargin;
    }

    protected abstract boolean isTicked();

    protected WidgetSprites getSprites(boolean isTicked) {
        return isTicked ? TICKED_BUTTON_SPRITES : UNTICKED_BUTTON_SPRITES;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        Identifier sprite = this.getSprites(this.isTicked()).get(this.active, this.isHoveredOrFocused());
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                sprite,
                this.getX() + 2,
                this.getY() + 2,
                16,
                16,
                ARGB.white(this.alpha));
        Font font = Minecraft.getInstance().font;
        guiGraphics.text(font,
                this.getMessage(),
                this.getX() + 20 + this.textMargin,
                this.getY() + 2 + 4,
                ARGB.color(this.alpha, this.getTextColor(this.active, this.isHoveredOrFocused())));
    }

    protected int getTextColor(boolean isActive, boolean isHovered) {
        if (!isActive) {
            return 0XFFA0A0A0;
        } else if (isHovered) {
            return DyeColor.YELLOW.getTextColor();
        } else {
            return -1;
        }
    }
}
