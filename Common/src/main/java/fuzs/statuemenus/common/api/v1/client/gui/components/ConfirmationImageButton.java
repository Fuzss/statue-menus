package fuzs.statuemenus.common.api.v1.client.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.WidgetSprites;

public class ConfirmationImageButton extends ConfirmationButton {
    private final WidgetSprites defaultSprites;
    private final WidgetSprites confirmationSprites;

    public ConfirmationImageButton(int x, int y, int width, int height, WidgetSprites defaultSprites, WidgetSprites confirmationSprites, OnPress onPress) {
        super(x, y, width, height, defaultSprites, onPress);
        this.defaultSprites = defaultSprites;
        this.confirmationSprites = confirmationSprites;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        this.sprites = this.getSprites(this.hasBeenClickedRecently());
        super.extractContents(graphics, mouseX, mouseY, partialTicks);
    }

    protected WidgetSprites getSprites(boolean hasBeenClickedRecently) {
        return hasBeenClickedRecently ? this.confirmationSprites : this.defaultSprites;
    }

    @Override
    protected int getLastClickedTicksDelay() {
        return 20;
    }

    @Override
    protected void extractDefaultLabel(GuiGraphicsExtractor guiGraphics) {
        // NO-OP
    }
}
