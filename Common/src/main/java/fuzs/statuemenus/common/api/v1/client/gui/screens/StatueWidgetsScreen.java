package fuzs.statuemenus.common.api.v1.client.gui.screens;

import fuzs.statuemenus.common.api.v1.client.gui.components.ImageButtonWithText;
import fuzs.statuemenus.common.api.v1.network.client.data.DataSyncHandler;
import fuzs.statuemenus.common.api.v1.world.inventory.StatueHolder;
import fuzs.statuemenus.common.api.v1.world.inventory.data.StatueScreenType;
import fuzs.statuemenus.common.impl.StatueMenus;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.renderer.texture.TickableTexture;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public abstract class StatueWidgetsScreen extends AbstractStatueScreen {
    public static final WidgetSprites MAGNIFYING_GLASS_BUTTON_SPRITES = new WidgetSprites(StatueMenus.id(
            "container/statue/magnifying_glass_button"),
            StatueMenus.id("container/statue/magnifying_glass_button_disabled"),
            StatueMenus.id("container/statue/magnifying_glass_button_highlighted"));
    public static final Component FOCUS_COMPONENT = Component.translatable(StatueScreenType.POSITION.id()
            .toLanguageKey("screen", "focus"));
    public static final Component SAVE_COMPONENT = Component.translatable(StatueScreenType.POSITION.id()
            .toLanguageKey("screen", "save"));
    protected static final int WIDGET_HEIGHT = 22;

    protected final List<ArmorStandWidget> widgets;
    private StatueWidgetsScreen.@Nullable ArmorStandWidget activeWidget;

    public StatueWidgetsScreen(StatueHolder holder, Inventory inventory, Component component, DataSyncHandler dataSyncHandler) {
        super(holder, inventory, component, dataSyncHandler);
        this.widgets = this.buildWidgets(holder.getEntity());
    }

    protected abstract List<ArmorStandWidget> buildWidgets(LivingEntity livingEntity);

    protected static <T extends StatueWidgetsScreen> List<ArmorStandWidget> buildWidgets(T screen, LivingEntity livingEntity, List<ArmorStandWidgetFactory<? super T>> widgetFactories) {
        return widgetFactories.stream().map(factory -> factory.apply(screen, livingEntity)).toList();
    }

    private Collection<ArmorStandWidget> getActivePositionComponentWidgets() {
        if (this.activeWidget != null) {
            List<ArmorStandWidget> activeWidgets = new ArrayList<>(List.of(this.activeWidget));
            for (ArmorStandWidget widget : this.widgets) {
                if (widget != this.activeWidget && widget.alwaysVisible(this.activeWidget)) {
                    activeWidgets.add(widget);
                }
            }

            return activeWidgets;
        }

        return this.widgets;
    }

    protected void setActiveWidget(ArmorStandWidget widget) {
        if (this.activeWidget == widget) {
            this.toggleMenuRendering(false);
            this.activeWidget = null;
        } else {
            this.activeWidget = widget;
            this.toggleMenuRendering(true);
        }
    }

    @Override
    protected boolean showInventoryEntity() {
        return false;
    }

    @Override
    protected boolean disableMenuRendering() {
        return this.activeWidget != null;
    }

    @Override
    protected void toggleMenuRendering(boolean disableMenuRendering) {
        super.toggleMenuRendering(disableMenuRendering);
        for (ArmorStandWidget widget : this.widgets) {
            widget.setVisible(!disableMenuRendering || widget.alwaysVisible(this.activeWidget));
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.getActivePositionComponentWidgets().forEach(ArmorStandWidget::tick);
    }

    @Override
    protected void init() {
        super.init();
        int fullWidgetsHeight =
                this.widgets.size() * WIDGET_HEIGHT + (this.widgets.size() - 1) * this.getWidgetRenderOffset();
        int startY = (this.imageHeight - fullWidgetsHeight) / 2;
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i)
                    .init(this.leftPos + 8,
                            this.topPos + startY + this.getWidgetTopOffset() + i * (WIDGET_HEIGHT
                                    + this.getWidgetRenderOffset()));
        }
    }

    protected int getWidgetRenderOffset() {
        return 7;
    }

    protected int getWidgetTopOffset() {
        return this.withCloseButton() ? 7 : 0;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (ArmorStandWidget widget : this.getActivePositionComponentWidgets()) {
            widget.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);
        }

        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);
    }

    @FunctionalInterface
    protected interface ArmorStandWidgetFactory<T extends StatueWidgetsScreen> extends BiFunction<T, LivingEntity, ArmorStandWidget> {

    }

    protected abstract class ArmorStandWidget extends AbstractContainerEventHandler implements Renderable, TickableTexture {
        private final List<GuiEventListener> children = new ArrayList<>();
        protected final Component title;
        protected int posX;
        protected int posY;
        @Nullable
        protected Button toggleButton;

        protected ArmorStandWidget() {
            this(CommonComponents.EMPTY);
        }

        protected ArmorStandWidget(Component title) {
            this.title = title;
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.children;
        }

        @Override
        public void tick() {
            // NO-OP
        }

        public void reset() {
            // NO-OP
        }

        protected boolean supportsToggleButton() {
            return false;
        }

        public void init(int posX, int posY) {
            this.children().clear();
            this.posX = posX;
            this.posY = posY;
            if (this.supportsToggleButton()) {
                this.toggleButton = new ImageButton(posX + 174,
                        posY + 1,
                        20,
                        20,
                        MAGNIFYING_GLASS_BUTTON_SPRITES,
                        (Button button) -> {
                            StatueWidgetsScreen.this.setActiveWidget(this);
                        });
                this.toggleButton.setTooltip(Tooltip.create(FOCUS_COMPONENT));
                this.addRenderableWidget(this.toggleButton);
            }
        }

        public void setVisible(boolean visible) {
            for (GuiEventListener widget : this.children()) {
                if (widget instanceof AbstractWidget abstractWidget) {
                    abstractWidget.visible = visible;
                }
            }
        }

        protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T widget) {
            this.children.add(widget);
            StatueWidgetsScreen.this.addRenderableWidget(widget);
            return widget;
        }

        protected <T extends GuiEventListener & Renderable> T addRenderableOnly(T widget) {
            this.children.add(widget);
            StatueWidgetsScreen.this.addRenderableOnly(widget);
            return widget;
        }

        @Override
        public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
            int x = this.posX + 36;
            int y = this.posY + 6;
            if (StatueWidgetsScreen.this.disableMenuRendering()) {
                int backgroundColor = StatueWidgetsScreen.this.minecraft.options.getBackgroundColor(0.25F);
                int textWidth = StatueWidgetsScreen.this.font.width(this.title);
                guiGraphics.fill(x - textWidth / 2 - 2,
                        y - 2,
                        x + textWidth / 2 + 2,
                        y + StatueWidgetsScreen.this.font.lineHeight + 2,
                        backgroundColor);
                ImageButtonWithText.centeredTextWithShadow(guiGraphics,
                        StatueWidgetsScreen.this.font,
                        this.title,
                        x,
                        y,
                        -1,
                        true);
            } else {
                ImageButtonWithText.centeredTextWithShadow(guiGraphics,
                        StatueWidgetsScreen.this.font,
                        this.title,
                        x,
                        y,
                        0xFF404040,
                        false);
            }
        }

        public boolean alwaysVisible(StatueWidgetsScreen.@Nullable ArmorStandWidget activeWidget) {
            return activeWidget == this;
        }
    }
}
