package fuzs.statuemenus.common.api.v1.client.gui.components.slider;

import fuzs.statuemenus.common.api.v1.client.gui.screens.StatuePositionScreen;
import fuzs.statuemenus.common.impl.world.inventory.StatuePoses;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public abstract class ImageSliderButton extends AbstractSliderButton implements ClearableSliderButton {
    private boolean dirty;

    public ImageSliderButton(int x, int y, int width, int height, double initialValue) {
        this(x, y, width, height, CommonComponents.EMPTY, initialValue);
    }

    public ImageSliderButton(int x, int y, int width, int height, Component component, double initialValue) {
        super(x, y, width, height, component, initialValue);
    }

    public void setRawValue(double value) {
        this.value = value;
    }

    @Override
    public Identifier getSprite() {
        return StatuePositionScreen.BUTTON_SPRITES.disabled();
    }

    @Override
    public Identifier getHandleSprite() {
        return StatuePositionScreen.BUTTON_SPRITES.get(true, this.active && (this.isHovered || this.canChangeValue));
    }

    @Override
    protected void setValue(double newValue) {
        double value = StatuePoses.snapValue(Mth.clamp(newValue, 0.0, 1.0), this.getSnapInterval());
        super.setValue(value);
    }

    protected double getSnapInterval() {
        return -1.0;
    }

    @Override
    protected void updateMessage() {
        // NO-OP
    }

    @Override
    public void onRelease(MouseButtonEvent event) {
        // We use #onRelease instead of directly applying in #applyValue.
        // Otherwise, the armor stand will glitch out visually since the server constantly sends outdated values.
        super.onRelease(event);
        this.clearDirty(this.isDirty());
    }

    @Override
    protected void applyValue() {
        this.dirty = true;
    }

    @Override
    public boolean isDirty() {
        return this.dirty;
    }

    @Override
    public void clearDirty(boolean isDirty) {
        this.dirty = false;
    }
}
