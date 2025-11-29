package me.myogoo.ae2tangilblebookmarks.mixin;

import appeng.core.network.ServerboundPacket;
import appeng.helpers.InventoryAction;
import appeng.menu.me.common.MEStorageMenu;
import com.mojang.blaze3d.platform.InputConstants;
import me.myogoo.ae2tangilblebookmarks.client.KeyBindings;
import me.myogoo.ae2tangilblebookmarks.network.serverbound.AE2TBInteractionPacket;
import mezz.jei.common.input.IInternalKeyMappings;
import mezz.jei.gui.input.CombinedRecipeFocusSource;
import mezz.jei.gui.input.IClickableIngredientInternal;
import mezz.jei.gui.input.IUserInputHandler;
import mezz.jei.gui.input.UserInput;
import mezz.jei.gui.input.handlers.FocusInputHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.neoforge.client.settings.KeyModifier;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mixin(FocusInputHandler.class)
public class FocusInputHandlerMixin {
    @Final
    @Shadow
    private CombinedRecipeFocusSource focusSource;

    @Inject(
            method = "handleUserInput",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;empty()Ljava/util/Optional;"),
            cancellable = true,
            remap = false
    )
    public void handleUserInput(Screen rawScreen, UserInput input, IInternalKeyMappings keyBindings, CallbackInfoReturnable<Optional<IUserInputHandler>> cir) {
        handleMiddleClick(rawScreen, input, keyBindings, KeyBindings.PICKUP_SINGLE_ITEM, InventoryAction.PICKUP_SINGLE);
        handleMiddleClick(rawScreen, input, keyBindings, KeyBindings.PICKUP_SET_ITEM, InventoryAction.SHIFT_CLICK);
        handleMiddleClick(rawScreen, input, keyBindings, KeyBindings.PICKED_ITEM_AUTOCRAFTING, InventoryAction.AUTO_CRAFT);
        cir.setReturnValue(Optional.empty());
    }

    @Unique
    private void handleMiddleClick(Screen rawScreen, UserInput input, IInternalKeyMappings keyBindings, KeyMapping keyMapping, InventoryAction action) {
        if(!input.is(keyMapping)) {
            return;
        }
        var minecraft = rawScreen.getMinecraft();
        var localPlayer = minecraft.player;


        if (localPlayer != null && localPlayer.containerMenu instanceof MEStorageMenu menu) {
            var menuMixin = ((MEStorageMenuStorageMixin) menu);
            List<IClickableIngredientInternal<?>> ingredientUnderMouse = focusSource.getIngredientUnderMouse(input, keyBindings)
                    .filter(x -> x.getElement().getBookmark().isPresent()).toList();


            for (IClickableIngredientInternal<?> clicked : ingredientUnderMouse) {
                var itemStack = clicked.getElement().getTypedIngredient().getItemStack();
                if (itemStack.isEmpty()) continue;
                if (menu.isClientSide()) {
                    if (action != null) {
                        ServerboundPacket packet = new AE2TBInteractionPacket(menu.containerId, itemStack.get(), action);
                        PacketDistributor.sendToServer(packet);
                        return;
                    }
                }
            }
        }
    }
}
