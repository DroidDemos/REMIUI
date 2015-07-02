package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FACLConfig implements SafeParcelable {
    public static final e CREATOR;
    private static final String GI;
    boolean IA;
    boolean IB;
    boolean Iw;
    String Ix;
    boolean Iy;
    boolean Iz;
    final int version;

    static {
        GI = "[" + FACLConfig.class.getSimpleName() + "]";
        CREATOR = new e();
    }

    FACLConfig(int version, boolean isAllCirclesVisible, String visibleEdges, boolean isAllContactsVisible, boolean showCircles, boolean showContacts, boolean hasShowCircles) {
        this.version = version;
        this.Iw = isAllCirclesVisible;
        this.Ix = visibleEdges;
        this.Iy = isAllContactsVisible;
        this.Iz = showCircles;
        this.IA = showContacts;
        this.IB = hasShowCircles;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
