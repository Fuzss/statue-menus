package fuzs.statuemenus.common.api.v1.client.gui.components;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;

public abstract class ConfirmationButton extends ImageTextButton {
    private long lastClickedTime;

    public ConfirmationButton(int x, int y, int width, int height, WidgetSprites sprites, OnPress onPress) {
        super(x, y, width, height, sprites, onPress);
    }

    public ConfirmationButton(int x, int y, int width, int height, WidgetSprites sprites, OnPress onPress, Component message) {
        super(x, y, width, height, sprites, onPress, message);
    }

    @Override
    public void onPress(InputWithModifiers input) {
        super.onPress(input);
        this.lastClickedTime = Util.getMillis();
    }

    protected final boolean hasBeenClickedRecently() {
        return Util.getMillis() - this.lastClickedTime < this.getLastClickedTicksDelay() * 50L;
    }

    protected abstract int getLastClickedTicksDelay();
}
