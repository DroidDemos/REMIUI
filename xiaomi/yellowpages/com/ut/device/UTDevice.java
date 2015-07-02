package com.ut.device;

import android.content.Context;

public class UTDevice {
    public static String getUtdid(Context context) {
        return com.ta.utdid2.device.UTDevice.getUtdid(context);
    }
}
