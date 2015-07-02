package com.google.android.finsky.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class CachedLocationAccess {
    public Location getCachedLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Location gpsLocation = locationManager.getLastKnownLocation("gps");
        Location networkLocation = locationManager.getLastKnownLocation("network");
        if (gpsLocation != null && networkLocation != null) {
            return gpsLocation.getAccuracy() < networkLocation.getAccuracy() ? gpsLocation : networkLocation;
        } else {
            if (gpsLocation != null) {
                return gpsLocation;
            }
            if (networkLocation != null) {
                return networkLocation;
            }
            return null;
        }
    }
}
