package me.myogoo.ae2tb.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AE2TBConfig {
    public static final Common COMMON = new Common();
    public static final ModConfigSpec COMMON_SPEC = COMMON.get();
    public static final ModConfigSpec CLIENT = new Client().get();

    public static boolean QoL() {
        return COMMON.QoL.get();
    }

    public static class Common {
        private final ModConfigSpec spec;
        public final ModConfigSpec.BooleanValue QoL;

        Common() {
            var builder = new ModConfigSpec.Builder();

            builder.push("upgrade");
            this.QoL = builder
                    .comment("Enables QoL behavior without requiring the Terminal Bookmark Interact Card. When false, bookmark interaction and bookmark amount rendering require the upgrade card in the terminal upgrade slot.")
                    .define("QoL", false);
            builder.pop();

            this.spec = builder.build();
        }

        ModConfigSpec get() {
            return spec;
        }
    }

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
