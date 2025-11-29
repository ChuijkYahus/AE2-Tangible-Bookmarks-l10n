package me.myogoo.ae2tangilblebookmarks.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyMapping PICKUP_SINGLE_ITEM = new KeyMapping(
            "key.ae2tb.pickup_single_item",
            KeyConflictContext.GUI,
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "key.categories.ae2tb"
    );

    public static final KeyMapping PICKUP_SET_ITEM = new KeyMapping(
            "key.ae2tb.pickup_set_item",
            KeyConflictContext.GUI,
            KeyModifier.SHIFT,
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "key.categories.ae2tb"
    );

    public static final KeyMapping PICKED_ITEM_AUTOCRAFTING = new KeyMapping(
            "key.ae2tb.picked_item_autocrafting",
            KeyConflictContext.GUI,
            KeyModifier.CONTROL,
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "key.categories.ae2tb"
    );
}
