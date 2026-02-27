package me.myogoo.ae2tangiblebookmarks.network.serverbound;

import appeng.api.stacks.AEItemKey;
import appeng.helpers.InventoryAction;
import appeng.menu.me.common.MEStorageMenu;
import me.myogoo.ae2tangiblebookmarks.mixin.MEStorageMenuStorageMixin;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record AE2TBInteractionPacket(
        int containerId,
        ItemStack itemStack,
        InventoryAction action) {
    public static AE2TBInteractionPacket decode(FriendlyByteBuf buffer) {
        var containerId = buffer.readInt();
        var stack = buffer.readItem();
        var action = InventoryAction.values()[buffer.readInt()];
        return new AE2TBInteractionPacket(containerId, stack, action);
    }

    public void write(FriendlyByteBuf data) {
        data.writeInt(containerId);
        data.writeItem(itemStack);
        data.writeInt(action.ordinal());
    }

    public void handleOnServer(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof MEStorageMenu) {
                var menu = ((MEStorageMenuStorageMixin) player.containerMenu);
                var key = AEItemKey.of(itemStack);
                menu.callHandleNetworkInteraction(player, key, action);
            }
        });
        context.setPacketHandled(true);
    }
}
