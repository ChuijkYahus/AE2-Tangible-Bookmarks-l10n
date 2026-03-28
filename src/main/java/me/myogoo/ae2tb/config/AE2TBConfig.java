package me.myogoo.ae2tb.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AE2TBConfig {
    public static final ModConfigSpec CLIENT = new Client().get();

    public static class Client {
        private final ModConfigSpec spec;

        Client() {
            var builder = new ModConfigSpec.Builder();
            spec = builder.build();
        }

        ModConfigSpec get() {
            return spec;
        }
    }
}
