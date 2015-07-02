package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class PeriodicTask extends Task {
    public static final Creator<PeriodicTask> CREATOR;
    private final long arD;
    private final long arE;

    static {
        CREATOR = new Creator<PeriodicTask>() {
            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return eZ(x0);
            }

            public PeriodicTask eZ(Parcel parcel) {
                return new PeriodicTask(parcel);
            }

            public PeriodicTask[] hh(int i) {
                return new PeriodicTask[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return hh(x0);
            }
        };
    }

    PeriodicTask() {
        this.arD = -1;
        this.arE = -1;
    }

    private PeriodicTask(Parcel in) {
        super(in);
        this.arD = in.readLong();
        this.arE = in.readLong();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeLong(this.arD);
        parcel.writeLong(this.arE);
    }
}
