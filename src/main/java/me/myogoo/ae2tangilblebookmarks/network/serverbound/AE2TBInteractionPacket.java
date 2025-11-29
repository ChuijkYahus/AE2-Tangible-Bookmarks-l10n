package me.myogoo.ae2tangilblebookmarks.network.serverbound;

import appeng.api.stacks.AEItemKey;
import appeng.core.network.ServerboundPacket;
import appeng.helpers.InventoryAction;
import appeng.menu.me.common.MEStorageMenu;
import me.myogoo.ae2tangilblebookmarks.AE2TB;
import me.myogoo.ae2tangilblebookmarks.mixin.MEStorageMenuStorageMixin;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public record AE2TBInteractionPacket(
        int containerId,
        ItemStack itemStack,
        InventoryAction action
) implements ServerboundPacket {
    public static final StreamCodec<RegistryFriendlyByteBuf, AE2TBInteractionPacket> STREAM_CODEC = StreamCodec.ofMember(
            AE2TBInteractionPacket::write,
            AE2TBInteractionPacket::decode
    );

    public static final Type<AE2TBInteractionPacket> TYPE = new CustomPacketPayload.Type<>(AE2TB.makeId("interaction"));

    public static AE2TBInteractionPacket decode(RegistryFriendlyByteBuf buffer) {
        var containerId = buffer.readInt();
        var stack = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
        var action = buffer.readEnum(InventoryAction.class);
        return new AE2TBInteractionPacket(containerId, stack, action);
    }

    public void write(RegistryFriendlyByteBuf data) {
        data.writeInt(containerId);
        ItemStack.OPTIONAL_STREAM_CODEC.encode(data, itemStack);
        data.writeEnum(action);
    }


    @Override
    public void handleOnServer(ServerPlayer player) {
        if(player.containerMenu instanceof MEStorageMenu) {
            var menu = ((MEStorageMenuStorageMixin) player.containerMenu);
            var key = AEItemKey.of(itemStack);
            menu.callHandleNetworkInteraction(player, key, action);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
