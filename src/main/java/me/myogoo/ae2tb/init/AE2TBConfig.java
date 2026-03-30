package me.myogoo.ae2tb.init;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;

public class AE2TBConfig {
    public static void initialize(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, me.myogoo.ae2tb.config.AE2TBConfig.CLIENT,"ae2tb-client.toml");
    }
}
