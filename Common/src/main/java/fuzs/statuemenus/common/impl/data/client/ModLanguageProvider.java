package fuzs.statuemenus.common.impl.data.client;

import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.statuemenus.common.api.v1.client.gui.screens.*;
import fuzs.statuemenus.common.api.v1.helper.ArmorStandInteractHelper;
import fuzs.statuemenus.common.api.v1.world.inventory.data.*;
import fuzs.statuemenus.common.impl.world.inventory.StatuePoseImpl;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ArmorStandInteractHelper.OPEN_SCREEN_TRANSLATION_KEY,
                "Use %s + %s with an empty hand to open configuration screen.");
        this.add(StatuePoseImpl.SourceType.MINECRAFT.component, "By %s");
        this.add(StatuePose.ATHENA.getTranslationKey(), "Athena");
        this.add(StatuePose.BRANDISH.getTranslationKey(), "Brandish");
        this.add(StatuePose.CANCAN.getTranslationKey(), "Cancan");
        this.add(StatuePose.DEFAULT.getTranslationKey(), "Default");
        this.add(StatuePose.ENTERTAIN.getTranslationKey(), "Entertain");
        this.add(StatuePose.HERO.getTranslationKey(), "Hero");
        this.add(StatuePose.HONOR.getTranslationKey(), "Honor");
        this.add(StatuePose.RIPOSTE.getTranslationKey(), "Riposte");
        this.add(StatuePose.SALUTE.getTranslationKey(), "Salute");
        this.add(StatuePose.SOLEMN.getTranslationKey(), "Solemn");
        this.add(StatuePose.ZOMBIE.getTranslationKey(), "Zombie");
        this.add(StatuePose.WALKING.getTranslationKey(), "Walking");
        this.add(StatuePose.RUNNING.getTranslationKey(), "Running");
        this.add(StatuePose.POINTING.getTranslationKey(), "Pointing");
        this.add(StatuePose.BLOCKING.getTranslationKey(), "Blocking");
        this.add(StatuePose.LUNGEING.getTranslationKey(), "Lungeing");
        this.add(StatuePose.WINNING.getTranslationKey(), "Winning");
        this.add(StatuePose.SITTING.getTranslationKey(), "Sitting");
        this.add(StatuePose.ARABESQUE.getTranslationKey(), "Arabesque");
        this.add(StatuePose.CUPID.getTranslationKey(), "Cupid");
        this.add(StatuePose.CONFIDENT.getTranslationKey(), "Confident");
        this.add(StatuePose.DEATH.getTranslationKey(), "Death");
        this.add(StatuePose.FACEPALM.getTranslationKey(), "Facepalm");
        this.add(StatuePose.LAZING.getTranslationKey(), "Lazing");
        this.add(StatuePose.CONFUSED.getTranslationKey(), "Confused");
        this.add(StatuePose.FORMAL.getTranslationKey(), "Formal");
        this.add(StatuePose.SAD.getTranslationKey(), "Sad");
        this.add(StatuePose.JOYOUS.getTranslationKey(), "Joyous");
        this.add(StatuePose.STARGAZING.getTranslationKey(), "Stargazing");
        this.add(StatueScreenType.EQUIPMENT.getTranslationKey(), "Equipment");
        this.add(StatueScreenType.ROTATIONS.getTranslationKey(), "Rotations");
        this.add(StatueScreenType.STYLE.getTranslationKey(), "Style");
        this.add(StatueScreenType.POSES.getTranslationKey(), "Poses");
        this.add(StatueScreenType.POSITION.getTranslationKey(), "Position");
        this.add(StatueStyleScreen.TEXT_BOX_HINT_TRANSLATION_KEY, "Custom Name");
        this.add(StatueStyleScreen.TEXT_BOX_TOOLTIP_TRANSLATION_KEY, "Set a name to display above the statue.");
        this.add(StatueStyleOption.SHOW_ARMS.getTranslationKey(), "Show Arms");
        this.add(StatueStyleOption.SHOW_ARMS.getDescriptionKey(),
                "Shows the statue's arms, so it may hold items in either hand.");
        this.add(StatueStyleOption.SMALL.getTranslationKey(), "Small");
        this.add(StatueStyleOption.SMALL.getDescriptionKey(), "Makes the statue half it's size like a baby mob.");
        this.add(StatueStyleOption.INVISIBLE.getTranslationKey(), "Invisible");
        this.add(StatueStyleOption.INVISIBLE.getDescriptionKey(),
                "Makes the statue itself invisible, but still shows all equipped items.");
        this.add(StatueStyleOption.NO_BASE_PLATE.getTranslationKey(), "No Base Plate");
        this.add(StatueStyleOption.NO_BASE_PLATE.getDescriptionKey(),
                "Hide the stone base plate at the statue's feet.");
        this.add(StatueStyleOption.SHOW_NAME.getTranslationKey(), "Show Name");
        this.add(StatueStyleOption.SHOW_NAME.getDescriptionKey(), "Render the statue's name tag above it's head.");
        this.add(StatueStyleOption.IMMOVABLE.getTranslationKey(), "Immovable");
        this.add(StatueStyleOption.IMMOVABLE.getDescriptionKey(),
                "Makes the statue have no physics in the world, so it may float freely.");
        this.add(StatueStyleOption.INVULNERABLE.getTranslationKey(), "Invulnerable");
        this.add(StatueStyleOption.INVULNERABLE.getDescriptionKey(),
                "The statue can neither be broken nor killed in survival mode.");
        this.add(StatueStyleOption.SEALED.getTranslationKey(), "Sealed");
        this.add(StatueStyleOption.SEALED.getDescriptionKey(),
                "Disallows changing equipment and opening this menu in survival mode.");
        this.add(StatuePositionScreen.SCALE_TRANSLATION_KEY, "Scale:");
        this.add(StatuePositionScreen.ROTATION_TRANSLATION_KEY, "Rotation:");
        this.add(StatuePositionScreen.POSITION_X_TRANSLATION_KEY, "X-Position:");
        this.add(StatuePositionScreen.POSITION_Y_TRANSLATION_KEY, "Y-Position:");
        this.add(StatuePositionScreen.POSITION_Z_TRANSLATION_KEY, "Z-Position:");
        this.add(StatuePositionScreen.INCREMENT_TRANSLATION_KEY, "+%s");
        this.add(StatuePositionScreen.DECREMENT_TRANSLATION_KEY, "-%s");
        this.add(StatuePositionScreen.DEGREES_TRANSLATION_KEY, "%s\u00B0");
        this.add(StatuePositionScreen.MOVE_BY_TRANSLATION_KEY, "Move By:");
        this.add(StatuePositionScreen.PIXELS_TRANSLATION_KEY, "%s Pixel(s)");
        this.add(StatuePositionScreen.BLOCKS_TRANSLATION_KEY, "%s Block(s)");
        this.add(StatuePositionScreen.CENTERED_TRANSLATION_KEY, "Align Centered");
        this.add(StatuePositionScreen.CENTERED_DESCRIPTION_TRANSLATION_KEY,
                "Align an armor stand in the center of the block position it is placed on.");
        this.add(StatuePositionScreen.CORNERED_TRANSLATION_KEY, "Align Cornered");
        this.add(StatuePositionScreen.CORNERED_DESCRIPTION_TRANSLATION_KEY,
                "Align an armor stand at the corner of the block position it is placed on.");
        this.add(StatuePositionScreen.ALIGNED_TRANSLATION_KEY, "Aligned!");
        this.add(StatueWidgetsScreen.FOCUS_COMPONENT, "Focus");
        this.add(StatueWidgetsScreen.SAVE_COMPONENT, "Save");
        this.add(PosePartMutator.HEAD.getTranslationKey(), "Head");
        this.add(PosePartMutator.BODY.getTranslationKey(), "Body");
        this.add(PosePartMutator.LEFT_ARM.getTranslationKey(), "Left Arm");
        this.add(PosePartMutator.RIGHT_ARM.getTranslationKey(), "Right Arm");
        this.add(PosePartMutator.LEFT_LEG.getTranslationKey(), "Left Leg");
        this.add(PosePartMutator.RIGHT_LEG.getTranslationKey(), "Right Leg");
        this.add(PosePartMutator.AXIS_X_TRANSLATION_KEY, "X: %s");
        this.add(PosePartMutator.AXIS_Y_TRANSLATION_KEY, "Y: %s");
        this.add(PosePartMutator.AXIS_Z_TRANSLATION_KEY, "Z: %s");
        this.add(StatueRotationsScreen.TIP_TRANSLATION_KEY + 1,
                "Hold any §dShift§r or §dAlt§r key to lock two-dimensional sliders to a single axis while dragging!");
        this.add(StatueRotationsScreen.TIP_TRANSLATION_KEY + 2,
                "Use arrow keys to move sliders with greater precision than when dragging! Focus a slider first by clicking.");
        this.add(StatueRotationsScreen.RESET_TRANSLATION_KEY, "Reset");
        this.add(StatueRotationsScreen.RANDOMIZE_TRANSLATION_KEY, "Randomize");
        this.add(StatueRotationsScreen.LIMITED_TRANSLATION_KEY, "Limited Rotations");
        this.add(StatueRotationsScreen.UNLIMITED_TRANSLATION_KEY, "Unlimited Rotations");
        this.add(StatueRotationsScreen.COPY_TRANSLATION_KEY, "Copy");
        this.add(StatueRotationsScreen.PASTE_TRANSLATION_KEY, "Paste");
        this.add(StatueRotationsScreen.MIRROR_TRANSLATION_KEY, "Mirror");
        this.add(StatueAlignment.BLOCK.getTranslationKey(), "Align Block On Surface");
        this.add(StatueAlignment.BLOCK.getDescriptionsKey(),
                "Align an armor stand placed on a surface so that a block held by it appears on the surface.");
        this.add(StatueAlignment.FLOATING_ITEM.getTranslationKey(), "Align Item On Surface");
        this.add(StatueAlignment.FLOATING_ITEM.getDescriptionsKey(),
                "Align an armor stand placed on a surface so that an item held by it appears upright on the surface.");
        this.add(StatueAlignment.FLAT_ITEM.getTranslationKey(), "Align Item Flat On Surface");
        this.add(StatueAlignment.FLAT_ITEM.getDescriptionsKey(),
                "Align an armor stand placed on a surface so that a non-tool item held by it appears flat on the surface.");
        this.add(StatueAlignment.TOOL.getTranslationKey(), "Align Tool Flat On Surface");
        this.add(StatueAlignment.TOOL.getDescriptionsKey(),
                "Align an armor stand placed on a surface so that a tool held by it appears flat on the surface.");
        this.add(StatuePosesScreen.CREDITS_TRANSLATION_KEY,
                "Some content on this page originates from the Vanilla Tweaks \"Armor Statues\" data pack. Click this button to go to their website!");
    }
}
