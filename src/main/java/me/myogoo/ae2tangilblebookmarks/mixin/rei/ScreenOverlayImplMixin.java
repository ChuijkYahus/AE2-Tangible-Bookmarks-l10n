package me.myogoo.ae2tangilblebookmarks.mixin.rei;

import appeng.helpers.InventoryAction;
import appeng.menu.me.common.MEStorageMenu;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.InputConstants;
import me.myogoo.ae2tangilblebookmarks.client.KeyBindings;
import me.myogoo.ae2tangilblebookmarks.integration.ae2.HandleInteraction;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.math.Point;
import me.shedaniel.math.impl.PointHelper;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import me.shedaniel.rei.impl.client.gui.widget.favorites.FavoritesListWidget;
import me.shedaniel.rei.impl.client.gui.widget.favorites.panel.FavoritesPanel;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(ScreenOverlayImpl.class)
public abstract class ScreenOverlayImplMixin {

    @Shadow
    @Final
    private List<Widget> widgets;

    @Inject(
            method = "mouseClicked",
            at = @At(value = "TAIL"),
            remap = false,
            cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        var player = Minecraft.getInstance().player;
        if (player == null) {
            cir.setReturnValue(false);
            return;
        }

        if (!(player.containerMenu instanceof MEStorageMenu menu)) {
            cir.setReturnValue(false);
            return;
        }

        var favoritesWidget = this.widgets.stream()
                .filter(widget -> widget instanceof FavoritesListWidget)
                .map(widget -> (FavoritesListWidget) widget)
                .findFirst().orElse(null);
        if (favoritesWidget == null) {
            cir.setReturnValue(false);
            return;
        }

        ItemStack stack = favoritesWidget.getFocusedStack().cheatsAs().castValue();
        if (stack == null || stack.isEmpty()) {
            cir.setReturnValue(false);
            return;
        }
        if (isClicked(KeyBindings.PICKUP_SINGLE_ITEM, button)) {
            HandleInteraction.sendPacket(menu, stack, InventoryAction.PICKUP_SINGLE);
            cir.setReturnValue(true);
            return;
        }
        if (isClicked(KeyBindings.PICKUP_SET_ITEM, button)) {
            HandleInteraction.sendPacket(menu, stack, InventoryAction.SHIFT_CLICK);
            cir.setReturnValue(true);
            return;
        }
        if (isClicked(KeyBindings.PICKED_ITEM_AUTOCRAFTING, button)) {
            HandleInteraction.sendPacket(menu, stack, InventoryAction.AUTO_CRAFT);
            cir.setReturnValue(true);
            return;
        }
    }

    @Unique
    private boolean isClicked(KeyMapping keyMapping, int button) {
        return keyMapping.matchesMouse(button) && keyMapping.getKeyModifier().isActive(KeyConflictContext.GUI);
    }
}
