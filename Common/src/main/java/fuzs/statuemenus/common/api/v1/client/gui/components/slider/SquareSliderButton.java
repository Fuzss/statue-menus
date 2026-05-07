package fuzs.statuemenus.common.api.v1.client.gui.components.slider;

import fuzs.statuemenus.common.impl.world.inventory.StatuePoses;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.util.Mth;

public abstract class SquareSliderButton extends AbstractSquareSliderButton implements ClearableSliderButton {
    private boolean dirty;

    public SquareSliderButton(int x, int y, int width, int height) {
        super(x, y, width, height, CommonComponents.EMPTY);
    }

    @Override
    protected double getValueKeyInterval() {
        return 0.035;
    }

    protected double getSnapInterval() {
        return StatuePoses.DEGREES_SNAP_INTERVAL;
    }

    @Override
    protected void setHorizontalValue(double newValue) {
        double value = StatuePoses.snapValue(Mth.clamp(newValue, 0.0, 1.0), this.getSnapInterval());
        super.setHorizontalValue(value);
    }

    @Override
    protected void setVerticalValue(double newValue) {
        double value = StatuePoses.snapValue(Mth.clamp(newValue, 0.0, 1.0), this.getSnapInterval());
        super.setVerticalValue(value);
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
