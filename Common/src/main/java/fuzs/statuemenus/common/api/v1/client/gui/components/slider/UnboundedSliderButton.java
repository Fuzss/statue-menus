package fuzs.statuemenus.common.api.v1.client.gui.components.slider;

public interface UnboundedSliderButton {
    default boolean isDirty() {
        return false;
    }

    default void clearDirty(boolean isDirty) {
        // NO-OP
    }
}
