package me.myogoo.ae2tb.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AE2TBConfig {
    public static final Common COMMON = new Common();
    public static final ModConfigSpec COMMON_SPEC = COMMON.get();
    public static final ModConfigSpec CLIENT = new Client().get();

    public static boolean allowBookmarkInteractionWithoutUpgrade() {
        return COMMON.allowBookmarkInteractionWithoutUpgrade.get();
    }

    public static class Common {
        private final ModConfigSpec spec;
        public final ModConfigSpec.BooleanValue allowBookmarkInteractionWithoutUpgrade;

        Common() {
            var builder = new ModConfigSpec.Builder();

            builder.push("upgrade");
            this.allowBookmarkInteractionWithoutUpgrade = builder
                    .comment("Allows bookmark interaction without installing the Terminal Bookmark Interact Card.")
                    .define("allowBookmarkInteractionWithoutUpgrade", false);
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
