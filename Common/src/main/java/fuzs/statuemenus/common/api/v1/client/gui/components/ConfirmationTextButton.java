package fuzs.statuemenus.common.api.v1.client.gui.components;

import fuzs.statuemenus.common.api.v1.client.gui.screens.StatuePositionScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class ConfirmationTextButton extends ConfirmationButton {
    private final Component defaultMessage;
    private final Component confirmationMessage;

    public ConfirmationTextButton(int x, int y, int width, int height, Component defaultMessage, Component confirmationMessage, OnPress onPress) {
        super(x, y, width, height, StatuePositionScreen.BUTTON_SPRITES, onPress, defaultMessage);
        this.defaultMessage = defaultMessage;
        this.confirmationMessage = confirmationMessage;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        this.setMessage(this.getMessage(this.hasBeenClickedRecently()));
        super.extractContents(graphics, mouseX, mouseY, partialTicks);
    }

    protected Component getMessage(boolean hasBeenClickedRecently) {
        return hasBeenClickedRecently ? this.confirmationMessage : this.defaultMessage;
    }

    @Override
    protected int getLastClickedTicksDelay() {
        return 30;
    }
}
