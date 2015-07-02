package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR;
    private final long arA;
    private final long arz;

    static {
        CREATOR = new Creator<OneoffTask>() {
            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return eX(x0);
            }

            public OneoffTask eX(Parcel parcel) {
                return new OneoffTask(parcel);
            }

            public OneoffTask[] hf(int i) {
                return new OneoffTask[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return hf(x0);
            }
        };
    }

    OneoffTask() {
        this.arA = -1;
        this.arz = -1;
    }

    private OneoffTask(Parcel in) {
        super(in);
        this.arz = in.readLong();
        this.arA = in.readLong();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeLong(this.arz);
        parcel.writeLong(this.arA);
    }
}
