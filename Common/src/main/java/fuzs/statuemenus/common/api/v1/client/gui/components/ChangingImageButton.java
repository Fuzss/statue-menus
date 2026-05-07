package fuzs.statuemenus.common.api.v1.client.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.util.Util;

public class ChangingImageButton extends ImageButton {
    protected final WidgetSprites originalSprites;
    protected final WidgetSprites changingSprites;
    private long lastClickedTime;

    public ChangingImageButton(int x, int y, int width, int height, WidgetSprites sprites, WidgetSprites changingSprites, OnPress onPress) {
        super(x, y, width, height, sprites, onPress);
        this.originalSprites = sprites;
        this.changingSprites = changingSprites;
    }

    @Override
    public void onPress(InputWithModifiers inputWithModifiers) {
        super.onPress(inputWithModifiers);
        this.lastClickedTime = Util.getMillis();
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        this.sprites = this.getSprites(this.hasBeenClickedRecently());
        super.extractContents(graphics, mouseX, mouseY, partialTicks);
    }

    protected WidgetSprites getSprites(boolean hasBeenClickedRecently) {
        return hasBeenClickedRecently ? this.changingSprites : this.originalSprites;
    }

    protected int getLastClickedTicksDelay() {
        return 20;
    }

    private boolean hasBeenClickedRecently() {
        return Util.getMillis() - this.lastClickedTime < this.getLastClickedTicksDelay() * 50L;
    }
}
