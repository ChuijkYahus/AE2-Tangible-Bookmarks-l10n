package me.myogoo.ae2tangilblebookmarks.init;

import me.myogoo.ae2tangilblebookmarks.AE2TB;
import me.myogoo.ae2tangilblebookmarks.network.serverbound.AE2TBInteractionPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class AE2TBNetwork {
    public static void init(RegisterPayloadHandlersEvent event) {
        var register = event.registrar(AE2TB.MODID);

        register.playToServer(AE2TBInteractionPacket.TYPE, AE2TBInteractionPacket.STREAM_CODEC, AE2TBInteractionPacket::handleOnServer);
    }
}
