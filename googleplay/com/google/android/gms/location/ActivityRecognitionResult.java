package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ActivityRecognitionResult implements SafeParcelable {
    public static final ActivityRecognitionResultCreator CREATOR;
    List<DetectedActivity> atg;
    long ath;
    long ati;
    private final int mVersionCode;

    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }

    public ActivityRecognitionResult(int versionCode, List<DetectedActivity> probableActivities, long timeMillis, long elapsedRealtimeMillis) {
        this.mVersionCode = 1;
        this.atg = probableActivities;
        this.ath = timeMillis;
        this.ati = elapsedRealtimeMillis;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.atg + ", timeMillis=" + this.ath + ", elapsedRealtimeMillis=" + this.ati + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        ActivityRecognitionResultCreator.a(this, out, flags);
    }
}
