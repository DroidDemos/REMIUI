package com.xiaomi.channel.commonutils.misc;

public class BuildSettings {
    public static final String BETA = "BETA";
    public static final String DEBUG = "DEBUG";
    public static final boolean IsBetaBuild;
    public static final boolean IsDebugBuild;
    public static final boolean IsDefaultChannel;
    public static final boolean IsForYYBuild;
    public static final boolean IsLogableBuild;
    public static final boolean IsRCBuild;
    public static boolean IsTestBuild = false;
    public static final String LOGABLE = "LOGABLE";
    public static final int Official = 1;
    public static final int OneBox = 3;
    public static final String ReleaseChannel = "xmsf";
    public static final int SandBox = 2;
    public static final String TEST = "TEST";
    public static final String YY = "YY";
    private static int envType;

    static {
        boolean z = IsRCBuild;
        IsDefaultChannel = ReleaseChannel.contains("2A2FE0D7");
        boolean z2 = (IsDefaultChannel || DEBUG.equalsIgnoreCase(ReleaseChannel)) ? true : IsRCBuild;
        IsDebugBuild = z2;
        IsLogableBuild = LOGABLE.equalsIgnoreCase(ReleaseChannel);
        IsForYYBuild = ReleaseChannel.contains(YY);
        IsTestBuild = ReleaseChannel.equalsIgnoreCase(TEST);
        IsBetaBuild = BETA.equalsIgnoreCase(ReleaseChannel);
        if (ReleaseChannel != null && ReleaseChannel.startsWith("RC")) {
            z = true;
        }
        IsRCBuild = z;
        envType = Official;
        if (ReleaseChannel.equalsIgnoreCase("SANDBOX")) {
            envType = SandBox;
        } else if (ReleaseChannel.equalsIgnoreCase("ONEBOX")) {
            envType = OneBox;
        } else {
            envType = Official;
        }
    }

    public static boolean IsSandBoxBuild() {
        return envType == SandBox ? true : IsRCBuild;
    }

    public static boolean IsOneBoxBuild() {
        return envType == OneBox ? true : IsRCBuild;
    }

    public static int getEnvType() {
        return envType;
    }

    public static void setEnvType(int type) {
        envType = type;
    }
}
