package me.myogoo.ae2tb.mixin.emi;

import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.runtime.EmiDrawContext;
import dev.emi.emi.runtime.EmiFavorite;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;


@Mixin(EmiFavorite.class)
public abstract class ItemEmiStackMixin {

    @Shadow
    public abstract List<EmiStack> getEmiStacks();

    @Shadow
    public abstract EmiIngredient getStack();

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiIngredient;render(Lnet/minecraft/client/gui/GuiGraphics;IIFI)V", shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void renderAELabels(GuiGraphics draw, int x, int y, float delta, int flags, CallbackInfo ci, @Local(name = "context") EmiDrawContext context) {
        /*
        var player = Minecraft.getInstance().player;
        if(player == null) {
            return;
        }
        if (!(player.containerMenu instanceof MEStorageMenu menu)) {
            return;
        }

        var entries = menu.getClientRepo().getAllEntries();
        var genericStack = GenericStack.fromItemStack(getEmiStacks().getFirst().getItemStack());
        if (entries == null) {
            return;
        }

        // -4 min no decorations!
        if (flags == -4 || flags == -3) { //hmm... why -4? negative values...
            for(var entry: entries) {
                if(entry.getWhat() != null && entry.getWhat().matches(genericStack)) {
                    EmiRenderHelper.renderAmount(context, x, y, EmiPort.literal(entry.getWhat().formatAmount(entry.getStoredAmount(), AmountFormat.SLOT)));
                    break;
                }
            }
        }
         */
    }
}
