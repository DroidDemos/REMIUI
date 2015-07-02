package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Comparator;

public class DetectedActivity implements SafeParcelable {
    public static final DetectedActivityCreator CREATOR;
    public static final Comparator<DetectedActivity> atj;
    int atk;
    int atl;
    private final int mVersionCode;

    static {
        atj = new Comparator<DetectedActivity>() {
            public int a(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
                int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
                return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
            }

            public /* synthetic */ int compare(Object x0, Object x1) {
                return a((DetectedActivity) x0, (DetectedActivity) x1);
            }
        };
        CREATOR = new DetectedActivityCreator();
    }

    public DetectedActivity(int versionCode, int activityType, int confidence) {
        this.mVersionCode = versionCode;
        this.atk = activityType;
        this.atl = confidence;
    }

    private int hq(int i) {
        return i > 9 ? 4 : i;
    }

    public int describeContents() {
        return 0;
    }

    public int getConfidence() {
        return this.atl;
    }

    public int getType() {
        return hq(this.atk);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return "DetectedActivity [type=" + getType() + ", confidence=" + this.atl + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        DetectedActivityCreator.a(this, out, flags);
    }
}
