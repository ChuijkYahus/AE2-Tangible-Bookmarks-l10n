package me.myogoo.ae2tangilblebookmarks;

import me.myogoo.ae2tangilblebookmarks.init.AE2TBNetwork;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(AE2TB.MODID)
public class AE2TB {
    public static final String MODID = "ae2tangilblebookmarks";

    public AE2TB(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(AE2TBNetwork::init);
    }

    public static ResourceLocation makeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID,path);
    }

}