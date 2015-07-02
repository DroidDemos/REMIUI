package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;

public class OnEventResponse implements SafeParcelable {
    public static final Creator<OnEventResponse> CREATOR;
    final int YZ;
    final ChangeEvent aaX;
    final CompletionEvent aaY;
    final int mVersionCode;

    static {
        CREATOR = new aq();
    }

    OnEventResponse(int versionCode, int eventType, ChangeEvent changeEvent, CompletionEvent completionEvent) {
        this.mVersionCode = versionCode;
        this.YZ = eventType;
        this.aaX = changeEvent;
        this.aaY = completionEvent;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aq.a(this, dest, flags);
    }
}
