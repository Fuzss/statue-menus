package fuzs.statuemenus.common.api.v1.client.gui.screens;

import fuzs.statuemenus.common.api.v1.client.gui.components.ConfirmationTextButton;
import fuzs.statuemenus.common.api.v1.network.client.data.DataSyncHandler;
import fuzs.statuemenus.common.api.v1.world.inventory.StatueHolder;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class StatueButtonsScreen extends StatueWidgetsScreen {

    public StatueButtonsScreen(StatueHolder holder, Inventory inventory, Component component, DataSyncHandler dataSyncHandler) {
        super(holder, inventory, component, dataSyncHandler);
    }

    protected class SingleButtonWidget extends ArmorStandWidget {
        private final Component defaultMessage;
        private final Component descriptionComponent;
        private final Component confirmationMessage;
        private final Button.OnPress onPress;

        public SingleButtonWidget(Component defaultMessage, Component descriptionComponent, Component confirmationMessage, Button.OnPress onPress) {
            this.defaultMessage = defaultMessage;
            this.descriptionComponent = descriptionComponent;
            this.confirmationMessage = confirmationMessage;
            this.onPress = onPress;
        }

        @Override
        public void init(int posX, int posY) {
            super.init(posX, posY);
            this.addRenderableWidget(new ConfirmationTextButton(posX,
                    posY + 1,
                    194,
                    20,
                    this.defaultMessage,
                    this.confirmationMessage,
                    this.onPress)).setTooltip(Tooltip.create(this.descriptionComponent));
        }
    }

    protected class DoubleButtonWidget extends ArmorStandWidget {
        private final Component leftDefaultMessage;
        private final Component rightDefaultMessage;
        private final Component leftDescriptionComponent;
        private final Component rightDescriptionComponent;
        private final Component leftConfirmationMessage;
        private final Component rightConfirmationMessage;
        private final Button.OnPress leftOnPress;
        private final Button.OnPress rightOnPress;

        public DoubleButtonWidget(Component leftDefaultMessage, Component rightDefaultMessage, Component leftDescriptionComponent, Component rightDescriptionComponent, Component clickedTitle, Button.OnPress leftOnPress, Button.OnPress rightOnPress) {
            this(leftDefaultMessage,
                    rightDefaultMessage,
                    leftDescriptionComponent,
                    rightDescriptionComponent,
                    clickedTitle,
                    clickedTitle,
                    leftOnPress,
                    rightOnPress);
        }

        public DoubleButtonWidget(Component leftDefaultMessage, Component rightDefaultMessage, Component leftDescriptionComponent, Component rightDescriptionComponent, Component leftConfirmationMessage, Component rightConfirmationMessage, Button.OnPress leftOnPress, Button.OnPress rightOnPress) {
            this.leftDefaultMessage = leftDefaultMessage;
            this.rightDefaultMessage = rightDefaultMessage;
            this.leftDescriptionComponent = leftDescriptionComponent;
            this.rightDescriptionComponent = rightDescriptionComponent;
            this.leftConfirmationMessage = leftConfirmationMessage;
            this.rightConfirmationMessage = rightConfirmationMessage;
            this.leftOnPress = leftOnPress;
            this.rightOnPress = rightOnPress;
        }

        @Override
        public void init(int posX, int posY) {
            super.init(posX, posY);
            this.addRenderableWidget(new ConfirmationTextButton(posX,
                    posY + 1,
                    94,
                    20,
                    this.leftDefaultMessage,
                    this.leftConfirmationMessage,
                    this.leftOnPress)).setTooltip(Tooltip.create(this.leftDescriptionComponent));
            this.addRenderableWidget(new ConfirmationTextButton(posX + 100,
                    posY + 1,
                    94,
                    20,
                    this.rightDefaultMessage,
                    this.rightConfirmationMessage,
                    this.rightOnPress)).setTooltip(Tooltip.create(this.rightDescriptionComponent));
        }
    }
}
