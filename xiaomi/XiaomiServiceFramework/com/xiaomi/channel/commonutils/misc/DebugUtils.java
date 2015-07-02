package com.xiaomi.channel.commonutils.misc;

import junit.framework.Assert;

public class DebugUtils {
    public static void assertTrue(boolean condition) {
        if (BuildSettings.IsDebugBuild || BuildSettings.IsSandBoxBuild() || BuildSettings.IsOneBoxBuild() || BuildSettings.IsTestBuild || BuildSettings.IsRCBuild) {
            Assert.assertTrue(condition);
        }
    }
}
