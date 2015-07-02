package com.alipay.sdk.sys;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class UserLocation {
    private static double a;
    private static double b;

    static {
        a = -1.0d;
        b = -1.0d;
    }

    public static void a(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager.isProviderEnabled("gps")) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    a = lastKnownLocation.getLatitude();
                    b = lastKnownLocation.getLongitude();
                }
            }
        } catch (Exception e) {
        }
    }

    public static double a() {
        return a;
    }

    public static double b() {
        return b;
    }

    public static String c() {
        return b + ";" + a;
    }
}
