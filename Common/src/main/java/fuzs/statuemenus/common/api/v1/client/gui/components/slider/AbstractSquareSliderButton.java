package fuzs.statuemenus.common.api.v1.client.gui.components.slider;

import com.mojang.blaze3d.platform.cursor.CursorType;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
import fuzs.puzzleslib.common.api.util.v1.CommonHelper;
import fuzs.statuemenus.common.impl.StatueMenus;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;

public abstract class AbstractSquareSliderButton extends AbstractSliderButton {
    public static final Identifier SLIDER_SPRITE = StatueMenus.id("container/statue/slider");
    public static final WidgetSprites SLIDER_HANDLE_SPRITES = new WidgetSprites(StatueMenus.id(
            "container/statue/slider_handle"),
            StatueMenus.id("container/statue/slider_handle_disabled"),
            StatueMenus.id("container/statue/slider_handle_highlighted"));
    protected static final int SLIDER_HANDLE_SIZE = 13;

    protected double horizontalValue;
    protected double verticalValue;

    public AbstractSquareSliderButton(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message, 0.0);
        this.refreshValues();
    }

    public abstract void refreshValues();

    @Override
    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        int sliderX = (int) (this.horizontalValue * (double) (this.width - SLIDER_HANDLE_SIZE - 2));
        int sliderY = (int) (this.verticalValue * (double) (this.height - SLIDER_HANDLE_SIZE - 2));
        if (!this.active || !this.isHoveredOrFocused()
                || !this.isHorizontalValueLocked() && !this.isVerticalValueLocked()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                    SLIDER_SPRITE,
                    this.getX(),
                    this.getY(),
                    this.width,
                    this.height,
                    ARGB.white(this.alpha));
        } else if (this.isHorizontalValueLocked() && this.isVerticalValueLocked()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                    SLIDER_SPRITE,
                    this.getX() + sliderX,
                    this.getY() + sliderY,
                    SLIDER_HANDLE_SIZE + 2,
                    SLIDER_HANDLE_SIZE + 2,
                    ARGB.white(this.alpha));
        } else if (this.isHorizontalValueLocked()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                    SLIDER_SPRITE,
                    this.getX() + sliderX,
                    this.getY(),
                    SLIDER_HANDLE_SIZE + 2,
                    this.height,
                    ARGB.white(this.alpha));
        } else {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                    SLIDER_SPRITE,
                    this.getX(),
                    this.getY() + sliderY,
                    this.width,
                    SLIDER_HANDLE_SIZE + 2,
                    ARGB.white(this.alpha));
        }

        Identifier handleSprite = SLIDER_HANDLE_SPRITES.get(this.active, this.isHovered || this.canChangeValue);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                handleSprite,
                this.getX() + 1 + sliderX,
                this.getY() + 1 + sliderY,
                SLIDER_HANDLE_SIZE,
                SLIDER_HANDLE_SIZE,
                ARGB.white(this.alpha));
        this.handleCursor(graphics);
    }

    @Override
    protected void handleCursor(GuiGraphicsExtractor graphics) {
        if (this.isHovered()) {
            CursorType cursorType;
            if (this.isActive() && (!this.isHorizontalValueLocked() || !this.isVerticalValueLocked())) {
                if (this.dragging) {
                    if (this.isHorizontalValueLocked()) {
                        cursorType = CursorTypes.RESIZE_NS;
                    } else if (this.isVerticalValueLocked()) {
                        cursorType = CursorTypes.RESIZE_EW;
                    } else {
                        cursorType = CursorTypes.RESIZE_ALL;
                    }
                } else {
                    cursorType = CursorTypes.POINTING_HAND;
                }
            } else {
                cursorType = CursorTypes.NOT_ALLOWED;
            }

            graphics.requestCursor(cursorType);
        }
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.isSelection()) {
            this.canChangeValue = !this.canChangeValue;
            return true;
        } else {
            if (this.canChangeValue) {
                if (event.isLeft()) {
                    this.setHorizontalValue(this.horizontalValue - this.getValueKeyInterval());
                    return true;
                } else if (event.isRight()) {
                    this.setHorizontalValue(this.horizontalValue + this.getValueKeyInterval());
                    return true;
                } else if (event.isUp()) {
                    this.setVerticalValue(this.verticalValue - this.getValueKeyInterval());
                    return true;
                } else if (event.isDown()) {
                    this.setVerticalValue(this.verticalValue + this.getValueKeyInterval());
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    protected double getValueKeyInterval() {
        return 1.0;
    }

    @Override
    public void setValueFromMouse(MouseButtonEvent event) {
        this.setHorizontalValue(
                (event.x() - (double) (this.getX() + 8)) / (double) (this.width - SLIDER_HANDLE_SIZE - 2));
        this.setVerticalValue(
                (event.y() - (double) (this.getY() + 8)) / (double) (this.height - SLIDER_HANDLE_SIZE - 2));
    }

    @Override
    protected void setValue(double newValue) {
        throw new UnsupportedOperationException();
    }

    protected void setHorizontalValue(double newValue) {
        double oldValue = this.horizontalValue;
        if (!this.isHorizontalValueLocked()) {
            this.horizontalValue = Mth.clamp(newValue, 0.0, 1.0);
        }

        if (oldValue != this.horizontalValue) {
            this.applyValue();
        }
    }

    protected boolean isHorizontalValueLocked() {
        return CommonHelper.hasAltDown();
    }

    protected void setVerticalValue(double newValue) {
        double oldValue = this.verticalValue;
        if (!this.isVerticalValueLocked()) {
            this.verticalValue = Mth.clamp(newValue, 0.0, 1.0);
        }

        if (oldValue != this.verticalValue) {
            this.applyValue();
        }
    }

    protected boolean isVerticalValueLocked() {
        return CommonHelper.hasShiftDown();
    }
}
