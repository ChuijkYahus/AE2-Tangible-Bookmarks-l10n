package me.myogoo.ae2tangilblebookmarks.init;

import me.myogoo.ae2tangilblebookmarks.AE2TB;
import me.myogoo.ae2tangilblebookmarks.client.KeyBindings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = AE2TB.MODID, value = Dist.CLIENT)
public class AE2TBKeyBinding {
    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.PICKUP_SINGLE_ITEM);
        event.register(KeyBindings.PICKUP_SET_ITEM);
        event.register(KeyBindings.PICKED_ITEM_AUTOCRAFTING);
    }
}
