package com.xiaomi.passport;

import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.passport.interfaces.ActivityInterface;
import com.xiaomi.passport.interfaces.PassportInterface;

public class PassportExternal {
    private static ActivityInterface sActivityInterface;
    private static PassportInterface sPassportInterface;

    public static ActivityInterface getActivityInterface() {
        return sActivityInterface;
    }

    public static void setActivityInterface(ActivityInterface activityInterface) {
        sActivityInterface = activityInterface;
    }

    public static PassportInterface getPassportInterface() {
        return sPassportInterface;
    }

    public static void setPassportInterface(PassportInterface passportInterface) {
        sPassportInterface = passportInterface;
    }

    public static void customizeAnalyticsTrackerInstance(AnalyticsTracker analyticsTracker) {
        AnalyticsTracker.customizeAnalyticsTrackerInstance(analyticsTracker);
    }
}
