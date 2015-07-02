package miui.external;

import miui.external.SdkConstants.SdkError;

/* synthetic */ class f {
    static final /* synthetic */ int[] oP;

    static {
        oP = new int[SdkError.values().length];
        try {
            oP[SdkError.NO_SDK.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            oP[SdkError.LOW_SDK_VERSION.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
