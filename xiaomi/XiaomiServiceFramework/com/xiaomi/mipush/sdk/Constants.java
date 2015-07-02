package com.xiaomi.mipush.sdk;

import com.xiaomi.channel.commonutils.misc.BuildSettings;

public class Constants {
    private static void useOnebox() {
        BuildSettings.setEnvType(3);
    }

    private static void useSandbox() {
        BuildSettings.setEnvType(2);
    }

    private static void useOfficial() {
        BuildSettings.setEnvType(1);
    }

    protected static int getEnvType() {
        return BuildSettings.getEnvType();
    }
}
